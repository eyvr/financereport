import model.GetPeriodReport;
import model.factory.RelevantReportsFactory;
import model.factory.ReportFactory;
import model.report.AveragingMonthlyReportCollection;
import model.report.MonthlyReport;
import model.report.MonthlyReportInterface;
import model.value.Month;
import model.value.Transaction;
import model.exception.InvalidArgumentException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GetMonthlyReportTest {
    RelevantReportsFactory reportFactory;
    GetPeriodReport testClass;

    @Before
    public void setUp() {
        reportFactory = mock(RelevantReportsFactory.class);
        testClass = new GetPeriodReport(reportFactory);
    }

    @Test
    public void testGetPeriodReport() throws InvalidArgumentException {
        Month month = new Month(2015, 1);
        ArrayList<MonthlyReportInterface> monthlyReports = new ArrayList<>();
        monthlyReports.add(mock(MonthlyReportInterface.class));
        when(reportFactory.getRelevantReports(month)).thenReturn(monthlyReports);

        List<MonthlyReportInterface> reports = this.testClass.getMonthlyReports(month);
        assertEquals(2, reports.size());
        assertTrue(reports.get(1) instanceof AveragingMonthlyReportCollection);
    }
}
