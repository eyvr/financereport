package model.forecast;

import model.forecast.ForecastMaker;
import model.forecast.ForecastCollection;
import model.report.MonthlyReport;
import model.report.MonthlyReportInterface;
import model.value.Month;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ForecastMakerTest {

    @Test
    public void testGuessPayUpMonth() throws ParseException {
        ForecastMaker testClass = new ForecastMaker(Month.createFromString("2015-01"), 300f);

        Assert.assertEquals("2015-01", testClass.guessPayUpMonth(getReportMock(10f)).getMonth().toString());
        Assert.assertEquals("2015-01", testClass.guessPayUpMonth(getReportMock(3000f)).getMonth().toString());
        Assert.assertEquals("2015-02", testClass.guessPayUpMonth(getReportMock(8f)).getMonth().toString());
        Assert.assertEquals("2015-10", testClass.guessPayUpMonth(getReportMock(1f)).getMonth().toString());
    }

    @Test
    public void testGuessPayUpIfDailyAverageIsNegative() throws ParseException {
        ForecastMaker testClass = new ForecastMaker(Month.createFromString("2015-01"), 300f);

        assertNull(testClass.guessPayUpMonth(getReportMock(-1f)).getMonth());
    }

    @Test
    public void testGuessPayUpMonth2() throws ParseException {
        ForecastMaker testClass = new ForecastMaker(Month.createFromString("2015-01"), 300f);

        ForecastCollection forecastCollection = testClass.guessPayUpMonth(getReportMocks(10f, 1f));

        assertEquals(2, forecastCollection.getForecasts().size());
        assertEquals("2015-01", forecastCollection.getForecasts().get(0).getMonth().toString());
        assertEquals("2015-10", forecastCollection.getForecasts().get(1).getMonth().toString());
        assertEquals(300f, forecastCollection.getTargetAmount(), 0f);
        assertEquals("2015-01", forecastCollection.getStartingMonth().toString());
    }

    private ArrayList<MonthlyReportInterface> getReportMocks(float v, float v1) {
        ArrayList<MonthlyReportInterface> monthlyReports = new ArrayList<>();
        monthlyReports.add(this.getReportMock(v));
        monthlyReports.add(this.getReportMock(v1));
        return monthlyReports;
    }

    private MonthlyReport getReportMock(float dailyAverage) {
        MonthlyReport report = mock(MonthlyReport.class);
        when(report.getDailyAverage()).thenReturn(dailyAverage);
        return report;
    }
}