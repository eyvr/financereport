package application;

import application.output.CliOutput;
import model.report.MonthlyReport;
import model.forecast.ForecastCollection;
import model.value.Month;

import java.sql.SQLException;
import java.util.*;

public class Application {

    private ServiceFactory serviceFactory;
    private CliOutput output;
    private Scanner scanner;

    public Application() {
        output = new CliOutput();
        try {
            serviceFactory = new ServiceFactory(output);
        } catch (SQLException e) {
            output.println("Failed to connect to mysql: " + e.getMessage());
            System.exit(1);
        }
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) throws SQLException {
        Application application = new Application();
        application.run();
    }

    private void run() {
        String option;
        while (true) {
            output.println("FinanceReport ----------------------------");
            output.println("1) monthly reports");
            output.println("2) forecasts");
            output.println("q) exit");

            try {
                option = scanner.next();
                if (Objects.equals(option, "1")) {
                    generateMonthlyReport();
                }
                if (Objects.equals(option, "2")) {
                    generateForecast();
                }
                if (Objects.equals(option, "q")) {
                    return;
                }
            }
            catch (SQLException e) {
                output.println("Error occurred while connecting to mysql: " + e.getMessage());
            }
        }
    }

    private void generateForecast() throws SQLException {
        int amount;
        while (true) {
            try {
                output.println("Target savings amount: ");
                amount = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                output.println("Invalid amount. Expected integer.");
            }
        }

        ForecastCollection forecasts = serviceFactory.getGetForecasts(amount).getForecast(Month.createCurrent());
        serviceFactory.getForecastCollectionPrinter().print(forecasts);
    }

    private void generateMonthlyReport() throws SQLException {
        ArrayList<MonthlyReport> monthlyReports = serviceFactory.getGetPeriodReport().getMonthlyReports(Month.createCurrent());
        serviceFactory.getReportPrinter().print(monthlyReports);
    }

}
