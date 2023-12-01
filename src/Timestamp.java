import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    public Timestamp(int day, int month, int year, int hour, int minute) {
        try {
            if (checkDateCorrectness(day, month, year, hour, minute)) {
                this.day = day;
                this.month = month;
                this.year = year;
                this.hour = hour;
                this.minute = minute;
                normalize();
            }
            else {
                throw new Exception("Неверный ввод, введенная дата находится в прошлом");
            }
        }
        catch (Exception ex) {
            setDefault();
            System.out.println(ex.getMessage());
            System.out.println("Установлены значения по умолчанию.");
        }

    }

    public Timestamp() {
        this(1, 1, 1970, 0, 0);
    }

    public Timestamp(Timestamp other) {
        this(other.day, other.month, other.year, other.hour, other.minute);
    }

    // Метод, возвращающий массив, представляющий собой текущее локальное время.
    private final int[] getLocalTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy HH mm");
        Date date = new Date();
        String[] localTimeArray = dateFormat.format(date).split(" ");
        int[] localTime = new int[5];
        int i = 0;
        for (String str: localTimeArray)
            localTime[i++] = Integer.parseInt(str);

        return localTime;
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

    // Метод, предназначенный для установки значений по умолчанию для временной метки.
    private void setDefault() {
        day = 1;
        month = 1;
        year = 1970;
        hour = 0;
        minute = 0;
    }

    // Метод, предназначенный для проверки действительности временной метки.
    private final boolean checkDateCorrectness(int day, int month, int year, int hour, int minute) {
        int[] localTime = getLocalTime();
        boolean dateCorrectness = false;

        if (year > localTime[2]) dateCorrectness = true;
        else if (year == localTime[2]) {
            if (month > localTime[1]) dateCorrectness = true;
            else if (month == localTime[1]) {
                if (day > localTime[0]) dateCorrectness = true;
                else if (day == localTime[0]) {
                    if (hour > localTime[3]) dateCorrectness = true;
                    else if (hour == localTime[3] && minute >= localTime[4]) dateCorrectness = true;
                }
            }
        }

        return dateCorrectness;
    }

    // Метод, предназначенный для изменения данных временной метки.
    public void changeData(int day, int month, int year, int hour, int minute) {
        if (checkDateCorrectness(day, month, year, hour, minute)) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.hour = hour;
            this.minute = minute;
            normalize();

            System.out.println("Данные временной метки успешно изменены.");
        }
        else {
            System.out.println("Неверный ввод, введенная дата находится в прошлом.");
        }

    }

    // Метод, предназначенный для определения того, является ли метка меткой по умолчанию.
    public boolean isDefault() {
        return day == 1 && month == 1 && year == 1970 && minute == 0 && hour == 0;
    }

    // Служебный метод, который проверяет, является ли год високосным.
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    // Служебный метод, возвращающий количество дней в выбранном месяце.
    private int getDaysInMonth(int month, int year) {
        if (month == 2) return (isLeapYear(year) ? 1 : 0) + 28;
        else if (month == 4 || month == 6 || month == 9 || month == 11) return 30;
        else return 31;
    }

    // Служебный метод, предназначенный для "нормализации" временной метки.
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
