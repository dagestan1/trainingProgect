import java.util.ArrayList;

public class YearlyReport {
    private final ArrayList<MonthReportSummary> monthReportSummaries = new ArrayList<>();
    private final FileReader fileReader = new FileReader();

    public ArrayList<MonthReportSummary> getMonthReportSummary() {
        return monthReportSummaries;
    }

    public YearlyReport(String path) {
        ArrayList<String> lines = fileReader.readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            MonthReportSummary report = new MonthReportSummary(month, amount, isExpense);
            monthReportSummaries.add(report);
        }
    }

    public int getAverageIncome() {
        int count = 0;
        int sum = 0;
        for (MonthReportSummary monthReportSummary : monthReportSummaries) {
            if (monthReportSummary.isExpense()) {
                count++;
                sum += monthReportSummary.getAmount();
            }
        }
        return sum / count;
    }


}
