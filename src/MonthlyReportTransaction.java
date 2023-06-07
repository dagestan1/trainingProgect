public class MonthlyReportTransaction {
    public String itemName;
    public boolean isExpense;
    public int quantity;
    public int unitPrice;

    int getTotalPrice() {
        return unitPrice * quantity;
    }
    public MonthlyReportTransaction(String itemName, boolean isExpense, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;



        MonthlyReportTransaction getMaxProfit() {
            MonthlyReportTransaction transactionWithMaxProfit = null;
            int maxProfit = 0;
            for (MonthlyReportTransaction transaction : monthlyReportTransactions) {
                if (!transaction.isExpense) {
                    int amount = transaction.getTotalPrice();
                    if(maxProfit < amount) {
                        maxProfit = amount;
                        transactionWithMaxProfit = transaction;
                    }
                }
            }
            return transactionWithMaxProfit;
        }
    }
}


