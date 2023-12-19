package vm_master;

import java.util.ArrayList;


public class SpecialVendingMachine extends VendingMachine{
    private Meal customMeal = new Meal(new ArrayList<>());
    private ArrayList<Item> topsilog_ingredients = new ArrayList<>();
    private ArrayList<Item> hotsilog_ingredients = new ArrayList<>();
    private ArrayList<Item> tocilog_ingredients = new ArrayList<>();
    private ArrayList<Item> longsilog_ingredients = new ArrayList<>();
    private ArrayList<Item> bangusilog_ingredients = new ArrayList<>();
    private ArrayList<Item> ingredients = new ArrayList<>();
    private ArrayList<Item> slotInventory;
    private Slot tempSlot;

    /**
     *  Call the constructor of the superclass to initialize default settings
     */
    public SpecialVendingMachine() {
        super();

        this.setNumSlots(13);

        System.out.println(this.seeInventory());// Debugging

        //Create Topsilog
        topsilog_ingredients.clear();
        topsilog_ingredients.add(new Item(this.getItemList().get(0).getName(), this.getItemList().get(0).getCalorieAmount(), this.getItemList().get(0).getPrice(), true)); // Egg
        topsilog_ingredients.add(new Item(this.getItemList().get(1).getName(), this.getItemList().get(1).getCalorieAmount(), this.getItemList().get(1).getPrice(), true)); // Fried rice
        topsilog_ingredients.add(new Item(this.getItemList().get(3).getName(), this.getItemList().get(3).getCalorieAmount(), this.getItemList().get(3).getPrice(), true)); // Meat
        topsilog_ingredients.add(new Item(this.getItemList().get(3).getName(), this.getItemList().get(3).getCalorieAmount(), this.getItemList().get(3).getPrice(), true)); // Meat
        topsilog_ingredients.add(new Item(this.getItemList().get(2).getName(), this.getItemList().get(2).getCalorieAmount(), this.getItemList().get(2).getPrice(), false)); // Garlic
        // One Item Instance
        Meal topsilog = new Meal("Topsilog  "  , topsilog_ingredients);

        // Create Hotsilog
        hotsilog_ingredients.clear();
        hotsilog_ingredients.add(new Item(this.getItemList().get(0).getName(), this.getItemList().get(0).getCalorieAmount(), this.getItemList().get(0).getPrice(), true)); // Egg
        hotsilog_ingredients.add(new Item(this.getItemList().get(1).getName(), this.getItemList().get(1).getCalorieAmount(), this.getItemList().get(1).getPrice(), true)); // Fried rice
        hotsilog_ingredients.add(new Item(this.getItemList().get(4).getName(), this.getItemList().get(4).getCalorieAmount(), this.getItemList().get(4).getPrice(), true)); // Meat
        hotsilog_ingredients.add(new Item(this.getItemList().get(4).getName(), this.getItemList().get(4).getCalorieAmount(), this.getItemList().get(4).getPrice(), true)); // Meat
        hotsilog_ingredients.add(new Item(this.getItemList().get(2).getName(), this.getItemList().get(2).getCalorieAmount(), this.getItemList().get(2).getPrice(), false)); // Garlic
        Meal hotsilog = new Meal("Hotsilog  "  , hotsilog_ingredients);

        // Create Tocilog
        tocilog_ingredients.clear();
        tocilog_ingredients.add(new Item(this.getItemList().get(0).getName(), this.getItemList().get(0).getCalorieAmount(), this.getItemList().get(0).getPrice(), true)); // Egg
        tocilog_ingredients.add(new Item(this.getItemList().get(1).getName(), this.getItemList().get(1).getCalorieAmount(), this.getItemList().get(1).getPrice(), true)); // Fried rice
        tocilog_ingredients.add(new Item(this.getItemList().get(5).getName(), this.getItemList().get(5).getCalorieAmount(), this.getItemList().get(5).getPrice(), true)); // Meat
        tocilog_ingredients.add(new Item(this.getItemList().get(5).getName(), this.getItemList().get(5).getCalorieAmount(), this.getItemList().get(5).getPrice(), true)); // Meat
        tocilog_ingredients.add(new Item(this.getItemList().get(2).getName(), this.getItemList().get(2).getCalorieAmount(), this.getItemList().get(2).getPrice(), false)); // Garlic
        Meal tocilog = new Meal("Tocilog   "  , tocilog_ingredients);

        // Create Longsilog
        longsilog_ingredients.clear();
        longsilog_ingredients.add(new Item(this.getItemList().get(0).getName(), this.getItemList().get(0).getCalorieAmount(), this.getItemList().get(0).getPrice(), true)); // Egg
        longsilog_ingredients.add(new Item(this.getItemList().get(1).getName(), this.getItemList().get(1).getCalorieAmount(), this.getItemList().get(1).getPrice(), true)); // Fried rice
        longsilog_ingredients.add(new Item(this.getItemList().get(6).getName(), this.getItemList().get(6).getCalorieAmount(), this.getItemList().get(6).getPrice(), true)); // Meat
        longsilog_ingredients.add(new Item(this.getItemList().get(6).getName(), this.getItemList().get(6).getCalorieAmount(), this.getItemList().get(6).getPrice(), true)); // Meat
        longsilog_ingredients.add(new Item(this.getItemList().get(2).getName(), this.getItemList().get(2).getCalorieAmount(), this.getItemList().get(2).getPrice(), false)); // Garlic
        Meal longsilog = new Meal("Longsilog "  , longsilog_ingredients);

        // Create Bangusilog
        bangusilog_ingredients.clear();
        bangusilog_ingredients.add(new Item(this.getItemList().get(0).getName(), this.getItemList().get(0).getCalorieAmount(), this.getItemList().get(0).getPrice(), true)); // Egg
        bangusilog_ingredients.add(new Item(this.getItemList().get(1).getName(), this.getItemList().get(1).getCalorieAmount(), this.getItemList().get(1).getPrice(), true)); // Fried rice
        bangusilog_ingredients.add(new Item(this.getItemList().get(7).getName(), this.getItemList().get(7).getCalorieAmount(), this.getItemList().get(7).getPrice(), true)); // Meat
        bangusilog_ingredients.add(new Item(this.getItemList().get(7).getName(), this.getItemList().get(7).getCalorieAmount(), this.getItemList().get(7).getPrice(), true)); // Meat
        bangusilog_ingredients.add(new Item(this.getItemList().get(2).getName(), this.getItemList().get(2).getCalorieAmount(), this.getItemList().get(2).getPrice(), false)); // Garlic
        Meal bangusilog = new Meal("Bangusilog"  , bangusilog_ingredients);

        // Initialize the list of items instances
        getItemList().add(topsilog);
        getItemList().add(hotsilog);
        getItemList().add(tocilog);
        getItemList().add(longsilog);
        getItemList().add(bangusilog);


        // Initialize the MEAL slot with its respective inventory
        for (int i = 8; i < 13; i++) {
            slotInventory = new ArrayList<>();
            for (int j = 0; j < 10; j++){
                slotInventory.add(getItemList().get(i));
            }
            Slot slot = new Slot(10, new ArrayList<>(slotInventory));
            getSlots().add(slot);
            getStartingInventory().addAll(slotInventory);
        }

        this.seeInventoryMeal();
    }

