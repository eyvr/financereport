package application;

import application.cli.output.CliOutput;
import application.cli.output.printer.ForecastCollectionPrinter;
import application.cli.output.printer.ReportPrinter;
import application.persistance.Repository;
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
