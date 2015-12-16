package model;

import model.report.MonthlyReportInterface;
import model.report.factory.RelevantReportsFactory;
import model.value.Month;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPeriodReportTest {
    RelevantReportsFactory reportFactory;
    GetPeriodReport testClass;

    @Before
    public void setUp() {
        reportFactory = mock(RelevantReportsFactory.class);
        testClass = new GetPeriodReport(reportFactory);
    }

    @Test
    public void testGetPeriodReport() throws SQLException {
        Month month = new Month(2015, 1);
        ArrayList<MonthlyReportInterface> monthlyReports = new ArrayList<>();
        MonthlyReportInterface reportMock = mock(MonthlyReportInterface.class);
        monthlyReports.add(reportMock);
        when(reportFactory.getRelevantReports(month)).thenReturn(monthlyReports);

        List<MonthlyReportInterface> reports = this.testClass.getMonthlyReports(month);
        assertEquals(1, reports.size());
        assertSame(reports.get(0), reportMock);
    }


}
