import java.util.ArrayList;
import java.util.Scanner;

public abstract class Account {
    protected String name = "";
    protected String email = "";
    protected String telephone = "";
    protected int age;
    protected int balance;
    protected ArrayList<Route<String>> tickets;

    public Account(String name, String email, String telephone, int age) {
        try {
            checkAccountDataCorrectness(name, email, telephone, age);
            this.name = name;
            this.email = email;
            this.telephone = telephone;
            this.age = age;
            this.balance = 0;
            this.tickets = new ArrayList<Route<String>>();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Чтобы пользоваться сервисом, пожалуйста, обновите личную информацию на действительную.");
        }

    }

    public Account() {}

    protected final void checkAccountDataCorrectness(
            String name,
            String email,
            String telephone,
            int age) throws Exception
    {
        if (name.split(" ").length != 3)
            throw new Exception("Вы неверно ввели ФИО.");
        if (!checkEmailCorrectness(email))
            throw new Exception("Вы ввели недействительный адрес электронной почты.");
        if (telephone.charAt(0) != '+' || telephone.length() != 12)
            throw new Exception("Вы ввели недействительный номер телефона.");
        if (age < 18)
            throw new Exception("Чтобы создать аккаунт, вам должно быть как минимум 18 лет.");
        if (age > 99)
            throw new Exception("Вы ввели недействительный возраст.");
    }

    protected boolean checkEmailCorrectness(String email) {
        boolean emailCorrectness = true;
        if (!email.contains("@")) emailCorrectness = false;
        else if (!email.contains(".")) emailCorrectness = false;
        else if (email.contains("@.")) emailCorrectness = false;
        else if (email.charAt(0) == '@' || email.charAt(email.length() - 1) == '.') emailCorrectness = false;

        return emailCorrectness;
    }

    protected final boolean isInitialized() {
        return !name.isEmpty() && !email.isEmpty() && telephone != null && age != 0;
    }

    public abstract void displayAccountInfo();

    public void overloadWithoutCall() {
        System.out.println("Account.overloadWithoutCall()");
    }

    public void overloadWithCall() {
        System.out.print("Account.overloadWithCall()");
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

    public final ArrayList<Route<String>> getTickets() {
        return new ArrayList<Route<String>>(tickets);
    }

    public void addTicket(Route<String> route) {
        tickets.add(route);
    }

    public void sellTicket(Route<String> rt) {
        tickets.remove(rt);
    }
}
