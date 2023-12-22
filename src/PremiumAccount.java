import java.util.Scanner;

public class PremiumAccount extends Account {
    final float cashbackPercent = 0.04F;
    int bonuses;

    public PremiumAccount() {
        super();
        bonuses = 0;
    }

    public PremiumAccount(Account other) {
        this.name = other.name;
        this.email = other.email;
        this.telephone = other.telephone;
        this.age = other.age;
        this.balance = 0;
        this.bonuses = 0;
    }

    public PremiumAccount(String name, String email, String telephone, int age) {
        super(name, email, telephone, age);
        bonuses = 0;
    }

    // Статический метод для создания экземпляра класса через консоль.
    public static PremiumAccount createFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String name, email, telephone;

        System.out.print("Введите Ваше ФИО: ");
        name = scanner.nextLine();

        System.out.print("Введите Вашу почту: ");
        email = scanner.nextLine();

        System.out.print("Введите Ваш телефон: ");
        telephone = scanner.nextLine();

        int age;
        System.out.print("Введите Ваш возраст: ");
        age = scanner.nextInt();

        return new PremiumAccount(name, email, telephone, age);
    }

    public int calculateBonuses(int ticket_price) {
        return (int)(ticket_price * cashbackPercent);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("ФИО: " + name);
        System.out.println("Контактный телефон: " + telephone);
        System.out.println("Адрес электронной почты: " + email);
        System.out.println("Возраст: " + age);
        System.out.println("Баланс: " + balance);
        System.out.println("Количество бонусов: " + bonuses);
    }

    @Override
    public String toString() {
        return "ФИО: " + name + "\nКонтактный телефон: " + telephone
                + "\nАдрес электронной почты: " + email + "\nВозраст: "
                + age + "\nБаланс: " + balance + "\nКоличество бонусов: "
                + bonuses + "\n";
    }
}
