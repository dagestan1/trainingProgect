import java.util.HashMap;
import java.util.Map;

public class ReportEngine {

    private YearlyReport yearlyReport;
    private final Map<Integer, MonthlyReport> monthlyReportsByMonth = new HashMap<>();

    public void loadMonthlyReports() {
        if (monthlyReportsByMonth.size() != 0) {
            System.out.println("Месячные отчеты уже считаны");
            return;
        }
        for (int i = 1; i <= 3; i++) {
            String path = "m.20210" + i + ".csv";
            MonthlyReport report = new MonthlyReport(path);
            monthlyReportsByMonth.put(i, report);
            System.out.println("Считан отчёт " + path);
        }
    }

    public void loadYearlyReports() {

        if (yearlyReport != null) {
            System.out.println("Годовые отчеты уже считаны");
            return;
        }
        final String path = "y.2021.csv";
        this.yearlyReport = new YearlyReport(path);
        System.out.println("Считан отчёт " + path);
    }

    public void check() {
        if (monthlyReportsByMonth.size() == 0 || yearlyReport == null) {
            System.out.println("Проверьте, все ли отчеты считанны, если нет, считайте их.");
            System.out.println();
            return;
        }
        boolean checkedAll = true;
        for (Integer monthNumber : monthlyReportsByMonth.keySet()) {
            checkedAll = checkedAll && checkReports(monthNumber);
        }
    }

    private boolean checkReports(Integer month) {
        final MonthlyReport monthlyReport = monthlyReportsByMonth.get(month);
        for (MonthReportSummary monthReportSummary : yearlyReport.getMonthReportSummary()) {
            if (monthReportSummary.getMonth() == month) {
                if (monthReportSummary.isExpense()) {
                    if (monthlyReport.getSumExpenses() != monthReportSummary.getAmount()) {
                        System.out.println("В " + Month.getByOrder(month).getName() + " несоответствие расходов. Согласно годовому отчёту " + monthReportSummary.getAmount() + " рублей, согласно месячному " + monthlyReport.getSumExpenses() + " рублей");
                        return false;
                    }
                } else {
                    if (monthlyReport.getSumIncomes() != monthReportSummary.getAmount()) {
                        System.out.println("В " + Month.getByOrder(month).getName() + " несоответствие доходов.  Согласно годовому отчёту " + monthReportSummary.getAmount() + " рублей, согласно месячному " + monthlyReport.getSumIncomes() + " рублей");
                        return false;
                    }
                }
            }
        }
            return true;
    }
}
