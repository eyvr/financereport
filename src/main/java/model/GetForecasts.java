package model;

import model.exception.InvalidArgumentException;
import model.report.MonthlyReportInterface;
import model.report.Oracle;
import model.value.ForecastCollection;

import java.util.ArrayList;

public class GetForecasts {
    private Oracle oracle;

    public GetForecasts(Oracle oracle) {
        this.oracle = oracle;
    }

    public ForecastCollection getForecast(ArrayList<MonthlyReportInterface> monthlyReports) throws InvalidArgumentException {
        return oracle.guessPayUpMonth(monthlyReports);
    }
}
