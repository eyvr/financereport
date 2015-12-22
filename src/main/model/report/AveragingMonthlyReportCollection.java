package model.report;

import model.value.TransactionType;

import java.util.ArrayList;

public class AveragingMonthlyReportCollection implements MonthlyReport {

    private ArrayList<MonthlyReport> reports;

    public AveragingMonthlyReportCollection(ArrayList<MonthlyReport> reports) {
        if (reports == null) {
            throw new IllegalArgumentException("null is not allowed");
        }
        this.reports = new ArrayList<>(reports);
    }

    @Override
    public String getMonthLabel() {
        return "average";
    }

    @Override
    public float getTotal(TransactionType type) {
        float sum = 0;
        for (MonthlyReport report : reports) {
            sum += report.getTotal(type);
        }
        return sum / reports.size();
    }

    @Override
    public float getTotal() {
        float sum = 0f;

        for (MonthlyReport report : reports) {
            sum += report.getTotal();
        }

        return sum / reports.size();
    }

    @Override
    public float getDailyAverage() {
        float sum = 0f;

        for (MonthlyReport report : reports) {
            sum += report.getDailyAverage();
        }

        return sum / reports.size();
    }
}
