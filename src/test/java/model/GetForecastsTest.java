package model;

import model.exception.InvalidArgumentException;
import model.report.MonthlyReportInterface;
import model.report.Oracle;
import model.value.ForecastCollection;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetForecastsTest {

    @Test
    public void testGetForecast() throws Exception, InvalidArgumentException {
        ArrayList<MonthlyReportInterface> monthlyReports = new ArrayList<>();

        ForecastCollection forecastCollection = mock(ForecastCollection.class);
        Oracle oracle = mock(Oracle.class);
        when(oracle.guessPayUpMonth(monthlyReports)).thenReturn(forecastCollection);

        GetForecasts getForecasts = new GetForecasts(oracle);
        ForecastCollection result = getForecasts.getForecast(monthlyReports);

        assertNotNull(result);
    }
}