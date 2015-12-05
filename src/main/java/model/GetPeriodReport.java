package model;

import model.report.MonthlyReportCollection;
import model.report.MonthlyReportInterface;
import model.value.Month;
import model.exception.InvalidArgumentException;
import model.persistance.Repository;
import model.report.MonthlyReport;

import java.util.ArrayList;
import java.util.List;

public class GetPeriodReport {
    private Repository repository;

    public GetPeriodReport(Repository repository) {
        this.repository = repository;
    }

    public ArrayList<MonthlyReportInterface> getMonthlyReports(Month startMonth, Month endMonth) throws InvalidArgumentException {
        Month current = startMonth;
        ArrayList<MonthlyReportInterface> reports = new ArrayList<>();

        while (current.isEarlierThan(endMonth) || current.isEqualTo(endMonth)) {
            reports.add(
                    new MonthlyReport(repository.getTransactionsForMonth(current))
            );
            current = current.getNextMonth();
        }
        return reports;
    }
}
