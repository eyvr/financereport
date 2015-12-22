package model.forecast;

import model.report.MonthlyReport;
import model.value.Month;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ForecastTest {
    @Test(expected = IllegalArgumentException.class)
    public void testNullReport()
    {
        new Forecast(null, mock(Month.class));
    }

    @Test
    public void testNullMonth()
    {
        assertNull(new Forecast(mock(MonthlyReport.class), null).getMonth());
    }
}