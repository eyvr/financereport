package model.report;

import model.value.TransactionType;

import java.util.List;

public interface MonthlyReportInterface {
    String getMonthLabel();

    float getTotal(TransactionType type);

    float getTotal();

    float getDailyAverage();
}
