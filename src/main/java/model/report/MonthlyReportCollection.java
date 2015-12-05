package model.report;

import model.value.TransactionType;

import java.util.ArrayList;

public class MonthlyReportCollection implements MonthlyReportInterface{

    private ArrayList<MonthlyReportInterface> reports;

    public MonthlyReportCollection(ArrayList<MonthlyReportInterface> reports) {
        this.reports = (ArrayList<MonthlyReportInterface>) reports.clone();
    }

    @Override
    public String getMonthLabel() {
        return "average";
    }

    @Override
    public float getTotal(TransactionType type) {
        float sum = 0;
        for (MonthlyReportInterface report : reports) {
            sum += report.getTotal(type);
        }
        return sum / reports.size();
    }

    @Override
    public float getTotal() {
        float sum = 0f;

        for (MonthlyReportInterface report : reports) {
            sum += report.getTotal();
        }

        return sum / reports.size();
    }

    @Override
    public float getDailyAverage() {
        float sum = 0f;

        for (MonthlyReportInterface report : reports) {
            sum += report.getDailyAverage();
        }

        return sum / reports.size();
    }
}
