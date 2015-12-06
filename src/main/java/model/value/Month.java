package model.value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Month {
    private int year;
    private int month;
    private Month nextMonth;
    private Date date;

    public Month(int year, int month)
    {
        this.year = year;
        this.month = month;
    }

    public String toString()
    {
        return String.format("%d-%02d", this.year, this.month);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public boolean isEarlierThan(Month month) {
        if (this.year < month.getYear()) {
            return true;
        }
        if (this.year > month.getYear()) {
            return false;
        }
        return month.getMonth() > this.month;
    }

    public boolean isEqualTo(Month month) {
        return month.getYear() == this.year && month.getMonth() == this.month;
    }

    public int getDaysCount() {
        if (month == 2) {
            return year % 4 == 0 ? 29 : 28;
        }
        if (month < 8) {
            return month % 2 == 0 ? 30 : 31;
        }
        return month % 2 == 0 ? 31 : 30;
    }

    public String getMonthStart() {
        return String.format("%d-%02d-01", year, month);
    }

    public Month getPreviousMonth() {
        int year = this.year;
        int month = this.month - 1;
        if (month < 1) {
            month = 12;
            year--;
        }
        return new Month(year, month);
    }

    public Month getNextMonth() {
        int year = this.year;
        int month = this.month + 1;
        if (month > 12) {
            month = 1;
            year++;
        }
        return new Month(year, month);
    }

    public static Month createFromString(String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(new SimpleDateFormat("yyyy-MM").parse(date));
        return new Month(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1);
    }

    public Date getDate(int day) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(String.format("%s-%d", this.toString(), day));
    }

    public static Month createCurrent() {
        Calendar c = Calendar.getInstance();
        return new Month(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1);
    }
}
