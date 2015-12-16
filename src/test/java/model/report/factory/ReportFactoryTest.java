package model.report.factory;

import model.exception.InvalidArgumentException;
import model.persistance.Repository;
import model.report.MonthlyReport;
import model.value.Month;
import model.value.Transaction;
import model.value.TransactionType;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportFactoryTest {

    @Test
    public void testCreateMonthlyReport() throws Exception, InvalidArgumentException {
        Repository repository = mock(Repository.class);
        ReportFactory reportFactory = new ReportFactory(repository);

        Month month = Month.createFromString("2015-01");

        ArrayList<Transaction> list = new ArrayList<>();
        list.add(new Transaction("asd", new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-01"), TransactionType.CLOTHES, 12f));
        when(repository.getTransactionsForMonth(month)).thenReturn(list);

        MonthlyReport result = reportFactory.createMonthlyReport(month);

        assertNotNull(result);
        assertEquals(result.getMonthLabel(), month.toString());
    }
}