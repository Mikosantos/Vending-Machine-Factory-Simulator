package vm_master;

/**
 * This class named Denomination  represents a denomination of currency with a specific value and amount. It provides methods to
 *  manipulate and retrieve information about the denomination.
 */
public class Denomination {
    private int amount;
    private double value;

    /** Create an instance of new Denomination with the specified value and initializes the amount to 0.
     *
     * @param  value the value of the denomination
     */
    public Denomination(double value){
        this.value = value;
        this.amount  = 0;
    }

    /**
     * Adds the specified amount to the denomination.
     *
     * @param additionalAmount the amount to add to the denomination
     * @throws IllegalArgumentException if the input amount is negative
     */
    public void addAmount(int additionalAmount) {
        try {
            if (additionalAmount >= 0)
                this.amount += additionalAmount;
            else
                throw new IllegalArgumentException("\t\tadditionalAmount cannot be negative.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Deducts the specified amount from the current amount.
     *
     *  @param deductAmount the amount to deduct
     */
    public void minusAmount(int deductAmount) {
        this.amount -= deductAmount;
    }

    /**
     * Edits the amount of the denomination to the specified amount.
     *
     * @param amount the new amount for the denomination
     * @throws IllegalArgumentException if the input amount is negative
     */
    public void editDenomAmount(int amount){
        if (amount >= 0) {
            this.amount *= amount;
        } else {
            throw new IllegalArgumentException("Input amount must be non-negative.");
        }
    }


    public int getAmount() {
        return amount;
    }

    /**
     * Returns the value of the denomination.
     *
     * @return the value of the denomination
     */
    public double getValue() {
        return value;
    }

    /** Updates the amount of a denomination.
     *
     * @param amount the amount of the denomination
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /** Updates the value of a denomination.
     *
     * @param value the value of the item
     */
    public void setValue(double value) {
        this.value = value;
    }
}
