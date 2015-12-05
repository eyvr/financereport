package model.value;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
    private final String title;
    private final Date date;
    private final TransactionType type;
    private final float amount;
    private Month month;

    public Transaction(String title, Date date, TransactionType type, float amount) {
        this.title = title;
        this.date = date;
        this.type = type;
        this.amount = amount;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        month = new Month(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
    }

    public float getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getMonthYear() {
        return this.month.toString();
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

    public Month getMonth() {
        return month;
    }
}
