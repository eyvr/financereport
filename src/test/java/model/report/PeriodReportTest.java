package model.report;

import model.Month;
import model.exception.InvalidArgumentException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PeriodReportTest
{
    private MonthlyReport monthlyReport1;
    private MonthlyReport monthlyReport2;

    @Before
    public void setUp() {
        this.monthlyReport1 = mock(MonthlyReport.class);
        when(this.monthlyReport1.getTotal()).thenReturn(100f);
        when(this.monthlyReport1.getDailyAverage()).thenReturn(30f);

        this.monthlyReport2 = mock(MonthlyReport.class);
        when(this.monthlyReport2.getTotal()).thenReturn(150f);
        when(this.monthlyReport2.getDailyAverage()).thenReturn(20f);
    }

    @Test
    public void testGetTotalsAverage() throws InvalidArgumentException {
        List<MonthlyReport> reports = new ArrayList<>();
        reports.add((this.monthlyReport1));
        reports.add((this.monthlyReport2));

        PeriodReport report = new PeriodReport(new Month(2015, 1), new Month(2015,3), reports);
        assertEquals(125.00f, report.getTotalsAverage(), 0);
    }

    @Test
    public void testGetDailyAverage() throws InvalidArgumentException {
        List<MonthlyReport> reports = new ArrayList<>();
        reports.add((this.monthlyReport1));
        reports.add((this.monthlyReport2));

        PeriodReport report = new PeriodReport(new Month(2015, 1), new Month(2015,3), reports);
        assertEquals(25f, report.getDailyAverage(), 0);
    }
}