import model.Month;
import org.junit.Before;
import org.junit.Test;
import model.report.MonthlyReport;
import model.Transaction;
import model.exception.InvalidArgumentException;
import model.persistance.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GetMonthlyReportTest {
    Repository repository;
    GetMonthlyReport testClass;

    @Before
    public void setUp() {
        repository = mock(Repository.class);
        testClass = new GetMonthlyReport(repository);
    }

    @Test
    public void testGetMonthlyReport() throws InvalidArgumentException {
        List<Transaction> transactions = new ArrayList<>();
        when(repository.getTransactionsForMonth(new Month(2015, 1))).thenReturn(transactions);

        MonthlyReport result = this.testClass.getReport(new Month(2015, 1));
        assertNotNull(result);
    }
}
