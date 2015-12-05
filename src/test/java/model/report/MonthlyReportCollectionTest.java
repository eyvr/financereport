package model.report;

import model.value.Month;
import model.exception.InvalidArgumentException;
import model.value.TransactionType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MonthlyReportCollectionTest {
    private MonthlyReport monthlyReport1;
    private MonthlyReport monthlyReport2;

    @Before
    public void setUp() {
        this.monthlyReport1 = mock(MonthlyReport.class);
        when(this.monthlyReport1.getTotal()).thenReturn(100f);
        when(this.monthlyReport1.getDailyAverage()).thenReturn(30f);
        when(this.monthlyReport1.getTotal(TransactionType.CLOTHES)).thenReturn(5f);

        this.monthlyReport2 = mock(MonthlyReport.class);
        when(this.monthlyReport2.getTotal()).thenReturn(150f);
        when(this.monthlyReport2.getDailyAverage()).thenReturn(20f);
        when(this.monthlyReport2.getTotal(TransactionType.CLOTHES)).thenReturn(1f);
    }

    @Test
    public void testGetDailyAverage() throws InvalidArgumentException {
        MonthlyReportCollection report = new MonthlyReportCollection(getMockedReports());
        assertEquals(25f, report.getDailyAverage(), 0);
    }

    @Test
    public void testGetMonthLabel() throws Exception {
        MonthlyReportCollection report = new MonthlyReportCollection(getMockedReports());
        assertEquals("average", report.getMonthLabel());
    }

    private ArrayList<MonthlyReportInterface> getMockedReports() {
        ArrayList<MonthlyReportInterface> reports = new ArrayList<>();
        reports.add((this.monthlyReport1));
        reports.add((this.monthlyReport2));
        return reports;
    }

    @Test
    public void testGetTotal() throws Exception {

        MonthlyReportCollection report = new MonthlyReportCollection(getMockedReports());
        assertEquals(125.00f, report.getTotal(), 0);
    }

    @Test
    public void testGetTotal1() throws Exception {

        MonthlyReportCollection report = new MonthlyReportCollection(getMockedReports());
        assertEquals(3.00f, report.getTotal(TransactionType.CLOTHES), 0);
    }
}