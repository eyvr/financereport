package application.cli;

import application.cli.output.CliOutput;
import application.cli.printer.ForecastCollectionPrinter;
import application.cli.printer.ReportPrinter;
import application.persistance.Repository;
import model.GetForecasts;
import model.GetPeriodReport;
import model.exception.InvalidArgumentException;
import model.report.MonthlyReportInterface;
import model.report.Oracle;
import model.report.factory.RelevantMonthsFactory;
import model.report.factory.RelevantReportsFactory;
import model.report.factory.ReportFactory;
import model.value.ForecastCollection;
import model.value.Month;
import model.value.Transaction;
import model.value.TransactionType;

import java.sql.SQLException;
import java.util.*;

public class Application {

    private final CliOutput output;

    public static void main(String[] args) throws SQLException, InvalidArgumentException {
        Application application = new Application();
        application.run();
    }

    public Application()
    {
        output = new CliOutput();
    }

    private void run() throws SQLException, InvalidArgumentException {
        Scanner s = new Scanner(System.in);
        String option;
        while (true) {
            output.writeln("FinanceReport ----------------------------");
            output.writeln("1) monthly reports");
            output.writeln("2) forecasts");
            output.writeln("3) transactions");

            option = s.next();
            if (Objects.equals(option, "1")) {
                generateMonthlyReport();
            }
            if (Objects.equals(option, "2")) {
                generateForecast();
            }
            if (Objects.equals(option, "3")) {
                dumpTransactions();
            }
            if (Objects.equals(option, "q")) {
                return;
            }
        }
    }

    private void generateForecast() throws SQLException, InvalidArgumentException {
        Scanner s = new Scanner(System.in);
        int amount;
        while(true) {
            try {
                output.writeln("Expected savings: ");
                amount = s.nextInt();
                break;
            } catch (InputMismatchException e) {
                output.writeln("Invalid amount. Expected integer.");
                return;
            }
        }
        ForecastCollection forecasts = getGetForecasts(amount).getForecast(getGetPeriodReport().getMonthlyReports(Month.createCurrent()));
        new ForecastCollectionPrinter(new CliOutput()).print(forecasts);
    }

    private void generateMonthlyReport() throws SQLException, InvalidArgumentException {
        ArrayList<MonthlyReportInterface> monthlyReports = getGetPeriodReport().getMonthlyReports(Month.createCurrent());
        new ReportPrinter(new CliOutput(), getTransactionTypes()).print(monthlyReports);
    }

    private GetForecasts getGetForecasts(int amount) {
        return new GetForecasts(new Oracle(Month.createCurrent(), amount));
    }

    private GetPeriodReport getGetPeriodReport() throws SQLException {
        return new GetPeriodReport(getRelevantReportFactory());
    }

    private List<TransactionType> getTransactionTypes() {
        return new ArrayList<>(EnumSet.allOf(TransactionType.class));
    }

    private void dumpTransactions() throws SQLException {
        Month month = Month.createCurrent().getPreviousMonth();
        output.writeln("Transactions dump: " + month.toString());

        Repository repository = getRepository();
        List<Transaction> transactions = repository.getTransactionsForMonth(month);
        for (Transaction transaction : transactions) {
            output.writeln(transaction.toString());
        }
    }

    private static Repository getRepository() throws SQLException {
        return new Repository("localhost", 3306, "root", "password", "transactions");
    }

    private static RelevantReportsFactory getRelevantReportFactory() throws SQLException {
        return new RelevantReportsFactory(new RelevantMonthsFactory(), new ReportFactory(getRepository()));
    }
}
