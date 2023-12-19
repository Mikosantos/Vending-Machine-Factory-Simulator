package controller;

import gui.*;
import vm_master.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ChooseSVMVendingMethodScreenController implements ActionListener, WindowListener {
    private ChooseSVMVendingMethodScreen chooseSVMVendingMethodScreen;

    private SpecialVendingMachine svm;
    private int[] validIngrAmount;
    private SelectSVMFeatureScreen svmFeatureScreen;

    public ChooseSVMVendingMethodScreenController(SpecialVendingMachine svm, ChooseSVMVendingMethodScreen chooseSVMVendingMethodScreen) {
        this.svm = svm;
        this.chooseSVMVendingMethodScreen = chooseSVMVendingMethodScreen;

        chooseSVMVendingMethodScreen.setVisible(true);
        chooseSVMVendingMethodScreen.setWindowListener(this);
        chooseSVMVendingMethodScreen.setActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SEE ITEM/MEAL INVENTORY")){
            chooseSVMVendingMethodScreen.addToLog(getSvm().seeInventory());

        } else if (e.getActionCommand().equals("PAY")){
            String paymentString = JOptionPane.showInputDialog("Enter Payment Amount: (>500)");

            if (paymentString != null) {
                try {
                    int payment = Integer.parseInt(paymentString);
                    chooseSVMVendingMethodScreen.addToLog(getSvm().handlePayment(payment));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("MAKE CUSTOM MEAL")){
            validIngrAmount = new int[8];
            this.createCustomMealDialog();
        } else if(e.getActionCommand().equals("DISPENSE ITEM")){
            String dispenseItemString = JOptionPane.showInputDialog("Input Item Meal No. to dispense:");
            if (dispenseItemString != null) {
                try{
                    int itemIndex = Integer.parseInt(dispenseItemString);
                    chooseSVMVendingMethodScreen.addToLog(getSvm().dispenseItem(itemIndex));
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid preMade meal NAME!.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (e.getActionCommand().equals("DISPENSE PRE-MADE MEAL")){
            String dispenseItemString = JOptionPane.showInputDialog("Input PreMade Meal No. to dispense:");
            if (dispenseItemString != null) {
                try{
                    int mealIndex = Integer.parseInt(dispenseItemString);
                    chooseSVMVendingMethodScreen.addToLog(getSvm().dispensePreMadeMeal(mealIndex-1));
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid preMade meal NAME!.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("DISPENSE CUSTOM MADE MEAL")){
            String dispenseItemString = JOptionPane.showInputDialog("Pick CustomMeal Name to dispense:");
            if (dispenseItemString != null) {
                try{
                    chooseSVMVendingMethodScreen.addToLog(getSvm().dispenseCustomMadeMeal(dispenseItemString));
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if (e.getActionCommand().equals("GET THE CHANGE")){
            String changeString = getSvm().getChange();
            chooseSVMVendingMethodScreen.addToLog(changeString);
            JOptionPane.showMessageDialog(null, changeString, "HERE'S YOUR CHANGE!", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("KNOW CALORIE AMOUNT")){
            chooseSVMVendingMethodScreen.addToLog(getSvm().getCalorieAmount());
            JOptionPane.showMessageDialog(null, getSvm().getCalorieAmount(), "HERE'S THE CALORIE CONTENT!", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("EXIT")){
            this.goBackToPreviousScreen();
        }
        //chooseSVMVendingMethodScreen.setVisible(false);
    }
    private void goBackToPreviousScreen(){
        SelectSVMFeatureScreenController selectSVMFeatureScreenController = new SelectSVMFeatureScreenController(getSvm(), svmFeatureScreen);
        svmFeatureScreen = new SelectSVMFeatureScreen();
        chooseSVMVendingMethodScreen.setVisible(false);
        svmFeatureScreen.setVisible(true);
    }

    private void createCustomMealDialog() {
        JFrame createMealFrame = new JFrame();
        chooseSVMVendingMethodScreen.addToLog("======================================\n");
        chooseSVMVendingMethodScreen.addToLog("Having ONE instance of EGG, FRIED RICE, and GARLIC, and any x instance/s of ONE MEAT TYPE is the MINIMUM REQUIREMENT for a custom meal!\n" );
        chooseSVMVendingMethodScreen.addToLog("======================================\n");
        createMealFrame.setSize(600, 600);
        createMealFrame.setLayout(new BorderLayout());
        createMealFrame.setBackground(Color.decode("#808080"));

        JPanel ctrCreateMealPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        ctrCreateMealPanel.setBackground(Color.decode("#808080"));

        int[] validIngrAmount = new int[8];
        String[] itemNames = {"Egg", "Fried Rice", "Garlic", "Tapa", "Hotdog", "Tocino", "Longanisa", "Bangus"};

        for (int i = 0; i < 8; i++) {
            String input = JOptionPane.showInputDialog("Enter amount for " + itemNames[i] + ":");

            try {
                int amount = Integer.parseInt(input);
                validIngrAmount[i] = amount;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(createMealFrame, "Invalid input for " + itemNames[i] + ". Setting amount to 0.");
                validIngrAmount[i] = 0;
            }
        }
        String mealName = JOptionPane.showInputDialog("Meal Name:");
        chooseSVMVendingMethodScreen.addToLog(getSvm().createMeal(validIngrAmount, mealName.trim()));
        chooseSVMVendingMethodScreen.addToLog(getSvm().seeInventory());
        getSvm().getCustomMeal().setName(mealName);
        JOptionPane.showMessageDialog(createMealFrame, "Meal Created: " + mealName);

    }



    private void handleWindowClosing() {
        int confirm;
        chooseSVMVendingMethodScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(chooseSVMVendingMethodScreen,
                "Are you sure you want to exit Vending Machine Simulator?", "Quit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            chooseSVMVendingMethodScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chooseSVMVendingMethodScreen.setVisible(false);
        }
    }
    @Override
    public void windowClosing(WindowEvent e) {
        int confirm;
        chooseSVMVendingMethodScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(chooseSVMVendingMethodScreen, "Are you sure you want to exit Vending Machine Simulator?",
                "Quit", JOptionPane.YES_NO_OPTION);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                chooseSVMVendingMethodScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chooseSVMVendingMethodScreen.setVisible(false);
                break;
            case JOptionPane.NO_OPTION:
                chooseSVMVendingMethodScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                chooseSVMVendingMethodScreen.setAlwaysOnTop(false);
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
    public SpecialVendingMachine getSvm(){
        return this.svm;
    }

    public void setSvm(SpecialVendingMachine svm) {
        this.svm = svm;
    }

}
