package model.report;

import model.exception.InvalidArgumentException;
import model.value.Month;
import model.value.Transaction;
import model.value.TransactionType;

import java.util.List;
import java.util.Objects;

public class MonthlyReport implements MonthlyReportInterface {
    private List<Transaction> transactions;

    public MonthlyReport(List<Transaction> transactions) throws InvalidArgumentException {
        String lastMonth = null;
        for (Transaction transaction : transactions) {
            if (lastMonth != null && !Objects.equals(transaction.getMonthYear(), lastMonth)) {
                throw new InvalidArgumentException(
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
        return getMonth().toString();
    }

    private Month getMonth() {
        return this.transactions.get(0).getMonth();
    }

    @Override
    public float getTotal(TransactionType type) {
        float sum = 0f;

        for (Transaction transaction : transactions) {
            if (type == transaction.getType()) {
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
        return getTotal()/this.getMonth().getDaysCount();
    }
}
