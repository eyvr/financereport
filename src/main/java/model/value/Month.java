package model.value;

public class Month {
    private int year;
    private int month;
    private Month nextMonth;

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
}
