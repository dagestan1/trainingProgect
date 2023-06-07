public class YearlyReportTransaction {
    public int month;
    public int amount;
    public boolean isExpense;

    public YearlyReportTransaction(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
