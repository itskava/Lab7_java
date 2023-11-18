import java.util.ArrayList;
import java.util.Scanner;

public class Account {
    private String name;
    private String email;
    private String telephone;
    private int age;
    private int balance;
    private ArrayList<Route> tickets;

    public Account(String name, String email, String telephone, int age, int balance) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
        this.balance = balance;
        this.tickets = new ArrayList<Route>();
    }

    public Account() {
        this("", "", "", 0, 0);
    }

    public Account(Account other) {
        this(other.name, other.email, other.telephone, other.age, other.balance);
        this.tickets = (ArrayList<Route>)other.tickets.clone();
    }

    // Статический метод для создания экземпляра класса через консоль.
    public static Account createFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String name, email, telephone;

        System.out.print("Введите Ваше ФИО: ");
        name = scanner.nextLine();

        System.out.print("Введите Вашу почту: ");
        email = scanner.nextLine();

        System.out.print("Введите Ваш телефон: ");
        telephone = scanner.nextLine();

        int age, balance;
        System.out.print("Введите Ваш возраст: ");
        age = scanner.nextInt();

        System.out.print("Введите сумму, на которую будет сразу пополнен Ваш счёт: ");
        balance = scanner.nextInt();
        scanner.nextLine();

        return new Account(name, email, telephone, age, balance);
    }

    // Метод для печати информации об аккаунте.
    public final void printAccountInfo() {
        System.out.println("Данные аккаунта:");
        System.out.println("ФИО: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Почта: " + email);
        System.out.println("Телефон: " + telephone + "\n");
    }

    public final String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public final String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public final String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public final int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public final int getBalance() {
        return this.balance;
    }

    public void setBalance(int amount) {
        this.balance = amount;
    }

    public final ArrayList<Route> getTickets() {
        return new ArrayList<Route>(tickets);
    }

    public void addTicket(Route route) {
        tickets.add(route);
    }

    public void sellTicket(Route rt) {
        tickets.remove(rt);
    }
}
