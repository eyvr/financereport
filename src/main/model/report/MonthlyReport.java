package model.report;

import model.value.TransactionType;

public interface MonthlyReport {
    String getMonthLabel();

    float getTotal(TransactionType type);

    float getTotal();

    float getDailyAverage();
}