    public void seeInventoryMeal(){
        System.out.println("================================================================");
        for (int i = 0; i < this.getItemList().size(); i++) {
            System.out.println(i + "\t" + this.getItemList().get(i).getName());

            if (i >= 8 && i<=13){
                Meal meal = (Meal) this.getItemList().get(i);
                ArrayList<Item> mealIngredients = meal.getIngredients();

                for (int j = 0; j < mealIngredients.size(); j++) {
                    System.out.println("\t\t" + mealIngredients.get(j).getName());
                }
            }
        }

        System.out.println("================================================================\n");
    }
    public String dispensePreMadeMeal(int SlotNumber) {
        int slotIndex = SlotNumber;
        double mealPrice = 0.0;
        StringBuilder resultBuilder = new StringBuilder();
        Meal selectedMeal = null;


        Item meal = this.getSlots().get(slotIndex).getInventory().get(0);

        resultBuilder.append("\n\nDispensing Pre-Made Meal " + meal.getName().trim() + ".....\n");

        // Identifies the Meal according to the item inventory list.
        for (Item item : getItemList()) {
            if (item instanceof Meal && item.getName().trim().equals(meal.getName().trim())) {
                selectedMeal = (Meal) item;
                break;
            }
        }

        if (selectedMeal != null) {
            resultBuilder.append("\n\nIngredient count before dispensing PRE-MADE Meal " +  selectedMeal.getName().trim()+ ":\n");
            resultBuilder.append(this.seeInventory());
            this.getCurrentTransaction().setItem(selectedMeal);

            ArrayList<Item> PreMadeMealIngrList = selectedMeal.getIngredients();
            resultBuilder.append("\nRemoving these ingredients....\n");


            for (Item preMadeMealIngrList : PreMadeMealIngrList) {
                mealPrice += preMadeMealIngrList.getPrice();
                String preMadeMealIngrName = preMadeMealIngrList.getName().trim();
                this.removeItemFromSlotInventory(preMadeMealIngrName);
                resultBuilder.append("\t-").append(preMadeMealIngrName).append("\n");
            }
            this.removeItemFromSlotInventory(meal.getName().trim());
            resultBuilder.append("\nIngredients Removed Succesfully!\n");


            if (!this.HasRestocked()) {

                // Update the current transaction details
                this.setDispensedItem(selectedMeal);
                this.getCurrentTransaction().setItem(selectedMeal);
                this.getCurrentTransaction().setPrice(mealPrice);

                this.getItemList().remove(selectedMeal);
                // Update the current transaction list
                this.getTransactions().add(this.getCurrentTransaction());
            } else {
                this.setDispensedItem(selectedMeal);
                this.getCurrentTransaction().setItem(selectedMeal);
                this.getCurrentTransaction().setPrice(mealPrice);
                this.getTransactionsAfterRestock().add(this.getCurrentTransaction());

                this.getItemList().remove(selectedMeal);
            }

            resultBuilder.append("Dispensed Pre-Made Meal: ").append("\t").append(this.getDispensedItem().getName()).append("\n");
            resultBuilder.append("Meal Price: ").append("\t").append(this.getCurrentTransaction().getPrice()).append("\n");
            resultBuilder.append("DISPENSED PRE-MADE MEAL SUCCESSFULLY!\n");
            resultBuilder.append(seeCurrentDenomination());

            resultBuilder.append("\n\nIngredient count after dispensing premade meal:");
            return resultBuilder.toString();

        } else {
            return "Selected Custom Meal is not found!";
        }
    }


