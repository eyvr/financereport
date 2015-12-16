package model.report;

import model.value.Forecast;
import model.value.ForecastCollection;
import model.value.Month;

import java.util.ArrayList;

public class Oracle {
    private final Month currentMonth;
    private final float targetAmount;

    public Oracle(Month currentMonth, float targetAmount) {
        this.currentMonth = currentMonth;
        this.targetAmount = targetAmount;
    }

    public Forecast guessPayUpMonth(MonthlyReportInterface report) {
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

    public ForecastCollection guessPayUpMonth(ArrayList<MonthlyReportInterface> reports) {
        ArrayList<Forecast> forecasts = new ArrayList<>();
        for (MonthlyReportInterface report : reports) {
            forecasts.add(this.guessPayUpMonth(report));
        }

        return new ForecastCollection(this.targetAmount, this.currentMonth, forecasts);
    }
}
