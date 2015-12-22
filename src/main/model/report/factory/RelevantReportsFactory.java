package model.report.factory;

import model.report.AveragingMonthlyReportCollection;
import model.report.MonthlyReport;
import model.report.RelevantMonthsChooser;
import model.value.Month;

import java.sql.SQLException;
import java.util.ArrayList;

public class RelevantReportsFactory {
    private RelevantMonthsChooser monthsFactory;
    private ReportFactory reportFactory;

    public RelevantReportsFactory(RelevantMonthsChooser monthsFactory, ReportFactory reportFactory) {
        if (monthsFactory == null || reportFactory == null) {
            throw new IllegalArgumentException("nulls not allowed");
        }
        this.monthsFactory = monthsFactory;
        this.reportFactory = reportFactory;
    }

    public ArrayList<MonthlyReport> getRelevantReports(Month targetMonth) throws SQLException {
        ArrayList<Month> relevantMonths = this.monthsFactory.getRelevantMonths(targetMonth);
        ArrayList<MonthlyReport> result = new ArrayList<>();

        for (Month month : relevantMonths) {
            result.add(this.reportFactory.createMonthlyReport(month));
        }
        result.add(new AveragingMonthlyReportCollection(result));
        return result;
    }
}
