package model.forecast;

import model.report.MonthlyReportInterface;
import model.value.Month;

public class Forecast {
    private MonthlyReportInterface report;
    private Month month;

    public Forecast(MonthlyReportInterface report, Month month) {
        this.report = report;
        this.month = month;
    }

    public Month getMonth() {
        return month;
    }

    public MonthlyReportInterface getReport() {
        return report;
    }
}
