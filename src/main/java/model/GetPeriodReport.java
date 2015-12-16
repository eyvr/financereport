package model;

import model.report.MonthlyReportInterface;
import model.report.factory.RelevantReportsFactory;
import model.value.Month;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Main use case - get nice summary of spendings.
 */
public class GetPeriodReport {

    private RelevantReportsFactory reportFactory;

    public GetPeriodReport(RelevantReportsFactory reportFactory) {
        this.reportFactory = reportFactory;
    }

    public ArrayList<MonthlyReportInterface> getMonthlyReports(Month month) throws SQLException {
        return reportFactory.getRelevantReports(month);
    }
}
