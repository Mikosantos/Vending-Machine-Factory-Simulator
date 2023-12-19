package controller;

import gui.*;
import vm_master.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class ChooseSVMMaintenanceMethodScreenController implements ActionListener, WindowListener{
    private  SpecialVendingMachine svm;
    private ChooseSVMMaintenanceMethodScreen chooseSVMMaintenanceMethodScreen;
    private SelectSVMFeatureScreen svmFeatureScreen;
    public ChooseSVMMaintenanceMethodScreenController(SpecialVendingMachine svm,  ChooseSVMMaintenanceMethodScreen chooseSVMMaintenanceMethodScreen) {
        this.svm = svm;
        this.chooseSVMMaintenanceMethodScreen = chooseSVMMaintenanceMethodScreen;

        chooseSVMMaintenanceMethodScreen.setVisible(true);
        chooseSVMMaintenanceMethodScreen.setWindowListener(this);
        chooseSVMMaintenanceMethodScreen.setActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("RESTOCK ITEMS")) {
            JOptionPane.showMessageDialog(null, "RESTOCKED SUCCESSFULLY", "RESTOCKING ITEMS", JOptionPane.INFORMATION_MESSAGE);
            chooseSVMMaintenanceMethodScreen.addToLog(getSvm().restockItems());
        } else if (e.getActionCommand().equals("CHANGE ITEM PRICE")) {
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
            chooseSVMMaintenanceMethodScreen.addToLog(getSvm().setPricePerItem(slotIndex, price));
        }  else if (e.getActionCommand().equals("COLLECT PROFIT")) {
            chooseSVMMaintenanceMethodScreen.addToLog(getSvm().collectProfit() + "\n");
            JOptionPane.showMessageDialog(null, getSvm().collectProfit(), "HERE'S THE TOTAL PROFIT", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("REPLENISH CASH")) {
            JOptionPane.showMessageDialog(null, "REPLENISHED CASH SUCCESSFULLY", "REPLENISHING CASH!", JOptionPane.INFORMATION_MESSAGE);
            chooseSVMMaintenanceMethodScreen.addToLog(getSvm().replenishDenomination());
        } else if (e.getActionCommand().equals("ALL TRANSACTION SUMMARY")) {
            JOptionPane.showMessageDialog(null, getSvm().generateTransactionSummary(), "GENERATING ALL TRANSACTION SUMMARY!", JOptionPane.INFORMATION_MESSAGE);
            chooseSVMMaintenanceMethodScreen.addToLog(getSvm().generateTransactionSummary());
        } else if (e.getActionCommand().equals("TRANSACTION SUMMARY FROM LAST RESTOCKING")) {
            JOptionPane.showMessageDialog(null, getSvm().generateInventorySummaryAfterRestock(), "GENERATING TRANSACTION SUMMARY FROM LAST RESTOCKING!", JOptionPane.INFORMATION_MESSAGE);
            chooseSVMMaintenanceMethodScreen.addToLog("\n======================================\n");
            chooseSVMMaintenanceMethodScreen.addToLog(getSvm().generateInventorySummaryAfterRestock());
            chooseSVMMaintenanceMethodScreen.addToLog("\n======================================\n");
        } else if (e.getActionCommand().equals("EXIT")) {
            this.goBackToPreviousScreen();
        }
        //chooseSVMMaintenanceMethodScreen.setVisible(false);
    }

    private void goBackToPreviousScreen(){
        chooseSVMMaintenanceMethodScreen.setVisible(false);
        svmFeatureScreen = new SelectSVMFeatureScreen();
        svmFeatureScreen.setVisible(true);
        SelectSVMFeatureScreenController selectSVMFeatureScreenController = new SelectSVMFeatureScreenController(getSvm(), svmFeatureScreen);

    }

    private void handleWindowClosing() {
        int confirm;
        chooseSVMMaintenanceMethodScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(chooseSVMMaintenanceMethodScreen,
                "Are you sure you want to exit Vending Machine Simulator?", "Quit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            chooseSVMMaintenanceMethodScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chooseSVMMaintenanceMethodScreen.setVisible(false);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int confirm;
        chooseSVMMaintenanceMethodScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(chooseSVMMaintenanceMethodScreen, "Are you sure you want to exit Vending Machine Simulator?",
                "Quit", JOptionPane.YES_NO_OPTION);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                chooseSVMMaintenanceMethodScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chooseSVMMaintenanceMethodScreen.setVisible(false);
                break;
            case JOptionPane.NO_OPTION:
                chooseSVMMaintenanceMethodScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                chooseSVMMaintenanceMethodScreen.setAlwaysOnTop(false);
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
}
