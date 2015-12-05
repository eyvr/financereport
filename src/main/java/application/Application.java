package application;

import application.persistance.Repository;
import model.Month;
import model.Transaction;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);
        String option;
        while (true) {
            System.out.println("FinanceReport ----------------------------");
            System.out.println("1) dump transactions");
            System.out.println("2) generate monthly report");
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

    private static void generateMonthlyReport() {
        System.out.println("report");
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
