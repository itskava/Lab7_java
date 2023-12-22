import java.util.Scanner;

public class Route<T> implements Cloneable, AdditionalInfoSupportable {
    private int ticketPrice;
    private String departureCity;
    private String arrivalCity;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private T additionalInfo;

    public Route(int ticketPrice,
                 String departureCity,
                 String arrivalCity,
                 Timestamp departureTime,
                 Timestamp arrivalTime,
                 T additionalInfo)
    {
        this.ticketPrice = ticketPrice;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.additionalInfo = additionalInfo;
    }

    public Route() {
        this(0, "", "", new Timestamp(), new Timestamp(), null);
    }

    public Route(Route<T> other) {
        this(other.ticketPrice, other.departureCity, other.arrivalCity, other.departureTime, other.arrivalTime, null);
    }

    // Статический метод для создания экземляра класса через консоль.
    public static Route<String> createFromConsole() {
        Scanner scanner = new Scanner(System.in);

        int ticketPrice;
        System.out.print("Введите цену билета: ");
        ticketPrice = scanner.nextInt();
        scanner.nextLine();

        String departureCity, arrivalCity;
        System.out.print("Введите город вылета: ");
        departureCity = scanner.nextLine();

        System.out.print("Введите город посадки: ");
        arrivalCity = scanner.nextLine();

        System.out.println("Для вылета:");
        Timestamp departureTime = Timestamp.createFromConsole();

        System.out.println("Для посадки: ");
        Timestamp arrivalTime = Timestamp.createFromConsole();


        return new Route<String>(ticketPrice, departureCity, arrivalCity, departureTime, arrivalTime, null);
    }

    public final String getArrivalCity() {
        return this.arrivalCity;
    }

    public final int getTicketPrice() {
        return this.ticketPrice;
    }

    public final boolean equals(Route<T> other) {
        return this.ticketPrice == other.ticketPrice && this.departureCity.equals(other.departureCity)
                && this.arrivalCity.equals(other.arrivalCity) && this.departureTime.equals(other.departureTime)
                && this.arrivalTime.equals(other.arrivalTime);
    }

    public final void displayAdditionalInfo() {
        if (additionalInfo != null) {
            System.out.println(additionalInfo.toString());
        }
        else {
            System.out.println("Для данного маршрута нет дополнительной информации.");
        }
    }

    public final void setAdditionalInfo(Object additionalInfo) {
        this.additionalInfo = (T)additionalInfo;
        System.out.println("Дополнительная информация успешно обновлена.");
    }

    public T getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Маршрут: " + departureCity + " - " + arrivalCity + "\n";
        str += "Время взлёта: " + departureTime.toString() + "\n";
        str += "Время посадки: " + arrivalTime.toString() + "\n";
        str += "Цена билета: " + ticketPrice;
        return str;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
