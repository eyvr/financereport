package model.forecast;

import model.report.MonthlyReport;
import model.value.Month;

import java.util.ArrayList;

public class ForecastMaker {
    private final Month currentMonth;
    private final float targetAmount;

    public ForecastMaker(Month currentMonth, float targetAmount) {
        if (currentMonth == null) {
            throw new IllegalArgumentException("null not allowed");
        }
        this.currentMonth = currentMonth;
        this.targetAmount = targetAmount;
    }

    public Forecast guessPayUpMonth(MonthlyReport report) {
        Month month = currentMonth;
        float dailyAverage = report.getDailyAverage();

        if (dailyAverage <= 0) {
            return new Forecast(report, null);
        }

        float sum = 30 * dailyAverage;
        while (sum < targetAmount) {
            sum += 30 * dailyAverage;
            month = month.getNextMonth();
        }

        return new Forecast(report, month);
    }

    public ForecastCollection guessPayUpMonth(ArrayList<MonthlyReport> reports) {
        ArrayList<Forecast> forecasts = new ArrayList<>();
        for (MonthlyReport report : reports) {
            forecasts.add(this.guessPayUpMonth(report));
        }

        return new ForecastCollection(this.targetAmount, this.currentMonth, forecasts);
    }
}
