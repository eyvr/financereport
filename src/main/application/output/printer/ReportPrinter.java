package application.output.printer;

import application.output.Output;
import model.persistence.Repository;
import model.report.MonthlyReport;
import model.value.TransactionType;

import java.sql.SQLException;
import java.util.List;

public class ReportPrinter {
    private Repository repository;
    private int categoriesWidth = 13;
    private int valuesWidth = 9;

    private Output output;

    public ReportPrinter(Output output, Repository repository) {
        this.output = output;
        this.repository = repository;
    }

    public void print(List<MonthlyReport> reports) throws SQLException {
        List<TransactionType> types = repository.getTypes();
        getHeadersRow(reports);
        getValuesRows(reports, types);
        getTotalsRow(reports, types);
    }

    private void getValuesRows(List<MonthlyReport> reports, List<TransactionType> types) {
        for (TransactionType type : types) {
            output.print(getCategoriesColumn(type.getName()));
            for (MonthlyReport report : reports) {
                output.print(getValuesColumn(report.getTotal(type)));
            }
            output.println("");
        }
    }

    private void getHeadersRow(List<MonthlyReport> reports) {
        output.print(getCategoriesColumn(""));
        for (MonthlyReport report : reports) {
            output.print(getValuesColumn(report.getMonthLabel()));
        }
        output.println("");
    }

    private void getTotalsRow(List<MonthlyReport> reports, List<TransactionType> types) {

        output.print(getCategoriesColumn("TOTAL"));

        float sum;
        for (MonthlyReport report : reports) {
            sum = 0f;
            for (TransactionType type : types) {
                sum += report.getTotal(type);
            }
            output.print(getValuesColumn(sum));
        }
        output.println("");
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
