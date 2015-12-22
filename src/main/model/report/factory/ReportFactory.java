package model.report.factory;

import model.persistence.Repository;
import model.report.DefaultMonthlyReport;
import model.value.Month;

import java.sql.SQLException;

public class ReportFactory {
    private Repository repository;

    public ReportFactory(Repository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("null not allowed");
        }
        this.repository = repository;
    }

    public DefaultMonthlyReport createMonthlyReport(Month month) throws SQLException {
        return new DefaultMonthlyReport(repository.getTransactionsForMonth(month));
    }
}
