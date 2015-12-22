package model;

import model.report.MonthlyReport;
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

    public ArrayList<MonthlyReport> getMonthlyReports(Month month) throws SQLException {
        return reportFactory.getRelevantReports(month);
    }
}
