import java.util.Scanner;

public class Timestamp {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private int getDaysInMonth(int month, int year) {
        if (month == 2) return (isLeapYear(year) ? 1 : 0) + 28;
        else if (month == 4 || month == 6 || month == 9 || month == 11) return 30;
        else return 31;
    }

    private void normalize() {
        while (minute >= 60) {
            minute -= 60;
            hour++;
        }

        while (hour >= 24) {
            hour -= 24;
            day++;
        }

        while (day > getDaysInMonth(month, year)) {
            day -= getDaysInMonth(month, year);
            if (++month > 12) {
                month = 1;
                year++;
            }
        }
    }

    public Timestamp(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        normalize();
    }

    public Timestamp() {
        this(1, 1, 1970, 0, 0);
    }

    public Timestamp(Timestamp other) {
        this(other.day, other.month, other.year, other.hour, other.minute);
    }

    // Статический метод для создания экземпляра класса через консоль.
    public static Timestamp createFromConsole() {
        int day, month, year, hour, minute;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите день, месяц, год, часы и минуты временной отметки через пробел (dd.MM.yyyy hh:mm): ");
        day = scanner.nextInt();
        month = scanner.nextInt();
        year = scanner.nextInt();
        hour = scanner.nextInt();
        minute = scanner.nextInt();

        return new Timestamp(day, month, year, hour, minute);
    }

    public final boolean equals(Timestamp other) {
        return this.hour == other.hour && this.minute == other.minute
                && this.day == other.day && this.month == other.month;
    }

    @Override
    public String toString() {
        String str = "";
        if (day < 10) str += "0";
        str += day + "/";
        if (month < 10) str += "0";
        str += month + "/";
        str += year + " ";
        if (hour < 10) str += "0";
        str += hour + ":";
        if (minute < 10) str += "0";
        str += minute;
        return str;
    }
}
