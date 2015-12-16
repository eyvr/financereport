package application.cli;

import application.ServiceFactory;
import application.cli.output.CliOutput;
import model.report.MonthlyReportInterface;
import model.forecast.ForecastCollection;
import model.value.Month;

import java.sql.SQLException;
import java.util.*;

public class Application {

    private final ServiceFactory serviceFactory;
    private final CliOutput output;

    public Application() throws SQLException {
        output = new CliOutput();
        serviceFactory = new ServiceFactory(output);
    }

    public static void main(String[] args) throws SQLException {
        Application application = new Application();
        application.run();
    }

    private void run() throws SQLException {
        Scanner s = new Scanner(System.in);
        String option;
        while (true) {
            output.println("FinanceReport ----------------------------");
            output.println("1) monthly reports");
            output.println("2) forecasts");

            option = s.next();
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
    }

    private void generateForecast() throws SQLException {
        Scanner s = new Scanner(System.in);
        int amount;
        while (true) {
            try {
                output.println("Target savings amount: ");
                amount = s.nextInt();
                break;
            } catch (InputMismatchException e) {
                output.println("Invalid amount. Expected integer.");
            }
        }

        ForecastCollection forecasts = serviceFactory.getGetForecasts(amount).getForecast(Month.createCurrent());
        serviceFactory.getForecastCollectionPrinter().print(forecasts);
    }

    private void generateMonthlyReport() throws SQLException {
        ArrayList<MonthlyReportInterface> monthlyReports = serviceFactory.getGetPeriodReport().getMonthlyReports(Month.createCurrent());
        serviceFactory.getReportPrinter().print(monthlyReports);
    }

}
