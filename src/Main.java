import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReportEngine reportEngine = new ReportEngine();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String userInput = scanner.next();
            switch (userInput) {
                case "1":
                    reportEngine.loadMonthlyReports();
                    break;
                case "2":
                    reportEngine.loadYearlyReports();
                    break;
                case "3":
                    reportEngine.check();
            }
        }
    }
    private static void printMenu() {
        System.out.println("Введите долбанную цифру!");
    }
}