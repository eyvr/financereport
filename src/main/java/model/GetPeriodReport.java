package model;

import model.exception.InvalidArgumentException;
import model.report.AveragingMonthlyReportCollection;
import model.report.MonthlyReportInterface;
import model.report.factory.RelevantReportsFactory;
import model.value.Month;

import java.util.ArrayList;

public class GetPeriodReport {

    private RelevantReportsFactory reportFactory;

    public GetPeriodReport(RelevantReportsFactory reportFactory) {
        this.reportFactory = reportFactory;
    }

    public ArrayList<MonthlyReportInterface> getMonthlyReports(Month month) throws InvalidArgumentException {

        ArrayList<MonthlyReportInterface> relevantReports = reportFactory.getRelevantReports(month);
        relevantReports.add(new AveragingMonthlyReportCollection(relevantReports));
        return relevantReports;
    }
}
