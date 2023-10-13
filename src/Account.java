import java.util.Scanner;

public class Account {
    private String name;
    private String email;
    private String telephone;
    private int age;
    private int balance;
    private Route ticket;

    public Account(String name, String email, String telephone, int age, int balance) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
        this.balance = balance;
        this.ticket = null;
    }

    public Account() {
        this("", "", "", 0, 0);
    }

    public Account(Account other) {
        this(other.name, other.email, other.telephone, other.age, other.balance);
    }

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

    public final Route getTicket() {
        return this.ticket;
    }

    public void setTicket(Route route) {
        if (route == null) this.ticket = null;
        else this.ticket = new Route(route);
    }

    public final boolean equals(Account other) {
        return this.name.equals(other.name) && this.email.equals(other.email)
                && this.telephone.equals(other.telephone) && this.age == other.age
                && this.balance == other.balance;
    }

}
