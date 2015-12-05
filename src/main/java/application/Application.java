package application;

import application.cli.ReportPrinter;
import application.persistance.Repository;
import model.GetPeriodReport;
import model.exception.InvalidArgumentException;
import model.report.MonthlyReport;
import model.report.MonthlyReportCollection;
import model.report.MonthlyReportInterface;
import model.value.Month;
import model.value.Transaction;
import model.value.TransactionType;

import java.sql.SQLException;
import java.util.*;

public class Application {
    public static void main(String[] args) throws SQLException, InvalidArgumentException {
        Scanner s = new Scanner(System.in);
        String option;
        while (true) {
            System.out.println("FinanceReport ----------------------------");
            System.out.println("1) show transactions");
            System.out.println("2) show monthly report");
            System.out.println("q) exit");

            option = s.next();
            if (Objects.equals(option, "1")) {
                dumpTransactions();
            }
            if (Objects.equals(option, "2")) {
                generateMonthlyReport();
            }
            if (Objects.equals(option, "q")) {
                return;
            }
        }
    }

    private static void generateMonthlyReport() throws SQLException, InvalidArgumentException {
        Month monthEnd = getPreviousMonth();
        Month monthStart = monthEnd.getPreviousMonth().getPreviousMonth();

        GetPeriodReport getPeriodReport = new GetPeriodReport(getRepository());
        ArrayList<MonthlyReportInterface> monthlyReports = getPeriodReport.getMonthlyReports(monthStart, monthEnd);
        monthlyReports.add(new MonthlyReportCollection(monthlyReports));

        List<TransactionType> transactionTypes = new ArrayList<>(EnumSet.allOf(TransactionType.class));
        transactionTypes.remove(TransactionType.DEBTS);

        ReportPrinter printer = new ReportPrinter(transactionTypes);
        System.out.println(printer.print(monthlyReports));
    }

    private static void dumpTransactions() throws SQLException {
        Month month = getPreviousMonth();
        System.out.println("Transactions " + month.toString());

        Repository repository = getRepository();
        List<Transaction> transactions = repository.getTransactionsForMonth(month);
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }

    private static Month getPreviousMonth() {
        Calendar cal = Calendar.getInstance();
        Month month = new Month(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
        month = month.getPreviousMonth();
        return month;
    }

    private static Repository getRepository() throws SQLException {
        return new Repository("localhost", 3306, "root", "password", "transactions");
    }
}
