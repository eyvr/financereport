package model;

import model.exception.InvalidArgumentException;
import model.persistance.Repository;
import model.report.AveragingMonthlyReportCollection;
import model.report.MonthlyReport;
import model.report.MonthlyReportInterface;
import model.value.Month;

import java.util.ArrayList;

public class GetForecast {
    private Repository repository;

    public GetForecast(Repository repository) {
        this.repository = repository;
    }

    public ArrayList<MonthlyReportInterface> getForecast() throws InvalidArgumentException {
        ArrayList<MonthlyReportInterface> reports = new ArrayList<>();
        return reports;
    }
}
