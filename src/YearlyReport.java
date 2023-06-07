import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    public ArrayList<YearlyReportTransaction> YearlyReportTransactions = new ArrayList<>();
    FileReader fileReader = new FileReader();
    public YearlyReport(String path) {
        ArrayList<String> lines = fileReader.readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

        YearlyReportTransaction report = new YearlyReportTransaction(month, amount, isExpense);
        YearlyReportTransactions.add(report);
        }

    }
    public int getAverageIncome() {
        int count = 0;
        int sum = 0;
        for (YearlyReportTransaction yearlyReportTransaction : YearlyReportTransactions) {
            if(yearlyReportTransaction.isExpense) {
            count++;
            sum = yearlyReportTransaction.amount;
        }
        

        }
        return sum / count;
    }


}
