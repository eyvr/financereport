package model;

import model.report.MonthlyReportInterface;
import model.forecast.ForecastMaker;
import model.report.factory.RelevantReportsFactory;
import model.forecast.ForecastCollection;
import model.value.Month;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetForecastsTest {

    @Test
    public void testGetForecast() throws Exception {
        Month month = Month.createCurrent();

        ArrayList<MonthlyReportInterface> monthlyReports = new ArrayList<>();

        RelevantReportsFactory relevantReportsFactory = mock(RelevantReportsFactory.class);
        when(relevantReportsFactory.getRelevantReports(month)).thenReturn(monthlyReports);

        ForecastCollection forecastCollection = mock(ForecastCollection.class);
        ForecastMaker forecastMaker = mock(ForecastMaker.class);
        when(forecastMaker.guessPayUpMonth(monthlyReports)).thenReturn(forecastCollection);

        GetForecasts getForecasts = new GetForecasts(relevantReportsFactory, forecastMaker);
        ForecastCollection result = getForecasts.getForecast(month);

        assertNotNull(result);
    }
}