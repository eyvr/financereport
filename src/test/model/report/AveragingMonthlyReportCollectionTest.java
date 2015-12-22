package model.report;

import model.value.TransactionType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AveragingMonthlyReportCollectionTest {
    private DefaultMonthlyReport monthlyReport1;
    private DefaultMonthlyReport monthlyReport2;
    private TransactionType type;

    @Before
    public void setUp() {
        type = new TransactionType("clothes");

        this.monthlyReport1 = mock(DefaultMonthlyReport.class);
        when(this.monthlyReport1.getTotal()).thenReturn(100f);
        when(this.monthlyReport1.getDailyAverage()).thenReturn(30f);
        when(this.monthlyReport1.getTotal(type)).thenReturn(5f);

        this.monthlyReport2 = mock(DefaultMonthlyReport.class);
        when(this.monthlyReport2.getTotal()).thenReturn(150f);
        when(this.monthlyReport2.getDailyAverage()).thenReturn(20f);
        when(this.monthlyReport2.getTotal(type)).thenReturn(1f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAllowNullArgument() {
        new AveragingMonthlyReportCollection(null);
    }

    @Test
    public void testGetDailyAverage() {
        AveragingMonthlyReportCollection report = new AveragingMonthlyReportCollection(getMockedReports());
        assertEquals(25f, report.getDailyAverage(), 0);
    }

    @Test
    public void testGetMonthLabel() throws Exception {
        AveragingMonthlyReportCollection report = new AveragingMonthlyReportCollection(getMockedReports());
        assertEquals("average", report.getMonthLabel());
    }

    private ArrayList<MonthlyReport> getMockedReports() {
        ArrayList<MonthlyReport> reports = new ArrayList<>();
        reports.add((this.monthlyReport1));
        reports.add((this.monthlyReport2));
        return reports;
    }

    @Test
    public void testGetTotal() throws Exception {

        AveragingMonthlyReportCollection report = new AveragingMonthlyReportCollection(getMockedReports());
        assertEquals(125.00f, report.getTotal(), 0);
    }

    @Test
    public void testGetTotal1() throws Exception {

        AveragingMonthlyReportCollection report = new AveragingMonthlyReportCollection(getMockedReports());
        assertEquals(3.00f, report.getTotal(type), 0);
    }
}