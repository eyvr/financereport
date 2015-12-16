package model.report;

import model.exception.InvalidArgumentException;
import model.value.Month;
import model.value.Transaction;
import model.value.TransactionType;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MonthlyReportTest
{
    @Test
    public void testGetMonth() throws InvalidArgumentException, ParseException {
        TransactionType typeA = TransactionType.CLOTHES;
        TransactionType typeB = TransactionType.ENTERTAINMENT;

        List<Transaction> transactions = new ArrayList<>();
        Month month = new Month(2015, 1);
        transactions.add(getTransaction(month, typeA, 10.00f));
        transactions.add(getTransaction(month, typeA, 5.00f));
        transactions.add(getTransaction(month, typeB, 20.00f));

        MonthlyReport report = new MonthlyReport(transactions);
        assertEquals(15.00f, report.getTotal(typeA), 0);
    }

    @Test(expected=InvalidArgumentException.class)
    public void testCreatingFromWrongTransactionsThrowsErrors() throws InvalidArgumentException, ParseException {
        TransactionType typeA = TransactionType.CLOTHES;

        List<Transaction> transactions = new ArrayList<>();
        Month month = new Month(2015, 1);
        transactions.add(getTransaction(month, typeA, 1f));
        transactions.add(getTransaction(month, typeA, 1f));
        transactions.add(getTransaction(new Month(2015, 2), typeA, 1f));

        new MonthlyReport(transactions);
    }

    private Transaction getTransaction(Month month, TransactionType type, float amount) throws ParseException {
        return new Transaction(
                "",
                new SimpleDateFormat("yyyy-MM").parse(month.toString()),
                type,
                amount
        );
    }

    @Test
    public void testGetTotal() throws InvalidArgumentException, ParseException {
        TransactionType typeA = TransactionType.CLOTHES;
        TransactionType typeB = TransactionType.ENTERTAINMENT;

        List<Transaction> transactions = new ArrayList<>();
        Month month = new Month(2015, 1);
        transactions.add(getTransaction(month, typeA, -10.00f));
        transactions.add(getTransaction(month, typeA, -5.00f));
        transactions.add(getTransaction( month, typeB, 20.00f));

        MonthlyReport report = new MonthlyReport(transactions);
        assertEquals(5.00f, report.getTotal(), 0);
    }

    @Test
    public void testGetDailyAverage() throws Exception, InvalidArgumentException {
        TransactionType typeB = TransactionType.ENTERTAINMENT;

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(getTransaction(new Month(2015, 3), typeB, 310.00f));

        MonthlyReport report = new MonthlyReport(transactions);
        assertEquals(310f/31, report.getDailyAverage(), 0);

        transactions = new ArrayList<>();
        transactions.add(getTransaction(new Month(2015, 4), typeB, 300.00f));

        report = new MonthlyReport(transactions);
        assertEquals(300f/30, report.getDailyAverage(), 0);
    }
}