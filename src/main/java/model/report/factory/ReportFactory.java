package model.report.factory;

import model.persistance.Repository;
import model.report.MonthlyReport;
import model.value.Month;

import java.sql.SQLException;

public class ReportFactory {
    private Repository repository;

    public ReportFactory(Repository repository) {
        this.repository = repository;
    }

    public MonthlyReport createMonthlyReport(Month month) throws SQLException {
        return new MonthlyReport(repository.getTransactionsForMonth(month));
    }
}
