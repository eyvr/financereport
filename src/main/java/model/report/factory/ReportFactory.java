package model.report.factory;

import model.exception.InvalidArgumentException;
import model.persistance.Repository;
import model.report.MonthlyReport;
import model.value.Month;

public class ReportFactory {
    private Repository repository;

    public ReportFactory(Repository repository) {

        this.repository = repository;
    }

    public MonthlyReport createMonthlyReport(Month month) throws InvalidArgumentException {
        return new MonthlyReport(repository.getTransactionsForMonth(month));
    }
}
