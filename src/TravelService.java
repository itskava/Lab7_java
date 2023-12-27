import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class TravelService {
    private ArrayList<Route<String>> routes;
    private Account account;
    private static long profit;

    public TravelService(String name, String email, String telephone, int age) {
        this.account = new BaseAccount(name, email, telephone, age);
        this.routes = new ArrayList<Route<String>>();
    }

    public TravelService() throws IllegalArgumentException {
        this.account = new BaseAccount("", "", "", 0);
        this.routes = new ArrayList<Route<String>>();
    }

    public TravelService(Account account)   {
        if (account instanceof BaseAccount || account instanceof PremiumAccount) {
            if (account instanceof BaseAccount) this.account = new BaseAccount(account);
            else  this.account = new PremiumAccount();
            this.account.setName(account.getName());
            this.account.setEmail(account.getEmail());
            this.account.setTelephone(account.getTelephone());
            this.account.setAge(account.getAge());
            this.account.setBalance(0);
            this.routes = new ArrayList<Route<String>>();
        }
        else {
            throw new IllegalArgumentException("Недопустимый параметр.");
        }

    }

    public static TravelService createFromConsole() {
        return new TravelService(BaseAccount.createFromConsole());
    }

    // Метод, распечатывающий информацию обо всех доступных маршрутах.
    public final void displayAvailableRoutes() {
        if (!routes.isEmpty()) {
            System.out.println("Доступные маршруты:\n");
            int index = 1;

            for (Route<String> route: routes) {
                System.out.println(index++ + ". " + route.toString() + "\n");
            }
        }
        else {
            System.out.println("В данный момент нет доступных маршрутов.");
        }
    }

    // Метод, предназначенный для вывода информации о профиле.
    public final void displayAccountInfo() {
        account.displayAccountInfo();
    }

    // Метод, предназначенный для изменения данных аккаунта.
    public void changeAccountData(String name, String email, String telephone, int age) {
        try {
            account.checkAccountDataCorrectness(name, email, telephone, age);
            account.setName(name);
            account.setEmail(email);
            account.setTelephone(telephone);
            account.setAge(age);

            System.out.println("Информация об аккаунте успешно изменена.\n");
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Пожалуйста, повторите попытку.");
        }
    }

    public final int getBalance() {
        return account.getBalance();
    }

    // Метод, предназначенный для пополнения баланса аккаунта.
    public void topUpBalance(int amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Ваш баланс успешно пополнен на " + amount + " рублей");
        System.out.println("Теперь он составляет " + account.getBalance() + " рублей.\n");
    }

    // Метод, предназначенный для добавления маршрута.
    public void addRoute(Route<String> route) {
        if (route == null) return;

        if (routes.contains(route)) {
            System.out.println("Данный маршрут уже существует, добавление невозможно");
            return;
        }
        routes.add(route);
        System.out.println("Маршрут успешно добавлен.");
    }

    // Метод, предназначенный для удаления маршрута.
    public void removeRoute(int desiredIndex) {
        var tickets = account.getTickets();
        if (tickets.isEmpty()) {
            System.out.println("Сейчас нет доступных маршрутов");
        }

        if (desiredIndex < 1 || desiredIndex > tickets.size()) {
            System.out.println("Введен некорректный номер билета.");
            return;
        }

        int index = 1;

        for (Route<String> rt: tickets) {
            if (index++ == desiredIndex) {
                routes.remove(rt);
                System.out.println("Маршрут успешно удален.");
                return;
            }
        }

        System.out.println("Данный маршрут не найден, удаление невозможно.");
    }

    /* Метод, производящий поиск маршрутов по выбранному городу.
       Выводит список всех доступных городов, город посадки которых совпадает с выбранным. */
    public final void searchTicketsByCity(String desiredCity) {
        boolean isFound = false;
        int index = 1;

        for (Route<String> rt: routes) {
            if (rt.getArrivalCity().equals(desiredCity)) {
                if (!isFound) {
                    isFound = true;
                    System.out.println("Найдены следующие маршруты до города " + desiredCity + ":");
                }

                System.out.println(index++ + ". " + rt.toString() + "\n");
            }
        }

        if (!isFound) {
            System.out.println("Подходящих маршрутов не найдено.");
        }
    }

    /* Метод, производящий поиск маршрутов по заданной цене.
       Выводит список всех доступных городов, цена билетов которых не превышает заданную. */
    public final void searchTicketsByPrice(int availableMoney) {
        boolean isFound = false;
        int index = 1;

        for (Route<String> rt: routes) {
            if (rt.getTicketPrice() <= availableMoney) {
                if (!isFound) {
                    isFound = true;
                    System.out.println("Найдены следующие маршруты стоимостью до " + availableMoney + " рублей:");
                }

                System.out.println(index++ + ". " + rt.toString() + "\n");
            }
        }

        if (!isFound) {
            System.out.println("Подходящих маршрутов не найдено.");
        }
    }

    public void sortTicketsByPrice() {
        routes.sort(Comparator.comparingInt(Route::getTicketPrice));
        System.out.println("Билеты успешно отсортированы.");
    }

    // Метод, предназначенный для покупки билетов.
    public void buyTicket(Route<String> route) {
        if (!account.isInitialized()) {
            System.out.println("Ваш аккаунт не инициализирован. Пожалуйста, обновите информацию профиля.");
            return;
        }

        boolean isNotEnoughMoney = false;
        if (routes.contains(route)) {
            int price = route.getTicketPrice();
            if (account instanceof BaseAccount) {
                if (account.getBalance() >= price) {
                    account.addTicket(route);
                    account.setBalance(account.getBalance() - price);
                    profit += price;
                    System.out.println("Билет успешно куплен, на вашем счету осталось " +
                            account.getBalance() + " рублей.\n");
                    return;
                }
                else {
                    isNotEnoughMoney = true;
                }
            }
            else {
                PremiumAccount premAcc = (PremiumAccount)account;
                if (account.balance + premAcc.bonuses >= price) {
                    int newBonuses = premAcc.calculateBonuses(price);

                    if (price >= premAcc.bonuses) {
                        int ticketPrice = price;
                        ticketPrice -= premAcc.bonuses;
                        premAcc.bonuses = 0;
                        premAcc.balance -= ticketPrice;
                    }
                    else {
                        premAcc.balance -= price;
                    }

                    premAcc.bonuses += newBonuses;

                    System.out.println("Билет успешно куплен, на счету осталось " +
                            premAcc.balance + " рублей и " + premAcc.bonuses +
                            " бонусов.\n");

                    account = premAcc;
                    return;
                }
                else {
                    isNotEnoughMoney = true;
                }
            }
        }

        if (isNotEnoughMoney) {
            System.out.println("На Вашем счету недостаточно средств для покупки билета.\n");
        }
        else {
            System.out.println("Данный маршрут пока недоступен.\n");
        }
    }

    // Метод, предназначенный для продажи билета.
    public void sellTicket(int desiredIndex) {
        if (!account.isInitialized()) {
            System.out.println("Ваш аккаунт не инициализирован. Пожалуйста, обновите информацию профиля.");
            return;
        }

        var tickets = account.getTickets();
        if (tickets.isEmpty()) {
            System.out.println("На Вашем аккаунте нет купленных билетов.\n");
        }

        if (desiredIndex < 1 || desiredIndex > tickets.size()) {
            System.out.println("Введен некорректный номер купленного билета.");
            return;
        }
        else {
            int index = 1;
            for (Route<String> rt: tickets) {
                if (index++ == desiredIndex) {
                    account.setBalance(account.getBalance() + rt.getTicketPrice());
                    profit -= rt.getTicketPrice();
                    account.sellTicket(rt);
                    break;
                }
            }
            System.out.println("Билет успешно продан, на Вашем счету " + account.getBalance() + " рублей.\n");
        }
    }

    // Метод, распечатывающий информацию о купленном билете.
    public final void printTicketsInfo() {
        var tickets = account.getTickets();
        if (tickets.isEmpty()) {
            System.out.println("На Вашем аккаунте нет купленных билетов.\n");
            return;
        }
        int index = 1;

        for (Route<String> rt: tickets) {
            System.out.println(index++ + ". " + rt.toString() + "\n");
        }
    }

    public static void getCompanyProfit() {
        System.out.println("На данный момент прибыль компании составляет " + profit + " рублей.\n");
    }

    public void upgradeToPremium() {
        if (account instanceof BaseAccount) {
            if (account.balance >= 2000) {
                PremiumAccount newAccount = new PremiumAccount();

                newAccount.name = account.name;
                newAccount.email = account.email;
                newAccount.telephone = account.telephone;
                newAccount.age = account.age;
                newAccount.balance = account.balance - 2000;
                newAccount.bonuses = 0;

                account = newAccount;
            }
            else {
                System.out.println("На вашем аккаунте недостаточно средств, чтобы получить премиум-аккаунт.");
            }
        }
        else {
            System.out.println("У вас уже куплен премиум-аккаунт.");
        }
    }
}