    /* Create a meal based on the list  of ingredient's amount set by user.
       Post Assumptions:  ALL ingredients should have amount indicated on the ingrAmount[] list
     */
    @Override
    public String createMeal(int[] ingrAmount, String customMealName){
        StringBuilder resultBuilder = new StringBuilder();

        int i, j;
        double customMealPrice = 0;
        // Egg, Fried rice, Garlic should be present in all CUSTOM meals
        if (ingrAmount[0] != 0 && ingrAmount[1] != 0 && ingrAmount[2] != 0){
            ingredients = new ArrayList<>();
            for (i = 0; i < 8; i++){ // For every i ingredient of that meal
                j = 0;
                while (j != ingrAmount[i]){
                    // Put every ingredient instances in the ingredients[] array
                    ingredients.add(this.getItemList().get(i));
                    customMealPrice += ingredients.get(i).getPrice();
                    j++;
                }
            }
            this.customMeal = new Meal(customMealName, ingredients);

            ArrayList<Item> tempDispensedMealsList = new ArrayList<>();
            tempDispensedMealsList.add(getCustomMeal());
            tempSlot = new Slot(8, tempDispensedMealsList);
            getSlots().add(tempSlot);

            this.getCustomMeal().setPrice(customMealPrice);
            this.getItemList().add(customMeal);

            resultBuilder.append("Dispensed  Meal : ").append(getCustomMeal().getName()).append("\n");
            resultBuilder.append("Meal Price      : ").append(this.getCustomMeal().getPrice()).append("\n");
            resultBuilder.append("DISPENSED SUCCESSFULLY!\n");
            resultBuilder.append(this.seeCurrentDenomination());


            return resultBuilder.toString();
        } else {
            return "Unsuccessful Created"  + customMealName;
        }
    }
    public String dispenseCustomMadeMeal(String mealName) {
        Meal selectedMeal = null;
        StringBuilder dispensingString = new StringBuilder();
        int  customMealPrice = 0;
        int selectedMealIndex = -1; // Initialize with a default value
        for (int i = 0; i < getItemList().size(); i++) {
            Item item = getItemList().get(i);
            if (item instanceof Meal && item.getName().equals(mealName)) {
                selectedMeal = (Meal) item;
                selectedMealIndex = i; // Store the index
                break;
            }
        }
        if (selectedMeal != null) {

            dispensingString.append(selectedMeal.prepareMeal());

            this.getCurrentTransaction().setItem(selectedMeal);

            ArrayList<Item> CustomMealIngrList = selectedMeal.getIngredients();
            CustomMealIngrList.add(this.getItemList().get(selectedMealIndex));

            //ArrayList<Item> tempDispensedMealsList = new ArrayList<>();
            //tempDispensedMealsList.add(selectedMeal);
            //Slot tempSlot = new Slot(8, tempDispensedMealsList);
            //getSlots().add(tempSlot);

            dispensingString.append(this.seeInventory());

            dispensingString.append("\nRemoving these ingredients....\n");

            for (Item customMealIngr : CustomMealIngrList){
                customMealPrice += customMealIngr.getPrice();
                String customMealIngrName = customMealIngr.getName().trim();
                this.removeItemFromSlotInventory(customMealIngrName);
                dispensingString.append("\t-").append(customMealIngrName).append("\n");
            }
            //this.removeItemFromSlotInventory(getCustomMeal().getName());\
            this.setDispensedItem((Item)selectedMeal);
            this.getItemList().remove(selectedMeal);
            getSlots().remove(tempSlot);

            dispensingString.append("\nIngredients Removed Succesfully!\n");
            this.getCurrentTransaction().setPrice(customMealPrice);
            dispensingString.append(this.seeInventory());

            dispensingString.append("\n'" + this.getCustomMeal().getName() + "'" + " DISPENSED SUCCESSFULLY!\n");
            dispensingString.append("Dispensed Custom Made Meal     : ").append(this.getDispensedItem().getName()).append("\n");
            dispensingString.append("Meal Price                     : ").append(this.getCurrentTransaction().getPrice()).append("\n");


            return dispensingString.toString();

        } else {
            return "Meal not found";
        }
    }
    private void removeItemFromSlotInventory(String itemName) {
        ArrayList<Slot> slotList = getSlots();

        for (Slot slot : slotList) {
            Item slotInventoryItem = slot.getInventory().get(0);
            String inventoryName = slotInventoryItem.getName().trim();
            if (inventoryName.equals(itemName)) {
                slot.getInventory().remove(0);
            }
        }
    }
    @Override
    public Meal getCustomMeal(){
        return customMeal;
    }

