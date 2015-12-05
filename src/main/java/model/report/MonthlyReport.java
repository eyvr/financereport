package model.report;

import model.Month;
import model.Transaction;
import model.TransactionType;
import model.exception.InvalidArgumentException;
import java.util.List;
import java.util.Objects;

public class MonthlyReport {
    private List<Transaction> transactions;
    private Month month;

    public MonthlyReport(Month month, List<Transaction> transactions) throws InvalidArgumentException {
        this.month = month;
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

    public float getSum(TransactionType type) {
        float sum = 0f;

        for (Transaction transaction : transactions) {
            if (type == transaction.getType()) {
                sum = sum + transaction.getAmount();
            }
        }
        return sum;
    }

    public float getTotal() {
        float sum = 0f;

        for (Transaction transaction : transactions) {
            sum = sum + transaction.getAmount();
        }
        return sum;
    }

    public float getDailyAverage() {
        return getTotal()/this.month.getDaysCount();
    }
}
