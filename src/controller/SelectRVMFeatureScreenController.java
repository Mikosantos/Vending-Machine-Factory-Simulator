package controller;

import gui.*;
import vm_master.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SelectRVMFeatureScreenController implements ActionListener, WindowListener {
    private VendingMachine regularVM;
    private SelectRVMFeatureScreen selectRVMFeatureScreen;

    private ChooseRVMVendingMethodScreen chooseRVMVendingMethodScreen;
    private ChooseRVMVendingMethodScreenController chooseRVMVendingMethodScreenController;;

    public SelectRVMFeatureScreenController(VendingMachine regularVM, SelectRVMFeatureScreen selectRVMFeatureScreen){
        this.regularVM = regularVM;
        this.selectRVMFeatureScreen = selectRVMFeatureScreen;

        selectRVMFeatureScreen.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("1")){
            ChooseRVMVendingMethodScreen chooseRVMVendingMethodScreen = new ChooseRVMVendingMethodScreen();
            ChooseRVMVendingMethodScreenController chooseRVMVendingMethodScreenController = new ChooseRVMVendingMethodScreenController(this.getRegularVM(), chooseRVMVendingMethodScreen);

        } else if (e.getActionCommand().equals("2")){
            ChooseRVMMaintenanceMethodScreen chooseRVMMaintenanceMethodScreen = new ChooseRVMMaintenanceMethodScreen();
            ChooseRVMMaintenanceMethodScreenController chooseRVMMaintenanceMethodScreenController = new ChooseRVMMaintenanceMethodScreenController(this.getRegularVM(), chooseRVMMaintenanceMethodScreen);
        }
        else if (e.getActionCommand().equals("3")){
            selectRVMFeatureScreen.setVisible(false);
        }
        selectRVMFeatureScreen.setVisible(false);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        handleWindowClosing();
    }
    private void handleWindowClosing() {
        int confirm;
        this.chooseRVMVendingMethodScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(this.chooseRVMVendingMethodScreen,
                "Are you sure you want to exit Vending Machine Simulator?", "Quit", JOptionPane.YES_NO_OPTION);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                this.chooseRVMVendingMethodScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.chooseRVMVendingMethodScreen.setVisible(false);
                break;
            case JOptionPane.NO_OPTION:
                this.chooseRVMVendingMethodScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                this.chooseRVMVendingMethodScreen.setAlwaysOnTop(false);
                break;
        }
    }

    public VendingMachine getRegularVM() {
        return this.regularVM;
    }

    public SelectRVMFeatureScreen getSelectRVMFeatureScreen() {
        return this.selectRVMFeatureScreen;
    }

    public ChooseRVMVendingMethodScreen getChooseRVMVendingMethodScreen() {
        return this.chooseRVMVendingMethodScreen;
    }

    public ChooseRVMVendingMethodScreenController getChooseRVMVendingMethodScreenController() {
        return this.chooseRVMVendingMethodScreenController;
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
    @Override
    public void windowOpened(WindowEvent e) {

    }
}
