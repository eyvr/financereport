package model.forecast;

import model.report.MonthlyReport;
import model.value.Month;

/**
 * Forecast based on monthly report. Month can be null if income is less than 0.
 */
public class Forecast {
    private MonthlyReport report;
    private Month month;

    public Forecast(MonthlyReport report, Month month) {
        if (report == null) {
            throw new IllegalArgumentException("null not allowed");
        }

        this.report = report;
        this.month = month;
    }

    public Month getMonth() {
        return month;
    }

    public MonthlyReport getReport() {
        return report;
    }
}
