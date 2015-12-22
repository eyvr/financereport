package application;

import application.output.CliOutput;
import application.output.printer.ForecastCollectionPrinter;
import application.output.printer.ReportPrinter;
import application.persistence.Repository;
import model.GetForecasts;
import model.GetPeriodReport;
import model.forecast.ForecastMaker;
import model.report.RelevantMonthsChooser;
import model.report.factory.RelevantReportsFactory;
import model.report.factory.ReportFactory;
import model.value.Month;

import java.sql.SQLException;

public class ServiceFactory {

    private Repository repository;
    private CliOutput output;

    public ServiceFactory(CliOutput output) throws SQLException {
        if (output == null) {
            throw new IllegalArgumentException();
        }
        this.output = output;
        this.repository = new Repository("localhost", 3306, "root", "password", "transactions");
    }

    public GetForecasts getGetForecasts(int amount) throws SQLException {
        return new GetForecasts(getRelevantReportFactory(), new ForecastMaker(Month.createCurrent(), amount));
    }

    public GetPeriodReport getGetPeriodReport() throws SQLException {
        return new GetPeriodReport(getRelevantReportFactory());
    }

    public ReportPrinter getReportPrinter() throws SQLException {
        return new ReportPrinter(output, repository);
    }

    public ForecastCollectionPrinter getForecastCollectionPrinter() {
        return new ForecastCollectionPrinter(output);
    }

    private RelevantReportsFactory getRelevantReportFactory() throws SQLException {
        return new RelevantReportsFactory(new RelevantMonthsChooser(), new ReportFactory(repository));
    }
}
