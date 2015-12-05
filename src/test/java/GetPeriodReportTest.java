import model.Month;
import model.report.PeriodReport;
import model.Transaction;
import model.exception.InvalidArgumentException;
import model.persistance.Repository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GetPeriodReportTest {
    Repository repository;
    GetPeriodReport testClass;

    @Before
    public void setUp() {
        repository = mock(Repository.class);
        testClass = new GetPeriodReport(repository);
    }

    @Test
    public void testGetPeriodReport() throws InvalidArgumentException {
        List<Transaction> transactions = new ArrayList<>();
        Month month = new Month(2015, 1);
        Month month2 = new Month(2015, 2);
        Month month3 = new Month(2015, 3);
        when(repository.getTransactionsForMonth(month)).thenReturn(transactions);
        when(repository.getTransactionsForMonth(month2)).thenReturn(transactions);
        when(repository.getTransactionsForMonth(month3)).thenReturn(transactions);

        PeriodReport result = this.testClass.getPeriodReport(month, month3);
        assertNotNull(result);
    }
}
