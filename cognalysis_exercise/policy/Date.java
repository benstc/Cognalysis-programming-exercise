package policy;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(String date) {
        String[] dateComponents = date.split("/");
        this.day = Integer.parseInt(dateComponents[1]);
        this.month = Integer.parseInt(dateComponents[0]);
        this.year = Integer.parseInt(dateComponents[2]);
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isAfter(Date otherDate) { // returns true if date calling funciton is after the parameter date
        if (otherDate.getYear() > this.getYear()) {
            return false;
        }
        else if (this.getYear() > otherDate.getYear()) {
            return true;
        }
        else { // Both dates share year
            if (otherDate.getMonth() > this.getMonth()) {
                return false;
            }
            else if (this.getMonth() > otherDate.getMonth()) {
                return true;
            }
            else { // Both dates share year an month
                if (otherDate.getDay() > this.getDay()) {
                    return false;
                }
                else if (this.getDay() > otherDate.getDay()) {
                    return true;
                }
                return true;
            }
        }
    }

    public int daysBetween(Date otherDate) {
        if (this.isAfter(otherDate)) {
            System.out.println("error");
            return -1;
        }
        int days1 = 365*year; 
        for (int i = 1; i < month; i++) {
            days1 += daysInMonth(i);

        }
        days1 += this.getDay(); // days1 now holds days since "this" and year 0

        int days2 = 365*otherDate.getYear();
        for (int i = 1; i < otherDate.getMonth(); i++) {
            days2 += daysInMonth(i);
        }
        days2 += otherDate.getDay(); // days2 now holds days since otherDate and year 0

        return days2 - days1;

    }

    public int daysInMonth() {
        int[] daysInMonths = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        return daysInMonths[this.month - 1];
    }

    public int daysInMonth(int month) {
        int[] daysInMonths = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        return daysInMonths[month - 1];
    }
}
