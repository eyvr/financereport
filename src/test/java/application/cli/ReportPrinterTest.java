package application.cli;

import model.exception.InvalidArgumentException;
import model.report.MonthlyReport;
import model.report.MonthlyReportInterface;
import model.value.Transaction;
import model.value.TransactionType;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ReportPrinterTest {

    private ReportPrinter printer;
    private List<MonthlyReportInterface> reports;

    @Before
    public void setUp() throws Exception, InvalidArgumentException {
        List<MonthlyReportInterface> reports = new ArrayList<>();

        List<TransactionType> types = new ArrayList<>();
        types.add(TransactionType.ENTERTAINMENT);
        types.add(TransactionType.FOOD);

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2015-02-01");
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(getTransaction(date, TransactionType.ENTERTAINMENT, 11f));
        transactions.add(getTransaction(date, TransactionType.FOOD, 2f));

        reports.add(new MonthlyReport(transactions));

        transactions = new ArrayList<>();

        date = new SimpleDateFormat("yyyy-MM-dd").parse("2015-03-01");
        transactions.add(getTransaction(date, TransactionType.CLOTHES, 1f));
        transactions.add(getTransaction(date, TransactionType.ENTERTAINMENT, 1f));
        transactions.add(getTransaction(date, TransactionType.FOOD, 3f));
        reports.add(new MonthlyReport(transactions));

        this.reports = reports;
        this.printer = new ReportPrinter(types);
    }

    @Test
    public void testPrint()
    {
        String result = this.printer.print(this.reports);
        assertEquals(
                "               2015-02  2015-03\n" +
                "entertainment    11.00     1.00\n" +
                "         food     2.00     3.00\n" +
                "        TOTAL    13.00     4.00\n",
                result
        );
    }

    private Transaction getTransaction(Date date, TransactionType type, float amount) {
        return new Transaction(
                "",
                date,
                type,
                amount
        );
    }
}