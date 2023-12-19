package vm_master;
import java.util.ArrayList;

/**
 * This class named VendingMachine represents a vending machine that sells various items.
 * It has slots to store the items tracks inventory and transactions, and handles payments and change.
 * The vending machine has a capacity per slot and a number of slots, and it maintains arrays for slots, current inventory, starting inventory, transactions,and item list.
 *  It also includes a cash box for handling payments and a current transaction object for tracking the current transaction.
 *  The class provides methods for displaying items, collecting payments, dispensing items, producing change, displaying item calorie information, restocking items, setting prices per item, retrieving the total money in the machine, replenishing money denominations, displaying transaction summaries, displaying inventory by last stock, and getting the starting inventory.
 */

public class VendingMachine implements MealCreator{

    private final int CAPACITYPERSLOT;

    public int getNUMSLOTS() {
        return NUMSLOTS;
    }

    private int NUMSLOTS;
    private ArrayList<Slot> slots;
    private ArrayList<Item> slotInventory;
    private ArrayList<Item> startingInventory;  // Storage of initial inventory for pre-made Item/Meals
    private ArrayList<Transaction> transactionList;
    private ArrayList<Transaction> transactionListAfterRestock;
    private ArrayList<Item> itemList;
    private CashBox payments;
    private double totalPayment;
    private double totalProfitAfterRestock = 0.0;

    private Item dispensedItem = null;
    private boolean hasRestocked = false;

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    private Transaction currentTransaction;

    private Denomination[] initiateMoneyForChange;


    public VendingMachine() {
        CAPACITYPERSLOT = 10;
        NUMSLOTS = 8;
        slots = new ArrayList<>();
        startingInventory = new ArrayList<>();
        transactionList = new ArrayList<>();
        transactionListAfterRestock = new ArrayList<>();
        
        
        payments = new CashBox();
        initiateMoneyForChange = new Denomination[]{
                new Denomination(100), new Denomination(50), new Denomination(20),
                new Denomination(10), new Denomination(5), new Denomination(1),
                new Denomination(0.25), new Denomination(0.1), new Denomination(0.05)
        };

        payments.setCashDenominations(initiateMoneyForChange);
        payments.replenishMoneyDenom();
        itemList = new ArrayList<>();

        // Initialize the list of items instances
        itemList.add(new Item("Egg       ", 72, 10, true));
        itemList.add(new Item("Fried Rice", 206, 20, true));
        itemList.add(new Item("Garlic    ", 4, 10, false));
        itemList.add(new Item("Tapa      ", 180, 40, true));
        itemList.add(new Item("Hotdog    ", 150, 35, true));
        itemList.add(new Item("Tocino    ", 190, 35, true));
        itemList.add(new Item("Longanisa ", 220, 35, true));
        itemList.add(new Item("Bangus    ", 250, 45, true));

        // Initialize the slots with their respective inventories
        for (int i = 0; i < NUMSLOTS; i++) {
            slotInventory = new ArrayList<>();
            for (int j = 0; j < CAPACITYPERSLOT; j++) {
                slotInventory.add(itemList.get(i));
            }
            Slot slot = new Slot(CAPACITYPERSLOT,  new ArrayList<>(slotInventory));
            slots.add(slot);
            startingInventory.addAll(slotInventory);
        }
    }

    //----------------------------------------------------------------
    // Vending Features

    /**
     *
     * 1. See Inventory
     * 2. Pay
     * 3. Dispense
     * 4. Get the change
     * 5. See Calorie Amt
     * 6. Exit
     *
     */

    public String seeInventory(){
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("\nCurrent Inventory....\n");

        ArrayList<Slot> slotList = this.getSlots();
        ArrayList<Item> inventoryList;
        int itemCount;
        Item currentItem;


        for (int i = 0; i < slotList.size(); i++) {
            itemCount = 0;
            inventoryList = slotList.get(i).getInventory();

            for (Item item : inventoryList) {
                itemCount++;
            }
            currentItem = inventoryList.get(0);
            resultBuilder.append(i+1).append("\t")
                    .append(currentItem.getName()).append("\t")
                    .append(" - Count:  ")
                    .append(itemCount).append("\t")
                    .append(", Price:  ")
                    .append(currentItem.getPrice())
                    .append("\n");
        }
        return resultBuilder.toString();
    }

