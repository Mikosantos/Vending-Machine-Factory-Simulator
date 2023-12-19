
package vm_master;

import java.util.ArrayList;

/**
 * The Meal class represents a meal item that can be composed of multiple ingredients.
 * It extends the Item class and contains lists of ingredients and meal preparations.
 */
public class Meal extends Item{
    private String name;
    private ArrayList<Item> ingredients;
    private ArrayList<String> mealPrep;

    public Meal(ArrayList<Item> ingredients){
        super("Meal", 0, 0, true);
        this.ingredients = ingredients;

        double sumCal = 0, sumPrice = 0;
        for (Item i : this.ingredients){
            sumCal += i.getCalorieAmount();
            sumPrice += i.getPrice();
        }
        this.setCalorieAmount(sumCal);
        this.setPrice(sumPrice);
        //TODO! prepareMeal();
    }

    /**
     * Constructs a Meal object with the given name and list of ingredients.
     * The calorie amount and price will be automatically calculated based on the ingredients.
     *
     * @param name        The name of the meal.
     * @param ingredients The list of ingredients that make up the meal.
    */
    public Meal(String name, ArrayList<Item> ingredients){
        super(name, 0, 0, true);
        this.ingredients = ingredients;

        double sumCal = 0, sumPrice = 0;
        for (int i = 0; i < ingredients.size(); i++){
            sumCal += ingredients.get(i).getCalorieAmount();
            sumPrice += ingredients.get(i).getPrice();
        }
        this.setCalorieAmount(sumCal);
        this.setPrice(sumPrice);

    }

    public String prepareMeal() {
        StringBuilder preparationSteps = new StringBuilder();

        mealPrep = new ArrayList<>();
        preparationSteps.append("\n");
        mealPrep.add("Preparing " + this.getName());
        preparationSteps.append("PREPARING CUSTOM MEAL......" + "\n\n");

        mealPrep.add("Placing egg on the plate...");
        preparationSteps.append("Placing egg on the plate...\n");

        mealPrep.add("Serving fried rice (sinangag)...");
        preparationSteps.append("Serving fried rice (sinangag)...\n");

        for (Item ingredient : ingredients) {
            if (!ingredient.getName().equals("Egg") && !ingredient.getName().equals("Fried rice")) {
                mealPrep.add("Adding " + ingredient.getName() + "...");
                preparationSteps.append("Adding " + ingredient.getName().trim() + "...\n");
            }
        }

        mealPrep.add(this.getName() + " finished!");
        preparationSteps.append(this.getName() + " finished!\n");

        return preparationSteps.toString();
    }


    /**
     * Returns the list of ingredients that make up the meal.
     *
     * @return The list of ingredients.
     */
    public ArrayList<Item> getIngredients(){
        return ingredients;
    }


    public void setName(String name){
        this.name = name;
    }

    public static void main(String[] args) throws Exception{

        // TODO: (DONE) CUSTOM MEAL
        SpecialVendingMachine svm = new SpecialVendingMachine();

        ArrayList<Item> mealIngredients = new ArrayList<>();
        mealIngredients.add(svm.getSlots().get(0).getInventory().get(0));
        mealIngredients.add(svm.getSlots().get(1).getInventory().get(0));
        mealIngredients.add(svm.getSlots().get(2).getInventory().get(0));
        mealIngredients.add(svm.getSlots().get(3).getInventory().get(0));

        Meal newMeal = new Meal("CustomMeal1", mealIngredients);
        newMeal.prepareMeal();
        System.out.println(newMeal.getName());

    }
}