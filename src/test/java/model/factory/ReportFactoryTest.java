package model.factory;

import model.exception.InvalidArgumentException;
import model.persistance.Repository;
import model.report.MonthlyReport;
import model.value.Month;
import model.value.Transaction;
import model.value.TransactionType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportFactoryTest {

    @Test
    public void testCreateMonthlyReport() throws Exception, InvalidArgumentException {
        Repository repository = mock(Repository.class);
        ReportFactory reportFactory = new ReportFactory(repository);

        Month month = Month.createFromString("2015-01");

        ArrayList<Transaction> list = new ArrayList<>();
        list.add(new Transaction("asd", month.getDate(1), TransactionType.CLOTHES, 12f));
        when(repository.getTransactionsForMonth(month)).thenReturn(list);

        MonthlyReport result = reportFactory.createMonthlyReport(month);

        assertNotNull(result);
        assertEquals(result.getMonthLabel(), month.toString());
    }
}