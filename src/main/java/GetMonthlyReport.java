import model.Month;
import model.report.MonthlyReport;
import model.exception.InvalidArgumentException;
import model.persistance.Repository;

public class GetMonthlyReport {
    Repository repository;

    public GetMonthlyReport(Repository repository) {
        this.repository = repository;
    }

    public MonthlyReport getReport(Month month) throws InvalidArgumentException {
        return new MonthlyReport(month, this.repository.getTransactionsForMonth(month));
    }
}