    public static void main(String[] args) {

        SpecialVendingMachine machine = new SpecialVendingMachine();


        /*
         * TODO: (DISPENSE ITEM) Vending Features
         */
        System.out.println(machine.seeInventory());
        System.out.println(machine.handlePayment(100));
        System.out.println(machine.dispenseItem(4));
        System.out.println(machine.getChange());
        System.out.println(machine.getCalorieAmount());
        System.out.println("================================================================");


        /*
         * TODO: (DISPENSE PRE-MADE MEAL) Vending Features
         */
        System.out.println(machine.seeInventory());
        System.out.println(machine.handlePayment(200));
        System.out.println(machine.dispensePreMadeMeal(9));
        System.out.println(machine.getChange());
        System.out.println(machine.getCalorieAmount());
        System.out.println(machine.seeInventory());
        System.out.println("================================================================");

        System.out.println("\n(CREATE CUSTOM MEAL) Vending Features....");
        String meal_Name  = "customMeal";
        machine.getCustomMeal().setName(meal_Name);
        int[] validIngrAmount = {1, 1, 1, 4, 0, 0, 0, 0};
        System.out.println(machine.createMeal(validIngrAmount, meal_Name));

        System.out.println("================================================================");


        /*
         * TODO: (DISPENSE CUSTOM MEAL) Vending Features
         */
        System.out.println("\n(DISPENSE CUSTOM MEAL) Vending Features....");
        System.out.println(machine.seeInventory());
        System.out.println(machine.handlePayment(400));
        System.out.println(machine.dispenseCustomMadeMeal(machine.getCustomMeal().getName()));
        System.out.println(machine.getChange()); // ASSUMED THAT
        System.out.println(machine.getCalorieAmount());
        System.out.println("================================================================");

        /*
         * TODO: (DONE) Maintenance Features
         */

        System.out.println("\nMAINTENANCE FEATURES....");

        System.out.println(machine.restockItems());                      // For verification
        System.out.println(machine.setPricePerItem(1, 15.00));  // Change price of Fried Rice
        System.out.println(machine.collectProfit());
        System.out.println(machine.replenishDenomination());
        System.out.println(machine.generateTransactionSummary());

        System.out.println("DISPENSING AFTER RESTOCKING");

        System.out.println(machine.handlePayment(300));
        System.out.println(machine.dispenseItem(4));
        System.out.println(machine.getChange());
        System.out.println(machine.getCalorieAmount());
        System.out.println(machine.seeInventory());
        System.out.println(machine.generateTransactionSummary());
        System.out.println(machine.generateInventorySummaryAfterRestock());

        System.out.println("================================================================");

    }
}
