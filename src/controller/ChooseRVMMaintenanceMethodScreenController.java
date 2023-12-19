package controller;

import gui.*;
import vm_master.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ChooseRVMMaintenanceMethodScreenController implements ActionListener, WindowListener {
    private VendingMachine rvm;
    private ChooseRVMMaintenanceMethodScreen chooseRVMMaintenanceMethodScreen;
    private SelectRVMFeatureScreen selectRVMFeatureScreen;


    public ChooseRVMMaintenanceMethodScreenController(VendingMachine rvm, ChooseRVMMaintenanceMethodScreen chooseRVMMaintenanceMethodScreen) {
        this.rvm = rvm;
        this.chooseRVMMaintenanceMethodScreen = chooseRVMMaintenanceMethodScreen;

        chooseRVMMaintenanceMethodScreen.setVisible(true);
        chooseRVMMaintenanceMethodScreen.setWindowListener(this);
        chooseRVMMaintenanceMethodScreen.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        /*
         *   RESTOCK ITEMS
         *   CHANGE ITEM PRICE
         *   COLLECT PROFIT
         *   REPLENISH CASH
         *   ALL TRANSACTION SUMMARY
         *   TRANSACTION SUMMARY FROM LAST RESTOCKING
         *   EXIT
         *
         */

        if (e.getActionCommand().equals("RESTOCK ITEMS")){
            JOptionPane.showMessageDialog(null, "RESTOCKED SUCCESSFULLY", "RESTOCKING ITEMS", JOptionPane.INFORMATION_MESSAGE);
            chooseRVMMaintenanceMethodScreen.addToLog(getRvm().restockItems());
        } else if (e.getActionCommand().equals("CHANGE ITEM PRICE")){
            int slotIndex = 0;
            double price = 0;
            String slotindexString = JOptionPane.showInputDialog("Enter Slot Number:");
            String priceString = JOptionPane.showInputDialog("Enter Price:");
            if (slotindexString != null) {
                try{
                    slotIndex = Integer.parseInt(slotindexString);
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid integer for price.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (priceString != null) {
                try{
                    price = Integer.parseInt(priceString);
                    if (price <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a positive integer for price.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a positive integer for price.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            chooseRVMMaintenanceMethodScreen.addToLog(getRvm().setPricePerItem(slotIndex, price));
        } else if (e.getActionCommand().equals("COLLECT PROFIT")){
            chooseRVMMaintenanceMethodScreen.addToLog(getRvm().collectProfit() + "\n");
            JOptionPane.showMessageDialog(null, getRvm().collectProfit(), "HERE'S THE TOTAL PROFIT", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("REPLENISH CASH")){
            JOptionPane.showMessageDialog(null, "REPLENISHED CASH SUCCESSFULLY", "REPLENISHING CASH!", JOptionPane.INFORMATION_MESSAGE);
            chooseRVMMaintenanceMethodScreen.addToLog(getRvm().replenishDenomination());
        } else if (e.getActionCommand().equals("ALL TRANSACTION SUMMARY")){
            JOptionPane.showMessageDialog(null, getRvm().generateTransactionSummary(), "GENERATING ALL TRANSACTION SUMMARY!", JOptionPane.INFORMATION_MESSAGE);
            chooseRVMMaintenanceMethodScreen.addToLog(getRvm().generateTransactionSummary());
        } else if (e.getActionCommand().equals("TRANSACTION SUMMARY FROM LAST RESTOCKING")){
            JOptionPane.showMessageDialog(null, getRvm().generateInventorySummaryAfterRestock(), "GENERATING TRANSACTION SUMMARY FROM LAST RESTOCKING!", JOptionPane.INFORMATION_MESSAGE);
            chooseRVMMaintenanceMethodScreen.addToLog("\n======================================\n");
            chooseRVMMaintenanceMethodScreen.addToLog(getRvm().generateInventorySummaryAfterRestock());
            chooseRVMMaintenanceMethodScreen.addToLog("\n======================================\n");
        } else if (e.getActionCommand().equals("EXIT")){
            goBackToPreviousScreen();
        }
    }

    private void goBackToPreviousScreen(){
        SelectRVMFeatureScreenController selectRVMFeatureScreenController = new SelectRVMFeatureScreenController(getRvm(), selectRVMFeatureScreen);
        chooseRVMMaintenanceMethodScreen.setVisible(false);
        selectRVMFeatureScreen =  new SelectRVMFeatureScreen();
        selectRVMFeatureScreen.setVisible(true);
    }

    private void handleWindowClosing() {
        int confirm;
        chooseRVMMaintenanceMethodScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(chooseRVMMaintenanceMethodScreen,
                "Are you sure you want to exit Vending Machine Simulator?", "Quit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            chooseRVMMaintenanceMethodScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chooseRVMMaintenanceMethodScreen.setVisible(false);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int confirm;
        chooseRVMMaintenanceMethodScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(chooseRVMMaintenanceMethodScreen, "Are you sure you want to exit Vending Machine Simulator?",
                "Quit", JOptionPane.YES_NO_OPTION);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                chooseRVMMaintenanceMethodScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chooseRVMMaintenanceMethodScreen.setVisible(false);
                break;
            case JOptionPane.NO_OPTION:
                chooseRVMMaintenanceMethodScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                chooseRVMMaintenanceMethodScreen.setAlwaysOnTop(false);
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
        return this.rvm;
    }

}
