import java.util.Scanner;

public class BaseAccount extends Account {
    public BaseAccount(Account other) {
        this.name = other.name;
        this.email = other.email;
        this.telephone = other.telephone;
        this.age = other.age;
        this.balance = 0;
    }

    public BaseAccount(String name, String email, String telephone, int age) {
        super(name, email, telephone, age);
    }

    public BaseAccount() { super(); }

    // Статический метод для создания экземпляра класса через консоль.
    public static BaseAccount createFromConsole() {
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

        return new BaseAccount(name, email, telephone, age);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("ФИО: " + name);
        System.out.println("Контактный телефон: " + telephone);
        System.out.println("Адрес электронной почты: " + email);
        System.out.println("Возраст: " + age);
        System.out.println("Баланс: " + balance);
    }

    public void overloadWithoutCall() {
        System.out.println("BaseAccount.overloadWithoutCall()");
    }

    public void overloadWithCall() {
        super.overloadWithCall();
        System.out.print(" + BaseAccount.overloadWithCall()");
    }

    @Override
    public String toString() {
        return "ФИО: " + name + "\nКонтактный телефон: " + telephone
                + "\nАдрес электронной почты: " + email + "\nВозраст: "
                + age + "\nБаланс: " + balance + "\n";
    }
}
