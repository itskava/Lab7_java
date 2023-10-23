import java.util.Scanner;

public class Timestamp {
    private int hour;
    private int minute;
    private int day;
    private int month;

    public Timestamp(int hour, int minute, int day, int month) {
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.month = month;
    }

    public Timestamp() {
        this(0, 0, 0, 0);
    }

    public Timestamp(final Timestamp other) {
        this(other.hour, other.minute, other.day, other.month);
    }

    // Статический метод для создания экземпляра класса через консоль.
    public static Timestamp createFromConsole() {
        int hour, minute, day, month;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите часы, минуты, день и месяц временной отметки через пробел (HH mm dd MM): ");
        hour = scanner.nextInt();
        minute = scanner.nextInt();
        day = scanner.nextInt();
        month = scanner.nextInt();

        return new Timestamp(hour, minute, day, month);
    }

    // Метод для печати информации о временной метке.
    public final void printTimestamp() {
        if (hour < 10) System.out.print("0");
        System.out.print(hour + ":");
        if (minute < 10) System.out.print("0");
        System.out.print(minute + " ");
        if (day < 10) System.out.print("0");
        System.out.print(day + ", ");
        if (month < 10) System.out.print("0");
        System.out.println(month);
    }

    // Метод, сравнивающий метки по полям.
    public final boolean equals(Timestamp other) {
        return this.hour == other.hour && this.minute == other.minute
                && this.day == other.day && this.month == other.month;
    }
}
