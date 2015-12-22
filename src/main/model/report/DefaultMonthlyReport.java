package model.report;

import model.value.Month;
import model.value.Transaction;
import model.value.TransactionType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class DefaultMonthlyReport implements MonthlyReport {
    private List<Transaction> transactions;

    public DefaultMonthlyReport(List<Transaction> transactions)  {
        if (transactions == null) {
            throw new IllegalArgumentException("null is not allowed");
        }
        String lastMonth = null;
        for (Transaction transaction : transactions) {
            if (lastMonth != null && !Objects.equals(transaction.getMonthYear(), lastMonth)) {
                throw new IllegalArgumentException(
                        "Monthly report may be created only from transactions from one month"
                );
            }
            lastMonth = transaction.getMonthYear();
        }

        this.transactions = transactions;
    }

    @Override
    public String getMonthLabel()
    {
        Month m = getMonth();
        if (m == null) {
            return "-";
        }
        return getMonth().toString();
    }

    @Override
    public float getTotal(TransactionType type) {
        float sum = 0f;

        for (Transaction transaction : transactions) {
            if (Objects.equals(type.getName(), transaction.getType().getName())) {
                sum = sum + transaction.getAmount();
            }
        }
        return sum;
    }

    @Override
    public float getTotal() {
        float sum = 0f;

        for (Transaction transaction : transactions) {
            sum = sum + transaction.getAmount();
        }
        return sum;
    }

    @Override
    public float getDailyAverage() {
        if (this.getMonth() == null) {
            return 0f;
        }
        return getTotal()/this.getMonth().getDaysCount();
    }

    private Month getMonth() {
        if (transactions.size() == 0) {
            return null;
        }
        return this.transactions.get(0).getMonth();
    }
}
