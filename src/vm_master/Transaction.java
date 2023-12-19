package vm_master;

/**
 * This class named Transaction represents a transaction that includes an item and its price. It provides methods to access and
 * retrieve information about the item and its associated price. A transaction is created by specifying the item being
 * transacted and its price. The item can be obtained using the getItem() method, which returns the item object
 * associated with the transaction. The price of the item in the transaction can be retrieved using the getPrice() method,
 * which returns the price value as a double. Overall, the Transaction class facilitates the management and retrieval of
 * transaction details within a vending machine system or any other relevant context.
 */
public class Transaction {
    private Item item;
    private double price;
    private String itemName;
    private int payment;

    /**
     * Creates a new transaction with the specified item and price.
     *
     * @param item the item being transacted
     * @param price the price of the item
     */
    Transaction(Item item, double price) {
        this.item = item;
        this.price = price;
    }

    Transaction(String itemName, double price, int payment) {
        this.itemName = itemName;
        this.price = price;
        this.payment  = payment;
    }

    /**
     * Returns the item associated with this transaction.
     *
     * @return the item of the transaction
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the price of the item in this transaction.
     *
     * @return the price of the transaction
     */
    public double getPrice() {
        return price;
    }
    public int getPayment(){
        return  payment;
    }

   /** Updates the price on item of the transaction.
    *
    * @param price the price of the item
    */
    public void setPrice(double price){
        this.price = price;
    }
    
   /** Updates the item of the transaction.
     *
     * @param item  the item of the item
     */
     public void setItem(Item item){
        this.item = item;
    }

    public void setPayment(int payment){
         this.payment = payment;
    }

}
