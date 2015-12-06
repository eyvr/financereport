package model.factory;

import model.exception.InvalidArgumentException;
import model.report.MonthlyReport;
import model.report.MonthlyReportInterface;
import model.value.Month;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RelevantReportsFactoryTest {
    private RelevantReportsFactory testClass;
    private RelevantMonthsFactory monthsFactory;
    private ReportFactory reportFactory;

    @Before
    public void setUp() throws Exception {
        this.monthsFactory = mock(RelevantMonthsFactory.class);
        this.reportFactory = mock(ReportFactory.class);
        this.testClass = new RelevantReportsFactory(this.monthsFactory, this.reportFactory);
    }

    @Test
    public void testGetRelevantReports() throws Exception, InvalidArgumentException {
        Month month = Month.createFromString("2011-01");

        ArrayList<Month> months = new ArrayList<>();
        months.add(Month.createFromString("2010-12"));
        months.add(Month.createFromString("2010-11"));
        when(this.monthsFactory.getRelevantMonths(month)).thenReturn(months);

        MonthlyReport report1 = mock(MonthlyReport.class);
        MonthlyReport report2 = mock(MonthlyReport.class);
        when(this.reportFactory.createMonthlyReport(months.get(0))).thenReturn(report1);
        when(this.reportFactory.createMonthlyReport(months.get(1))).thenReturn(report2);

        ArrayList<MonthlyReportInterface> result = this.testClass.getRelevantReports(month);
        assertEquals(months.size(), result.size());
        assertSame(result.get(0), report1);
        assertSame(result.get(1), report2);
    }
}