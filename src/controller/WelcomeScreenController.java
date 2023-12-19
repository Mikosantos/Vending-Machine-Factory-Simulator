package controller;

import gui.WelcomeScreen;
import gui.SelectVMTypeScreen;
import vm_master.VMTypeSelector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreenController implements ActionListener {
    private WelcomeScreen welcomeScreen;
    private VMTypeSelector vmTypeSelector;
    private SelectVMTypeScreen selectVMTypeScreen;
    private SelectVMTypeScreenController selectVMTypeScreenController;

    public WelcomeScreenController(VMTypeSelector vmTypeSelector, WelcomeScreen welcomeScreenGUI) {
        this.welcomeScreen = welcomeScreenGUI;
        this.vmTypeSelector = vmTypeSelector;

        welcomeScreen.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("CONTINUE")){
            selectVMTypeScreen = new SelectVMTypeScreen();
            selectVMTypeScreenController = new SelectVMTypeScreenController(vmTypeSelector, selectVMTypeScreen);
        }

        /* Hide current screen dsiplayed. */
        welcomeScreen.setVisible(false);


    }
}
