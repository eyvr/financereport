import model.Month;
import model.exception.InvalidArgumentException;
import model.persistance.Repository;
import model.report.MonthlyReport;
import model.report.PeriodReport;

import java.util.ArrayList;
import java.util.List;

public class GetPeriodReport {
    private Repository repository;

    public GetPeriodReport(Repository repository) {
        this.repository = repository;
    }

    public PeriodReport getPeriodReport(Month startMonth, Month endMonth) throws InvalidArgumentException {
        Month current = startMonth;
        List<MonthlyReport> reports = new ArrayList<>();

        while (current.isEarlierThan(endMonth) || current.isEqualTo(endMonth)) {
            reports.add(
                    new MonthlyReport(current, repository.getTransactionsForMonth(current))
            );
            current = current.getNextMonth();
        }
        return new PeriodReport(startMonth, endMonth, reports);
    }
}
