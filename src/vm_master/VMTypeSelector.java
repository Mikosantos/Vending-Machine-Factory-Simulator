package vm_master;

/**
 * The VendingMachineFactory class is responsible for creating instances of VendingMachine and SpecialVendingMachine
 * and providing access to the created VendingMachine instance.
 */
public class VMTypeSelector {
    private VendingMachine VM;
    /**
     * Constructs a new VendingMachineFactory.
     * Initializes the VM instance to null.
     */
    public VMTypeSelector(){
        this.VM = null;
    }
    /**
     * Creates a Regular Vending Machine (RVM) instance.
     * Initializes the VM instance to a new VendingMachine object.
     */
    public void createRVM(){
        this.VM = new VendingMachine();
    }
    /**
     * Creates a Special Vending Machine (SVM) instance.
     * Initializes the VM instance to a new SpecialVendingMachine object.
     */
    public void createSVM(){
        this.VM = new SpecialVendingMachine();
    }
    /**
     * Returns the current VendingMachine instance.
     *
     * @return The VendingMachine instance.
     */
    public VendingMachine getVendingMachine(){
        return this.VM;
    }

    public static void main(String[] args){
        VMTypeSelector selector = new VMTypeSelector();
        selector.createRVM();
    }
}
