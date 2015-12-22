package model.report.factory;

import model.report.AveragingMonthlyReportCollection;
import model.report.DefaultMonthlyReport;
import model.report.MonthlyReport;
import model.report.RelevantMonthsChooser;
import model.value.Month;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RelevantReportsFactoryTest {
    private RelevantReportsFactory testClass;
    private RelevantMonthsChooser monthsFactory;
    private ReportFactory reportFactory;

    @Before
    public void setUp() throws Exception {
        this.monthsFactory = mock(RelevantMonthsChooser.class);
        this.reportFactory = mock(ReportFactory.class);
        this.testClass = new RelevantReportsFactory(this.monthsFactory, this.reportFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullsNotAllowed() throws Exception {
        new RelevantReportsFactory(null, mock(ReportFactory.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullsNotAllowed2() throws Exception {
        new RelevantReportsFactory(mock(RelevantMonthsChooser.class), null);
    }

    @Test
    public void testGetRelevantReports() throws Exception {
        Month month = Month.createFromString("2011-01");

        ArrayList<Month> months = new ArrayList<>();
        months.add(Month.createFromString("2010-12"));
        months.add(Month.createFromString("2010-11"));
        when(this.monthsFactory.getRelevantMonths(month)).thenReturn(months);

        DefaultMonthlyReport report1 = mock(DefaultMonthlyReport.class);
        DefaultMonthlyReport report2 = mock(DefaultMonthlyReport.class);
        when(this.reportFactory.createMonthlyReport(months.get(0))).thenReturn(report1);
        when(this.reportFactory.createMonthlyReport(months.get(1))).thenReturn(report2);

        ArrayList<MonthlyReport> result = this.testClass.getRelevantReports(month);
        assertEquals(months.size() + 1, result.size());
        assertSame(result.get(0), report1);
        assertSame(result.get(1), report2);
        assertTrue(result.get(2) instanceof AveragingMonthlyReportCollection);
    }
}