package model.report;

import model.Month;
import model.Transaction;

import java.util.List;

public class PeriodReport {

    private Month start;
    private Month end;

    private List<MonthlyReport> reports;

    public PeriodReport(Month start, Month end, List<MonthlyReport> reports) {
        this.start = start;
        this.end = end;
        this.reports = reports;
    }

    public float getTotalsAverage() {
        int count = 0;
        float sum = 0f;

        for (MonthlyReport report : reports) {
            sum += report.getTotal();
            count += 1;
        }

        return sum / count;
    }

    public double getDailyAverage() {
        int count = 0;
        float sum = 0f;

        for (MonthlyReport report : reports) {
            sum += report.getDailyAverage();
            count += 1;
        }

        return sum / count;
    }
}