    public String handlePayment(int amount) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("\nProcessing Payment....\n");

        try {
            payments.setTotalPayment(amount);
            currentTransaction = new Transaction(null, 0, amount);

            resultBuilder.append("Payment: ").append(payments.getTotalPayment()).append("\nPayment Successful\n\n");
            payments.addPaymentToDenominationList(amount);
            resultBuilder.append(seeCurrentDenomination());
            //System.out.println(seeCurrentDenomination());

        } catch (Exception e) {
            resultBuilder.append("Error: ").append(e.getMessage());
        }

        return resultBuilder.toString();
    }


    public String dispenseItem(int slotNumber) {
        StringBuilder resultBuilder = new StringBuilder();
        try {
            ArrayList<Item> inventory = this.getSlots().get(slotNumber - 1).getInventory();

            if (slotNumber >= 1 && slotNumber <= 8) {
                resultBuilder.append("\n\nDispensing Item....\n");
                dispensedItem = inventory.get(0);
                double itemPrice = dispensedItem.getPrice();

                inventory.remove(0);

                if (!hasRestocked) {
                    // Update the current transaction details
                    this.currentTransaction.setItem(dispensedItem);
                    this.currentTransaction.setPrice(itemPrice);
                    // Update the current transaction list
                    this.transactionList.add(currentTransaction);
                } else {
                    this.currentTransaction.setItem(dispensedItem);
                    this.currentTransaction.setPrice(itemPrice);
                    this.transactionListAfterRestock.add(currentTransaction);
                }

                resultBuilder.append("Dispensed Item  : ").append(dispensedItem.getName()).append("\n");
                resultBuilder.append("Item Price      : ").append(dispensedItem.getPrice()).append("\n");
                // Debugging
                resultBuilder.append(seeCurrentDenomination());
            }

        } catch (Exception e) {
            resultBuilder.append("Error: ").append(e.getMessage());
        }

        return resultBuilder.toString();
    }

    public String seeCurrentDenomination() {
        StringBuilder resultBuilder = new StringBuilder();
        //this.getPayments().getDenominationStat(this.getPayments().getCashDenominations());
        resultBuilder.append("Total [CashBox]: ").append(this.getPayments().getTotalMoney()).append("\n");
        return resultBuilder.toString();
    }


    public String getChange() {
        StringBuilder resultBuilder = new StringBuilder();

        resultBuilder.append("\nProcessing Change....\n");
        String changeInString;
        double change = 0.0;

        if (dispensedItem == null && currentTransaction.getPayment() != 0) {
            resultBuilder.append("No item chosen and you don't pay yet.");
        } else {
            if (dispensedItem == null) { // If the user wants a change without picking an item first
                //No item chosen. You'll get your payment.
                resultBuilder.append("No item chosen. You'll get your payment.\n");
                change = payments.getTotalPayment();
            }
            if (dispensedItem != null) {
                change = payments.getTotalPayment() - currentTransaction.getPrice();
            }

            changeInString = payments.calculateDenominationBreakdown(change);
            payments.updateTotalPayment();
            payments.updateTotalCashBoxMoney(change);
            resultBuilder.append("Change          : ").append(changeInString).append("\n");
            resultBuilder.append(seeCurrentDenomination()); // Incorporate this line
        }

        return resultBuilder.toString();
    }

    public String getCalorieAmount() {
        StringBuilder resultBuilder = new StringBuilder();

        resultBuilder.append("\nProcessing Calorie Amount....\n"); // Incorporate this line
        try {
            double calorieAmtHolder = this.currentTransaction.getItem().getCalorieAmount();
            resultBuilder.append("Calorie Amount  : ").append(calorieAmtHolder).append("\n"); // Incorporate this line
            return resultBuilder.toString();
        } catch (Exception e) {
            resultBuilder.append(e.getMessage()).append("\n");
            return resultBuilder.toString();
        }
    }

    public void setDispensedItem(Item dispensedItem) {
        this.dispensedItem = dispensedItem;
    }


    //----------------------------------------------------------------

    /* Maintenance Features
     *
     * 1. Restocking
     * 2. Change Price
     * 3. Collect profit
     * 4. Replenish Cash
     * 5. Print Transaction Summary
     * 6. Exit
     *
     */

    /**
     * Restocks all slots with items.
     */
    public String restockItems() {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("\nRestocking Items....\n"); // Incorporate this line

        hasRestocked = true;
        totalProfitAfterRestock = 0.0;

        for (int i = 0; i < NUMSLOTS; i++) {
            Slot slot = slots.get(i);
            int currentInventorySize = slot.getInventory().size();
            int remainingCapacity = slot.getSlotCapacity() - currentInventorySize;

            for (int j = 0; j < remainingCapacity; j++) {
                slot.getInventory().add(itemList.get(j));
            }

            resultBuilder.append("Slot ")
                    .append(i + 1)
                    .append(" restocked with ")
                    .append(remainingCapacity)
                    .append(" items.\n");
        }

        // Append the result of seeInventoryString
        resultBuilder.append("\n").append(this.seeInventory());

        return resultBuilder.toString();
    }

    /**
     * Sets the price per item for a specific slot.
     *
     * @param slotIndex the index of the slot
     * @param price     the price to set
     */
    public String setPricePerItem(int slotIndex, double price) {
        int slotValid = slotIndex - 1;
        StringBuilder resultBuilder = new StringBuilder();

        try {
            if (slotIndex >= 0 && slotIndex < slots.size()) { // Check if slotIndex is within the valid range
                if (price >= 0) { // Check if price is non-negative
                    resultBuilder.append("\nChanging Price of Items....\n"); // Include this line in the result

                    for (Item item : this.getSlots().get(slotValid).getInventory()) {
                        item.setPrice(price); // Set the price for each item in the slot
                    }

                    resultBuilder.append(this.seeInventory()); // Append the result of seeInventoryString
                } else {
                    resultBuilder.append("Invalid price. Price must be non-negative.");
                }
            } else {
                resultBuilder.append("Failed to set price. Invalid slot number ").append(slotIndex);
            }
        } catch (Exception e) {
            resultBuilder.append("An error occurred: ").append(e.getMessage());
        }

        return resultBuilder.toString();
    }
    /**
     * Returns the total amount of money in the vending machine.
     *
     * @return the total amount of money
     */
    public String collectProfit() {
        StringBuilder resultBuilder = new StringBuilder();

        try {
            resultBuilder.append("\nCollecting Profit....\n"); // Include this line in the result
            double profit = payments.getTotalMoney();
            resultBuilder.append("Profit: ").append(profit); // Append the profit amount
        } catch (Exception e) {
            resultBuilder.append("An error occurred: ").append(e.getMessage());
        }

        return resultBuilder.toString();
    }
    /**
     * Replenishes the money denomination in the cash box.
     * This method calls the underlying CashBox's method to replenish the money denominations.
     * Money denominations include various bills and coins used for transactions.
     * After replenishing, the cash box will have an updated balance of each denomination.
     */
    public String replenishDenomination() {
        StringBuilder resultBuilder = new StringBuilder();

        try {
            resultBuilder.append(this.seeCurrentDenomination());
            resultBuilder.append("\n\nReplenishing Denominations....\n"); // Include this line in the result
            payments.replenishMoneyDenom();
            payments.setCashDenominations(getInitiateMoneyForChange());

            payments.getDenominationStat(payments.getCashDenominations());
            resultBuilder.append("Total: ").append(payments.getTotalMoney()).append(" (Added 1100)\n");
        } catch (Exception e) {
            resultBuilder.append("An error occurred: ").append(e.getMessage());
        }

        return resultBuilder.toString();
    }

    /**
     * Generates a summary of transactions in the vending machine.
     *
     * This method creates a summary of transactions in the vending machine. It iterates through the list of ALL
     * transactions and constructs a formatted string containing each transaction's item name and price.
     * @return A String containing the formatted summary of transactions
    */
    public String generateTransactionSummary() {
        StringBuilder summaryBuilder = new StringBuilder("\nPrinting Transaction Summary (All Transactions)\nTransaction Summary:\n");
        double total = appendTransactionDetailsToSummary(transactionList, summaryBuilder);

        if (hasRestocked) {
            total += appendTransactionDetailsToSummary(transactionListAfterRestock, summaryBuilder);
        }
        summaryBuilder.append(String.format("\nTotal: %.2f\n", total));
        return summaryBuilder.toString();
    }

    private double appendTransactionDetailsToSummary(ArrayList<Transaction> transactions, StringBuilder summaryBuilder) {
        double total = 0.0;
        for (Transaction transaction : transactions) {
            String productName = transaction.getItem().getName().trim();
            double price = transaction.getPrice();
            summaryBuilder.append(String.format("\t> %s: %.2f\n", productName, price));
            total += price;
        }
        return total;
    }

    /**
     * Generates a summary of the inventory after restocking, including the list of products and the total amount collected.
     * @return A String containing the inventory summary.
     */
    public String generateInventorySummaryAfterRestock() {
        try {
            if (!hasRestocked) {
                return "Inventory has not been restocked yet.";
            }

            double totalProfitAfterRestock = 0.0;
            StringBuilder summaryString = new StringBuilder("Printing Transaction Summary (Starting from the last restocking)\nCurrent Inventory by Last Stock:\n");
            totalProfitAfterRestock = appendTransactionDetailsToSummary(transactionListAfterRestock, summaryString);
            summaryString.append(String.format("\nTotal: %.2f", totalProfitAfterRestock));
            return summaryString.toString();

        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
            return "generateInventorySummaryAfterRestock() ERROR. NullPointerException.";
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e.getMessage());
            return "generateInventorySummaryAfterRestock() ERROR. Unexpected exception.";
        }
    }


    @Override
    public String createMeal(int[] ingrAmount, String customMealName) {
        // Return false here, as the base vending machine doesn't support custom meals
        return null;
    }

    @Override
    public Meal getCustomMeal() {
        // Return null here, as the base vending machine doesn't support custom meals
        return null;
    }

    //----------------------------------------------------------------
    // Getters and Setters
    public ArrayList<Item> getStartingInventory() {
        return startingInventory;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public int getCapacityPerSlot() {
        return CAPACITYPERSLOT;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactionList;
    }

    public ArrayList<Transaction> getTransactionsAfterRestock() {
        return transactionListAfterRestock;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setNumSlots(int numSlots) {
        this.NUMSLOTS = numSlots;
    }

    public CashBox getPayments() {
        return payments;
    }


    public double getTotalPayment() {
        return totalPayment;
    }

    public Item getDispensedItem() {
        return dispensedItem;
    }

    public boolean HasRestocked() {
        return hasRestocked;
    }

    public void addTransactionsAfterRestock(Transaction transaction) {
        this.transactionListAfterRestock.add(transaction);
    }

    public void addTransactions(Transaction transactions) {
        this.transactionList.add(transactions);
    }

    public ArrayList<Item> getSlotInventory() {
        return slotInventory;
    }
    public Denomination[] getInitiateMoneyForChange() {
        return initiateMoneyForChange;
    }



    public static void main(String[] args){


        System.out.println("\nVENDING FEATURES....");

        VendingMachine machine = new VendingMachine();
        /*
         * TODO: (DONE) Vending Features
         */
        System.out.println(machine.seeInventory());
        System.out.println(machine.handlePayment(100));
        System.out.println(machine.dispenseItem(4));
        System.out.println(machine.getChange());
        System.out.println(machine.getCalorieAmount());


        /*
         * TODO: (DONE) Maintenance Features
        */

        System.out.println("\nMAINTENANCE FEATURES....");

        System.out.println(machine.restockItems());                      // For verification
        System.out.println(machine.setPricePerItem(1, 15.00));  // Change price of Fried Rice
        System.out.println(machine.collectProfit());
        System.out.println(machine.replenishDenomination());
        System.out.println(machine.generateTransactionSummary());

        System.out.println("================================================================");
        System.out.println("DISPENSING AFTER RESTOCKING");

        System.out.println(machine.handlePayment(100));
        System.out.println(machine.dispenseItem(4));
        System.out.println(machine.getChange());
        System.out.println(machine.getCalorieAmount());
        System.out.println(machine.generateTransactionSummary());
        System.out.println(machine.generateInventorySummaryAfterRestock());

        System.out.println("================================================================");
    }


}