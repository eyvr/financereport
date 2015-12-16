package model.report.factory;

import model.report.AveragingMonthlyReportCollection;
import model.report.MonthlyReportInterface;
import model.report.RelevantMonthsChooser;
import model.value.Month;

import java.sql.SQLException;
import java.util.ArrayList;

public class RelevantReportsFactory {
    private RelevantMonthsChooser monthsFactory;
    private ReportFactory reportFactory;

    public RelevantReportsFactory(RelevantMonthsChooser monthsFactory, ReportFactory reportFactory) {
        this.monthsFactory = monthsFactory;
        this.reportFactory = reportFactory;
    }

    public ArrayList<MonthlyReportInterface> getRelevantReports(Month targetMonth) throws SQLException {
        ArrayList<Month> relevantMonths = this.monthsFactory.getRelevantMonths(targetMonth);
        ArrayList<MonthlyReportInterface> result = new ArrayList<>();

        for (Month month : relevantMonths) {
            result.add(this.reportFactory.createMonthlyReport(month));
        }
        result.add(new AveragingMonthlyReportCollection(result));
        return result;
    }
}
