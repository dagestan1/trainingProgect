import java.util.HashMap;

public class ReportEngine {

    YearlyReport yearlyReport;
    HashMap<Integer, MonthlyReport> monthlyReportsByMonth;

    MonthlyReport monthlyReport = new MonthlyReport(path);

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

        if(yearlyReport != null) {
            System.out.println("Годовые отчеты уже считаны");
            return;
        }
        String path = "y.2021.csv";
        YearlyReport report = new YearlyReport(path);
        yearlyReport = report;
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
            checkedAll = checkedAll && checkReports(monthNumber, monthlyReportsByMonth.get(monthNumber));
        }

    private boolean checkReports(Integer month, MonthlyReport monthlyReport) {
            for(YearlyReportTransaction transaction : yearlyReport.YearlyReportTransactions) {
                if (transaction.month == month) {
                    if (transaction.isExpense) {
                        if (monthlyReport.getSumExpenses() != transaction.amount) {
                            System.out.println(monthHelper.getMonthByNumber(month) + " : несоответствие расходов");
                            return false;
                        }
                    } else if (monthlyReport.getTopProduct() != transaction.amount) {
                        System.out.println(monthHelper.getMonthByNumber(month) + " : несоответствие доходов");
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
