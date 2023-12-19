package vm_master;
import gui.WelcomeScreen;
import controller.WelcomeScreenController;

/**
 * The Main class represents the entry point of the Vending Machine application.
 */
public class VendingMachineFactory {

    public static void main(String[] args) {
        VMTypeSelector vmTypeSelector = new VMTypeSelector();
        WelcomeScreen welcomeScreenGUI = new WelcomeScreen();
        WelcomeScreenController welcomeScreenController = new WelcomeScreenController(vmTypeSelector, welcomeScreenGUI);
    }
}
