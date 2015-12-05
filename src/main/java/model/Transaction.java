package model;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
    private final String title;
    private final Date date;
    private final TransactionType type;
    private final float amount;

    public Transaction(String title, Date date, TransactionType type, float amount) {
        this.title = title;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getMonthYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.YEAR)) + String.valueOf(cal.get(Calendar.MONTH) + 1);
    }

    public String toString()
    {

        return String.format(
                "%s %13s %9s %s",
                new java.text.SimpleDateFormat("yyyy-MM-dd").format(date),
                type.toString(),
                String.format("%.2f", amount),
                title.replace("\n", " ")
        );

    }
}
