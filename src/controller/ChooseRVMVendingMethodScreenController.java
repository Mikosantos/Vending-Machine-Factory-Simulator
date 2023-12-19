package controller;

import gui.*;
import vm_master.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ChooseRVMVendingMethodScreenController implements ActionListener, WindowListener {
    private VendingMachine rvm;
    private ChooseRVMVendingMethodScreen chooseRVMVendingMethodScreen;
    private SelectRVMFeatureScreen selectRVMFeatureScreen;

    public ChooseRVMVendingMethodScreenController(VendingMachine rvm, ChooseRVMVendingMethodScreen chooseRVMVendingMethodScreen) {
        this.rvm = rvm;
        this.chooseRVMVendingMethodScreen = chooseRVMVendingMethodScreen;

        chooseRVMVendingMethodScreen.setVisible(true);
        chooseRVMVendingMethodScreen.setWindowListener(this);
        chooseRVMVendingMethodScreen.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
         *   SEE ITEM INVENTORY
         *   PAY
         *   MAKE CUSTOM MEAL
         *   DISPENSE ITEM
         *   GET THE CHANGE
         *   KNOW CALORIE AMOUNT
         *   EXIT
         */
        if (e.getActionCommand().equals("SEE ITEM INVENTORY")){
            chooseRVMVendingMethodScreen.addToLog(getRvm().seeInventory());
        } else if (e.getActionCommand().equals("PAY")){
            // Show a dialog to get the payment input from the user
            String paymentString = JOptionPane.showInputDialog("Enter Payment Amount: (>500)");

            // Check if the user pressed Cancel or closed the dialog
            if (paymentString != null) {
                try {
                    // Parse the input as an integer (you can change this based on your requirements)
                    int payment = Integer.parseInt(paymentString);

                    // Call the method to handle payment with the obtained value
                    chooseRVMVendingMethodScreen.addToLog(getRvm().handlePayment(payment));
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid integer
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("DISPENSE ITEM")){
            String dispenseItemString = JOptionPane.showInputDialog("Pick Item No. to dispense:");
            if (dispenseItemString != null) {
                try{
                    int itemIndex = Integer.parseInt(dispenseItemString);
                    chooseRVMVendingMethodScreen.addToLog(getRvm().dispenseItem(itemIndex));
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getActionCommand().equals("GET THE CHANGE")){
            String changeString = getRvm().getChange();
            chooseRVMVendingMethodScreen.addToLog(changeString);
            JOptionPane.showMessageDialog(null, changeString, "HERE'S YOUR CHANGE!", JOptionPane.INFORMATION_MESSAGE);

        }else if(e.getActionCommand().equals("KNOW CALORIE AMOUNT")){
            String calorieAmount = getRvm().getCalorieAmount();
            chooseRVMVendingMethodScreen.addToLog(calorieAmount);
            JOptionPane.showMessageDialog(null, calorieAmount, "HERE'S THE CALORIE CONTENT!", JOptionPane.INFORMATION_MESSAGE);
        }else if (e.getActionCommand().equals("EXIT")) {
            chooseRVMVendingMethodScreen.setVisible(false);
            selectRVMFeatureScreen.setVisible(true);
        }
        //chooseRVMVendingMethodScreen.setVisible(false);
    }

    /*
    private void goBackToPreviousScreen(){
        SelectRVMFeatureScreenController selectRVMFeatureScreenController = new SelectRVMFeatureScreenController(getRvm(), selectRVMFeatureScreen);
        chooseRVMVendingMethodScreen.setVisible(false);
        selectRVMFeatureScreen =  new SelectRVMFeatureScreen();
        selectRVMFeatureScreen.setVisible(true);
    }
    */


    private void handleWindowClosing() {
        int confirm;
        chooseRVMVendingMethodScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(chooseRVMVendingMethodScreen,
                "Are you sure you want to exit Vending Machine Simulator?", "Quit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            chooseRVMVendingMethodScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chooseRVMVendingMethodScreen.setVisible(false);
        }
    }


    @Override
    public void windowClosing(WindowEvent e) {
        int confirm;
        chooseRVMVendingMethodScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(chooseRVMVendingMethodScreen, "Are you sure you want to exit Vending Machine Simulator?",
                "Quit", JOptionPane.YES_NO_OPTION);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                chooseRVMVendingMethodScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chooseRVMVendingMethodScreen.setVisible(false);
                break;
            case JOptionPane.NO_OPTION:
                chooseRVMVendingMethodScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                chooseRVMVendingMethodScreen.setAlwaysOnTop(false);
                break;
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }
    @Override
    public void windowClosed(WindowEvent e) {

    }
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }
    public void windowOpened(WindowEvent e) {

    }
    public VendingMachine getRvm() {
        return rvm;
    }

}
