package model.report;

import model.value.TransactionType;

public interface MonthlyReportInterface {
    String getMonthLabel();

    float getTotal(TransactionType type);

    float getTotal();

    float getDailyAverage();
}
