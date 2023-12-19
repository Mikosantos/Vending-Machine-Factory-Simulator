package controller;

import gui.*;
import vm_master.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SelectVMTypeScreenController implements ActionListener  {
    private SelectVMTypeScreen selectVMTypeScreen; // GUI
    private VMTypeSelector vmTypeSelector; // Model
    private SelectRVMFeatureScreen selectRVMFeatureScreen;
    private SelectRVMFeatureScreenController selectRVMFeatureScreenController;
    private SelectSVMFeatureScreen selectSVMFeatureScreen;
    private SelectSVMFeatureScreenController selectSVMFeatureScreenController;
    private VendingMachine machine;

    /* Construct controller */
    public SelectVMTypeScreenController(VMTypeSelector vmTypeSelector, SelectVMTypeScreen selectVMTypeScreen){
        this.selectVMTypeScreen = selectVMTypeScreen;
        this.vmTypeSelector = vmTypeSelector;
        selectVMTypeScreen.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("RVM")) {
            vmTypeSelector.createRVM();
            /* Gets the regular vending machine instance */
            machine =  vmTypeSelector.getVendingMachine();
            selectRVMFeatureScreen = new SelectRVMFeatureScreen();
            selectRVMFeatureScreenController = new SelectRVMFeatureScreenController(machine, selectRVMFeatureScreen);

        } else if (e.getActionCommand().equals("SVM")) {
            vmTypeSelector.createSVM();
            machine =  vmTypeSelector.getVendingMachine();
            if (machine instanceof SpecialVendingMachine) {
                SpecialVendingMachine specialMachine = (SpecialVendingMachine) machine;
                selectSVMFeatureScreen = new SelectSVMFeatureScreen();
                selectSVMFeatureScreenController = new SelectSVMFeatureScreenController(specialMachine, selectSVMFeatureScreen);
            } else {
                // Show error message
                selectVMTypeScreen.showErrorMessage("No Special Vending Machine Created");
            }
        }
        selectVMTypeScreen.setVisible(false);
    }
}


