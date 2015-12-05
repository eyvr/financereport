package model.report;

import model.Month;
import model.Transaction;
import model.TransactionType;
import model.exception.InvalidArgumentException;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MonthlyReportTest
{
    @Test
    public void testGetMonth() throws InvalidArgumentException {
        TransactionType typeA = TransactionType.CLOTHES;
        TransactionType typeB = TransactionType.ENTERTAINMENT;

        List<Transaction> transactions = new ArrayList<>();
        Month month = new Month(2015, 1);
        transactions.add(getTransaction("", month, typeA, 10.00f));
        transactions.add(getTransaction("", month, typeA, 5.00f));
        transactions.add(getTransaction("", month, typeB, 20.00f));

        MonthlyReport report = new MonthlyReport(month, transactions);
        assertEquals(15.00f, report.getSum(typeA), 0);
    }

    @Test(expected=InvalidArgumentException.class)
    public void testCreatingFromWrongTransactionsThrowsErrors() throws InvalidArgumentException {
        TransactionType typeA = TransactionType.CLOTHES;

        List<Transaction> transactions = new ArrayList<>();
        Month month = new Month(2015, 1);
        transactions.add(getTransaction("", month, typeA, 1f));
        transactions.add(getTransaction("", month, typeA, 1f));
        transactions.add(getTransaction("", new Month(2015, 2), typeA, 1f));

        new MonthlyReport(month, transactions);
    }

    private Transaction getTransaction(String name, Month month, TransactionType type, float amount) {
        return new Transaction(
                name,
                new Date(month.getYear(), month.getMonth(), 1),
                type,
                amount
        );
    }

    @Test
    public void testGetTotal() throws InvalidArgumentException {
        TransactionType typeA = TransactionType.CLOTHES;
        TransactionType typeB = TransactionType.ENTERTAINMENT;

        List<Transaction> transactions = new ArrayList<>();
        Month month = new Month(2015, 1);
        transactions.add(getTransaction("", month, typeA, -10.00f));
        transactions.add(getTransaction("", month, typeA, -5.00f));
        transactions.add(getTransaction("", month, typeB, 20.00f));

        MonthlyReport report = new MonthlyReport(month, transactions);
        assertEquals(5.00f, report.getTotal(), 0);
    }

    @Test
    public void testGetDailyAverage() throws Exception, InvalidArgumentException {
        TransactionType typeB = TransactionType.ENTERTAINMENT;

        List<Transaction> transactions = new ArrayList<>();
        Month month = new Month(2015, 3);
        transactions.add(getTransaction("", month, typeB, 310.00f));

        MonthlyReport report = new MonthlyReport(month, transactions);
        assertEquals(310f/31, report.getDailyAverage(), 0);

        report = new MonthlyReport(new Month(2015, 4), transactions);
        assertEquals(310f/30, report.getDailyAverage(), 0);
    }
}