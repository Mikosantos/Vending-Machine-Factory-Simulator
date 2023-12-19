package vm_master;

public class Item {
    private String name;
    private double calorieAmount;
    private double price;
    private final boolean sellable;
    private String prep;


    public Item(String name, double calorieAmount, double price, boolean sellable) {
        this.name = name;
        this.calorieAmount = calorieAmount;
        this.price = price;
        this.sellable = sellable;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public double getCalorieAmount() {
        return calorieAmount;
    }
    public boolean getSellable() {
        return sellable;
    }
    public void setCalorieAmount(double calorieAmount) {
        this.calorieAmount = calorieAmount;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }

    //----------------------------------------------------------------
    public String getPrep() {
        return prep;
    }
    public void setPrep(String prep) {
        this.prep = prep;
    }

}