package model.forecast;

import model.report.MonthlyReport;
import model.forecast.Forecast;
import model.forecast.ForecastCollection;
import model.value.Month;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class ForecastCollectionTest {

    @Test
    public void testGetOptimistic() throws Exception {
        Forecast f1 = getForecast(new Month(2015, 4));
        Forecast f2 = getForecast(new Month(2015, 8));
        ForecastCollection forecastCollection = getForecastCollection(f1, f2);
        assertSame(f1, forecastCollection.getOptimistic());

        forecastCollection = getForecastCollection(f2, f1);
        assertSame(f1, forecastCollection.getOptimistic());

        Forecast f3 = getForecast(null);
        forecastCollection = getForecastCollection(f2, f3);
        assertSame(f2, forecastCollection.getOptimistic());

        forecastCollection = getForecastCollection(f3, f3);
        assertNull(forecastCollection.getOptimistic());
    }

    @Test
    public void testGetPessimistic() throws Exception {
        Forecast f1 = getForecast(new Month(2015, 4));
        Forecast f2 = getForecast(new Month(2015, 8));
        ForecastCollection forecastCollection = getForecastCollection(f1, f2);
        assertSame(f2, forecastCollection.getPessimistic());

        forecastCollection = getForecastCollection(f2, f1);
        assertSame(f2, forecastCollection.getPessimistic());

        Forecast f3 = getForecast(null);
        forecastCollection = getForecastCollection(f2, f3);
        assertSame(f2, forecastCollection.getPessimistic());

        forecastCollection = getForecastCollection(f3, f3);
        assertNull(forecastCollection.getPessimistic());
    }



    private ForecastCollection getForecastCollection(Forecast forecast, Forecast forecast1) throws ParseException {
        ArrayList<Forecast> forecasts = new ArrayList<>();
        forecasts.add(forecast1);
        forecasts.add(forecast);
        return new ForecastCollection(10000f, Month.createFromString("2015-12"), forecasts);
    }

    private Forecast getForecast(Month month) throws ParseException {
        return new Forecast(mock(MonthlyReport.class), month);
    }
}