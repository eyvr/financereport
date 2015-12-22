package application.output.printer;

import model.report.DefaultMonthlyReport;
import model.forecast.Forecast;
import model.forecast.ForecastCollection;
import model.value.Month;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ForecastCollectionPrinterTest {

    @Test
    public void testPrint() throws Exception {
        ArrayList<Forecast> forecasts = new ArrayList<>();
        forecasts.add(new Forecast(getMonthlyReportMock("January"), Month.createFromString("2017-01")));
        forecasts.add(new Forecast(getMonthlyReportMock("February"), Month.createFromString("2018-11")));
        ForecastCollection forecastCollection = new ForecastCollection(10000f, Month.createFromString("2015-09"), forecasts);

        FakeOutput output = new FakeOutput();
        new ForecastCollectionPrinter(output).print(forecastCollection);

        assertEquals(
                "Target amount: 10000\n"
              + "\n"
              + "Current:     2015-09\n"
              + "Optimistic:  2017-01 (16 months left, based on January)\n"
              + "Pessimistic: 2018-11 (38 months left, based on February)\n"
              + "\n"
              + "2015 |        C---|\n"
              + "2016 |------------|\n"
              + "2017 |O-----------|\n"
              + "2018 |----------P |\n"
              + "\n",
                output.getOutput()
        );
    }

    private DefaultMonthlyReport getMonthlyReportMock(String label) {
        DefaultMonthlyReport monthlyReport = mock(DefaultMonthlyReport.class);
        when(monthlyReport.getMonthLabel()).thenReturn(label);
        return monthlyReport;
    }

    @Test
    public void testPrintWhenInfinityForecasted() throws Exception {
        ArrayList<Forecast> forecasts = new ArrayList<>();
        forecasts.add(new Forecast(mock(DefaultMonthlyReport.class), null));
        ForecastCollection forecastCollection = new ForecastCollection(10000f, Month.createFromString("2015-09"), forecasts);

        FakeOutput output = new FakeOutput();
        new ForecastCollectionPrinter(output).print(forecastCollection);

        assertEquals(
                "Looks like you won't achieve this goal: 10000.\n",
                output.getOutput()
        );
    }
}