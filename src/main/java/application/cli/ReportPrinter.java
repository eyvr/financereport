package application.cli;

import model.report.MonthlyReportInterface;
import model.value.TransactionType;

import java.util.List;

public class ReportPrinter {
    private int categoriesWidth = 13;
    private int valuesWidth = 9;

    private List<TransactionType> types;

    public ReportPrinter(List<TransactionType> types) {
        this.types = types;
    }

    public String print(List<MonthlyReportInterface> reports) {
        String result = "";

        result += getHeadersRow(reports);
        result += getValuesRows(reports);
        result += getTotalsRow(reports);

        return result;
    }

    private String getValuesRows(List<MonthlyReportInterface> reports) {
        String result = "";
        for (TransactionType type : types) {
            result += getCategoriesColumn(type.toString().toLowerCase());
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

    private String getTotalsRow(List<MonthlyReportInterface> reports) {
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
