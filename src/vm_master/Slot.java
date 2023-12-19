
package vm_master;

import java.util.ArrayList;
/**
 * This class named Slot  represents a slot with a certain capacity to hold items. A slot object is used to manage and track the
 * inventory of items within the slot. It has a specified capacity, indicating the maximum number of items it can hold.
 * The slot's inventory is stored as an ArrayList of Item objects. The Slot class provides methods to retrieve the slot's
 * capacity and inventory.
 */
public class Slot {
    private int slotCapacity;
    ArrayList<Item> inventory;
    
    /**
     * Constructs a slot object with the specified capacity and inventory.
     *
     * @param slotCapacity the maximum number of items the slot can hold
     * @param Inventory the list of items currently stored in the slot
     */
    public Slot(int slotCapacity, ArrayList<Item> Inventory){
        this.slotCapacity = slotCapacity;
        this.inventory = Inventory;
    }
    
    /**
     * Returns the capacity of the slot.
     *
     * @return the maximum number of items the slot can hold
     */
    public int getSlotCapacity() {
        return slotCapacity;
    }
    
    /**
     * Returns the inventory of the slot.
     *
     * @return the list of items currently stored in the slot
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    
    /**
     * Updates the inventory of the vending machine
     * @param inventory the ArrayList of items to set as the inventory
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
    
    /**
     * Updates the slot capacity of the vending machine.
     * @param slotCapacity the capacity per slot to set
     */
    public void setSlotCapacity(int slotCapacity) {
        this.slotCapacity = slotCapacity;
    }

    public static void main(String[] args){

    }
}

