package vm_master;

public interface MealCreator {

    // These are abstract methods, implementaion in the SpecialVendingMachine and VendingMachine
    String createMeal(int[] ingrAmount,String customMealName);
    Meal getCustomMeal();
}
