package vm_master;

import java.util.ArrayList;

public class CashBox {
    private final ArrayList<Integer> nDenominations;
    private Denomination[] cashDenominations;
    private double total;
    private double denomination = 0.0;
    private double totalPayment = 0.0;

    public CashBox() {
        cashDenominations = new Denomination[9];
        cashDenominations[0] = new Denomination(0.05);
        cashDenominations[1] = new Denomination(0.1);
        cashDenominations[2] = new Denomination(0.25);
        cashDenominations[3] = new Denomination(1);
        cashDenominations[4] = new Denomination(5);
        cashDenominations[5] = new Denomination(10);
        cashDenominations[6] = new Denomination(20);
        cashDenominations[7] = new Denomination(50);
        cashDenominations[8] = new Denomination(100);
        nDenominations = new ArrayList<>();
        for (Denomination cashDenomination : cashDenominations) {
            cashDenomination.addAmount(1); // Add 1 to the initial amount
        }
    }

    public void addPaymentToDenominationList(double payment) {
        this.nDenominations.clear();
        for (int i = 0; i<9 ; i++) {
            double denominationValue = cashDenominations[i].getValue();
            // Calculate the number of denominations needed for the current denomination
            int denominationCount = (int) (payment / denominationValue);
            // Add the denomination count to the nDenominations list
            nDenominations.add(denominationCount);

            // Reduce the payment by deducting the value of the current denomination
            payment -= denominationCount * denominationValue;
            cashDenominations[i].addAmount(denominationCount);
        }
    }

    /**
     * Calculates the denomination breakdown for a given payment amount.
     *
     * @param payment the payment amount
     * @return a string representing the denomination breakdown
     */
    public String calculateDenominationBreakdown(double payment) {
        nDenominations.clear();

        for (int i = 0; i<9 ; i++) {
            double denominationValue = cashDenominations[i].getValue();

            // Calculate the number of denominations needed for the current denomination
            int denominationCount = (int) (payment / denominationValue);

            // Add the denomination count to the nDenominations list
            nDenominations.add(denominationCount);

            // Reduce the payment by deducting the value of the current denomination
            payment -= denominationCount * denominationValue;
        }

        // Create a StringBuilder to construct the denomination breakdown string
        StringBuilder breakdownString = new StringBuilder();

        // Iterate through the nDenominations list and construct the breakdown string
        for (int i = 0; i < 9; i++) {
            double denominationValue = cashDenominations[i].getValue();
            int denominationCount = nDenominations.get(i);

            // Append the denomination and its count to the breakdownString if the count is not zero
            if (denominationCount != 0) {
                breakdownString.append(denominationValue).append(": ").append(denominationCount).append(", ");
            }
        }
        if (breakdownString.length() > 0) {
            breakdownString.delete(breakdownString.length() - 2, breakdownString.length());
        }

        return breakdownString.toString();
    }


    public void replenishMoneyDenom() {
        int[] replenishmentAmounts = {5, 4, 10, 10, 10, 20, 40, 100, 200}; // Default: Php 1100.0
        // Add the replenishment amount to each denomination.
        for (int i = 0; i < cashDenominations.length; i++) {
            if (total == 0.0) {
                cashDenominations[i].addAmount(1); // Add 1 to the initial amount
                cashDenominations[i].editDenomAmount(replenishmentAmounts[i]);
            }
            else {
                cashDenominations[i].addAmount(replenishmentAmounts[i]);
            }
        }
    }

    /**
    * Updates the total cash box money based on the given change amount.
    *
    * @param change the change amount to update the cash box money
    */
    public void updateTotalCashBoxMoney(double change){
        for (Denomination cashDenomination : cashDenominations) {
            double denominationValue = cashDenomination.getValue();
            // Calculate the number of denominations needed for the current denomination
            int denominationCount = (int) (change / denominationValue);
            // Subtract the denomination count to the nDenominations list
            cashDenomination.minusAmount(denominationCount);
            // Reduce the payment by deducting the value of the current denomination
            change -= denominationCount * denominationValue;
        }

    }

    public void updateTotalPayment(){
        this.totalPayment = 0.0;
    }
    

    /**
     * Returns the total amount of money in the cash box.
     *
     * @return the total money in the cash box
     */
    public double getTotalMoney() {
        this.total = 0;
        // Calculate the contribution of each denomination to the total amount
        // by multiplying the denomination's amount with its value
        for (Denomination denomination : cashDenominations) {
            this.total += denomination.getAmount() * denomination.getValue();
        }
        return total;
    }

    public double getTotalPayment(){
        return this.totalPayment;
    }
    public double getDenomination() {
        return denomination;
    }
    public double getTotal() {
        return total;
    }
    public void getDenominationStat(Denomination[] initiateMoneyForChange){
        for (Denomination denom : initiateMoneyForChange){
            System.out.println(denom.getValue() + ": "+ denom.getAmount());
        }
    }
    public ArrayList<Integer> getnDenominations() {
        return nDenominations;
    }

    public Denomination[] getCashDenominations() {
        return cashDenominations;
    }

    public void setCashDenominations(Denomination[] cashDenominations) {
        this.cashDenominations = cashDenominations;
    }
    public void setDenomination(double denomination) {
        this.denomination = denomination;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setTotalPayment(double totalPayment){
        this.totalPayment = totalPayment;
    }

    public static void main(String[] args){
        CashBox box  = new CashBox();
        System.out.println("Creating CashBox instance");
        Denomination[] initiateMoneyForChange = {
                new Denomination(100), new Denomination(50), new Denomination(20),
                new Denomination(10), new Denomination(5), new Denomination(1),
                new Denomination(0.25), new Denomination(0.1), new Denomination(0.05)
        };
        System.out.println("Replenishing it with denomination");
        box.setCashDenominations(initiateMoneyForChange);
        box.replenishMoneyDenom();

        box.getDenominationStat(initiateMoneyForChange);
        System.out.println("Total : " + box.getTotalMoney());

    }

}
