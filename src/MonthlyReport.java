import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    public ArrayList<MonthlyReportTransaction> reports = new ArrayList<>();
    FileReader fileReader = new FileReader();
    public MonthlyReport(String path) {
        ArrayList<String> lines = fileReader.readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int unitPrice = Integer.parseInt(parts[3]);
            MonthlyReportTransaction report = new MonthlyReportTransaction(itemName, isExpense, quantity, unitPrice);
            reports.add(report);
        }
    }

    public String getTopProduct() {
        HashMap<String, Integer> fregs = new HashMap<>();
        for (MonthlyReportTransaction report : reports) {
           fregs.put(report.itemName,fregs.getOrDefault(report.itemName, 0) + report.quantity);
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
}
