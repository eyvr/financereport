package model.value;

import model.report.MonthlyReportInterface;

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
