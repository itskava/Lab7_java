import java.util.ArrayList;

public class TravelService {
    private ArrayList<Route> routes;
    private Account account;
    private static long profit;

    public TravelService(String name, String email, String telephone, int age, int balance) {
        this.account = new Account(name, email, telephone, age, balance);
        this.routes = new ArrayList<Route>();
    }

    public TravelService() {
        this.account = new Account("", "", "", 0, 0);
        this.routes = new ArrayList<Route>();
    }

    public TravelService(Account account) {
        this.account = new Account(account);
        this.routes = new ArrayList<Route>();
    }

    public static TravelService createFromConsole() {
        return new TravelService(Account.createFromConsole());
    }

    // Метод, распечатывающий информацию обо всех доступных маршрутах.
    public final void printAvailableRoutes() {
        if (!routes.isEmpty()) {
            System.out.println("Доступные маршруты:\n");
            int index = 1;

            for (Route route: routes) {
                System.out.println(index++ + ". " + route.toString() + "\n");
            }
        }
        else {
            System.out.println("В данный момент нет доступных маршрутов.");
        }
    }

    // Метод, распечатывающий информацию об аккаунте.
    public final void printAccountInfo() {
        account.printAccountInfo();
    }

    // Метод, предназначенный для изменения данных аккаунта.
    public void changeAccountInfo(String name, String email, String telephone, int age) {
        account.setName(name);
        account.setEmail(email);
        account.setTelephone(telephone);
        account.setAge(age);

        System.out.println("Учетные данные Вашего аккаунта успешно изменены.\n");
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
    public void addRoute(Route route) {
        if (route == null) return;

        for (Route rt: routes) {
            if (rt.equals(route)) {
                System.out.println("Данный маршрут уже существует, добавление невозможно");
                return;
            }
        }
        routes.add(route);
        System.out.println("Маршрут успешно добавлен.");
    }

    // Метод, предназначенный для удаления маршрута.
    public void removeRoute(Route route) {
        for (Route rt: routes) {
            if (rt.equals(route)) {
                routes.remove(route);
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

        for (Route rt: routes) {
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

        for (Route rt: routes) {
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

    // Метод, предназначенный для покупки билетов.
    public void buyTicket(Route route) {
        boolean isNotEnoughMoney = false;
        for (Route rt: routes) {
            if (rt.equals(route)) {
                if (account.getBalance() >= rt.getTicketPrice()) {
                    account.addTicket(route);
                    account.setBalance(account.getBalance() - rt.getTicketPrice());
                    profit += rt.getTicketPrice();
                    System.out.println("Билет успешно куплен, на вашем счету осталось " +
                            account.getBalance() + " рублей.\n");
                    return;
                }
            }
            else {
                isNotEnoughMoney = true;
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
        var tickets = account.getTickets();
        if (desiredIndex < 1 || desiredIndex > tickets.size()) {
            System.out.println("Введен некорректный номер купленного билета.");
            return;
        }

        if (tickets.isEmpty()) {
            System.out.println("На Вашем аккаунте нет купленных билетов.\n");
        }

        else {
            int index = 1;
            for (Route rt: tickets) {
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

        for (Route rt: tickets) {
            System.out.println(index++ + ". " + rt.toString() + "\n");
        }
    }

    public static void getCompanyProfit() {
        System.out.println("На данный момент прибыль компании составляет " + profit + " рублей.\n");
    }
}
