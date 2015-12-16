package model;

import model.forecast.ForecastMaker;
import model.report.factory.RelevantReportsFactory;
import model.forecast.ForecastCollection;
import model.value.Month;

import java.sql.SQLException;

/**
 * Main use case - calculate forecast based on data from previous months.
 */
public class GetForecasts {
    private RelevantReportsFactory relevantReportsFactory;
    private ForecastMaker forecastMaker;

    public GetForecasts(RelevantReportsFactory relevantReportsFactory, ForecastMaker forecastMaker) {
        this.relevantReportsFactory = relevantReportsFactory;
        this.forecastMaker = forecastMaker;
    }

    public ForecastCollection getForecast(Month month) throws SQLException {
        return forecastMaker.guessPayUpMonth(relevantReportsFactory.getRelevantReports(month));
    }
}
