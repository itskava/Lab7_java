import java.util.Scanner;

public class Route {
    private int ticketPrice;
    private String departureCity;
    private String arrivalCity;
    private Timestamp departureTime;
    private Timestamp arrivalTime;

    public Route(int ticketPrice,
                 String departureCity,
                 String arrivalCity,
                 Timestamp departureTime,
                 Timestamp arrivalTime)
    {
        this.ticketPrice = ticketPrice;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Route() {
        this(0, "", "", new Timestamp(), new Timestamp());
    }

    public Route(Route other) {
        this(other.ticketPrice, other.departureCity, other.arrivalCity, other.departureTime, other.arrivalTime);
    }

    // Статический метод для создания экземляра класса через консоль.
    public static Route createFromConsole() {
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

        return new Route(ticketPrice, departureCity, arrivalCity, departureTime, arrivalTime);
    }

    public final String getArrivalCity() {
        return this.arrivalCity;
    }

    public final int getTicketPrice() {
        return this.ticketPrice;
    }

    public final boolean equals(Route other) {
        return this.ticketPrice == other.ticketPrice && this.departureCity.equals(other.departureCity)
                && this.arrivalCity.equals(other.arrivalCity) && this.departureTime.equals(other.departureTime)
                && this.arrivalTime.equals(other.arrivalTime);
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
}
