import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    private final ArrayList<MonthlyReportSummary> transactions = new ArrayList<>();
    private final int sumExpenses;
    private final int sumIncomes;
    FileReader fileReader = new FileReader();

    public MonthlyReport(String path) {
        int sumExpenses = 0;
        int sumIncomes = 0;
        ArrayList<String> lines = fileReader.readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int unitPrice = Integer.parseInt(parts[3]);
            if (isExpense) {
                sumExpenses += quantity * unitPrice;
            } else {
                sumIncomes += quantity * unitPrice;
            }
            MonthlyReportSummary transaction = new MonthlyReportSummary(itemName, isExpense, quantity, unitPrice);
            transactions.add(transaction);
        }
        this.sumExpenses = sumExpenses;
        this.sumIncomes = sumIncomes;
    }

    public String getTopProduct() {
        HashMap<String, Integer> fregs = new HashMap<>();
        for (MonthlyReportSummary report : transactions) {
            fregs.put(report.itemName, fregs.getOrDefault(report.itemName, 0) + report.quantity);
        }
        String maxItem = null;
        for (String itemName : fregs.keySet()) {
            if (maxItem == null) {
                maxItem = itemName;
                continue;
            }
            if (fregs.get(maxItem) < fregs.get(itemName)) {
                maxItem = itemName;
            }
        }
        return maxItem;
    }

    public MonthlyReportSummary getMaxProfit() {
        MonthlyReportSummary transactionWithMaxProfit = null;
        int maxProfit = 0;
        for (MonthlyReportSummary transaction : transactions) {
            if (!transaction.isExpense) {
                int amount = transaction.getTotalPrice();
                if (maxProfit < amount) {
                    maxProfit = amount;
                    transactionWithMaxProfit = transaction;
                }
            }
        }
        return transactionWithMaxProfit;
    }

    public int getSumExpenses() {
        return sumExpenses;
    }

    public int getSumIncomes() {
        return sumIncomes;
    }
}
