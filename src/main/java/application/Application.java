package application;

import application.cli.ReportPrinter;
import application.persistance.Repository;
import model.GetPeriodReport;
import model.exception.InvalidArgumentException;
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
        System.out.println("Monthly report");
        Month monthEnd = getPreviousMonth();
        Month monthStart = monthEnd.getPreviousMonth().getPreviousMonth();

        ArrayList<MonthlyReportInterface> monthlyReports = new GetPeriodReport(getRepository()).getMonthlyReports(monthStart, monthEnd);
        monthlyReports.add(new MonthlyReportCollection(monthlyReports));

        System.out.println(new ReportPrinter(getTransactionTypes()).print(monthlyReports));
    }

    private static List<TransactionType> getTransactionTypes() {
        List<TransactionType> transactionTypes = new ArrayList<>(EnumSet.allOf(TransactionType.class));
        transactionTypes.remove(TransactionType.DEBTS);
        return transactionTypes;
    }

    private static void dumpTransactions() throws SQLException {
        Month month = getPreviousMonth();
        System.out.println("Transactions dump: " + month.toString());

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
