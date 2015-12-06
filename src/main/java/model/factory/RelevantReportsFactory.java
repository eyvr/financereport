package model.factory;

import model.exception.InvalidArgumentException;
import model.report.MonthlyReportInterface;
import model.value.Month;

import java.util.ArrayList;

public class RelevantReportsFactory {
    private RelevantMonthsFactory monthsFactory;
    private ReportFactory reportFactory;

    public RelevantReportsFactory(RelevantMonthsFactory monthsFactory, ReportFactory reportFactory) {
        this.monthsFactory = monthsFactory;
        this.reportFactory = reportFactory;
    }

    public ArrayList<MonthlyReportInterface> getRelevantReports(Month targetMonth) throws InvalidArgumentException {
        ArrayList<Month> relevantMonths = this.monthsFactory.getRelevantMonths(targetMonth);
        ArrayList<MonthlyReportInterface> result = new ArrayList<>();

        for (Month month : relevantMonths) {
            result.add(this.reportFactory.createMonthlyReport(month));
        }
        return result;
    }
}
