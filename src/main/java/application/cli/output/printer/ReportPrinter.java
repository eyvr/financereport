package application.cli.output.printer;

import application.cli.output.OutputInterface;
import model.persistance.Repository;
import model.report.MonthlyReportInterface;
import model.value.TransactionType;

import java.sql.SQLException;
import java.util.List;

public class ReportPrinter {
    private Repository repository;
    private int categoriesWidth = 13;
    private int valuesWidth = 9;

    private OutputInterface output;

    public ReportPrinter(OutputInterface output, Repository repository) {
        this.output = output;
        this.repository = repository;
    }

    public void print(List<MonthlyReportInterface> reports) throws SQLException {
        List<TransactionType> types = repository.getTypes();
        output.print(getHeadersRow(reports));
        output.print(getValuesRows(reports, types));
        output.print(getTotalsRow(reports, types));
    }

    private String getValuesRows(List<MonthlyReportInterface> reports, List<TransactionType> types) {
        String result = "";
        for (TransactionType type : types) {
            result += getCategoriesColumn(type.getName());
            for (MonthlyReportInterface report : reports) {
                result += getValuesColumn(report.getTotal(type));
            }
            result += "\n";
        }
        return result;
    }

    private String getHeadersRow(List<MonthlyReportInterface> reports) {
        String result;
        result = "";
        result += getCategoriesColumn("");
        for (MonthlyReportInterface report : reports) {
            result += getValuesColumn(report.getMonthLabel());
        }
        result += "\n";
        return result;
    }

    private String getTotalsRow(List<MonthlyReportInterface> reports, List<TransactionType> types) {
        String result = "";

        result += getCategoriesColumn("TOTAL");

        float sum;
        for (MonthlyReportInterface report : reports) {
            sum = 0f;
            for (TransactionType type : types) {
                sum += report.getTotal(type);
            }
            result += getValuesColumn(sum);
        }

        result += "\n";
        return result;
    }

    private String getValuesColumn(String string) {
        return String.format("%" + valuesWidth + "s", string);
    }

    private String getValuesColumn(float string) {
        return String.format("%" + valuesWidth + ".2f", string);
    }

    private String getCategoriesColumn(String name)
    {
        return String.format("%" + categoriesWidth + "s", name);
    }
}
