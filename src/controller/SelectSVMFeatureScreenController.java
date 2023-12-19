package controller;

import gui.*;
import vm_master.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SelectSVMFeatureScreenController implements ActionListener, WindowListener {
    private SpecialVendingMachine specialVM;
    private SelectSVMFeatureScreen selectSVMFeatureScreen;
    private ChooseSVMVendingMethodScreen chooseSVMVendingMethodScreen;
    private ChooseRVMVendingMethodScreenController chooseRVMVendingMethodScreenController;


    public SelectSVMFeatureScreenController(SpecialVendingMachine specialVM, SelectSVMFeatureScreen selectSVMFeatureScreen) {
        this.specialVM  = specialVM;
        this.selectSVMFeatureScreen = selectSVMFeatureScreen;

        selectSVMFeatureScreen.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("1")){
            ChooseSVMVendingMethodScreen chooseRVMVendingMethodScreen = new ChooseSVMVendingMethodScreen();
            ChooseSVMVendingMethodScreenController chooseRVMVendingMethodScreenController = new ChooseSVMVendingMethodScreenController(this.getSpecialVM(), chooseRVMVendingMethodScreen);
        } else if (e.getActionCommand().equals("2")){
            ChooseSVMMaintenanceMethodScreen chooseSVMMaintenanceMethodScreen = new ChooseSVMMaintenanceMethodScreen();
            ChooseSVMMaintenanceMethodScreenController controller = new ChooseSVMMaintenanceMethodScreenController(this.getSpecialVM(),chooseSVMMaintenanceMethodScreen);
        } else if (e.getActionCommand().equals("3")){
            selectSVMFeatureScreen.setVisible(false);
        }
        selectSVMFeatureScreen.setVisible(false);
    }
    private void handleWindowClosing() {
        int confirm;
        this.selectSVMFeatureScreen.setAlwaysOnTop(true);
        confirm = JOptionPane.showConfirmDialog(this.selectSVMFeatureScreen,
                "Are you sure you want to exit Vending Machine Simulator?", "Quit", JOptionPane.YES_NO_OPTION);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                this.selectSVMFeatureScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.selectSVMFeatureScreen.setVisible(false);
                break;
            case JOptionPane.NO_OPTION:
                this.selectSVMFeatureScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                this.selectSVMFeatureScreen.setAlwaysOnTop(false);
                break;
        }
    }

    public SpecialVendingMachine getSpecialVM(){
        return this.specialVM;
    }


    @Override
    public void windowClosing(WindowEvent e) {
        handleWindowClosing();
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
