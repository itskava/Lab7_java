import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;

public class FlightBookingService {
    private ArrayList<Route> routes;
    private Account account;

    public FlightBookingService(String name, String email, String telephone, int age, int balance) {
        this.account = new Account(name, email, telephone, age, balance);
        this.routes = new ArrayList<Route>();
    }

    public FlightBookingService() {
        this.account = new Account("", "", "", 0, 0);
        this.routes = new ArrayList<Route>();
    }

    public FlightBookingService(Account account) {
        this.account = new Account(account);
        this.routes = new ArrayList<Route>();
    }

    public static FlightBookingService createFromConsole() {
        return new FlightBookingService(Account.createFromConsole());
    }

    // Метод, распечатывающий информацию обо всех доступных маршрутах.
    public final void printAvailableRoutes() {
        if (!routes.isEmpty()) {
            System.out.println("Доступные маршруты:\n");
            int index = 1;

            for (Route route: routes) {
                System.out.print(index++ + ". ");
                route.printRouteInfo();
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
    public final void searchTicketsByCity(String desired_city) {
        boolean is_found = false;
        int index = 1;

        for (Route rt: routes) {
            if (rt.getArrivalCity().equals(desired_city)) {
                if (!is_found) {
                    is_found = true;
                    System.out.println("Найдены следующие маршруты до города " + desired_city + ":");
                }

                System.out.print(index++ + ". ");
                rt.printRouteInfo();
            }
        }

        if (!is_found) {
            System.out.println("Подходящих маршрутов не найдено.");
        }
    }

    /* Метод, производящий поиск маршрутов по заданной цене.
       Выводит список всех доступных городов, цена билетов которых не превышает заданную. */
    public final void searchTicketsByPrice(int available_money) {
        boolean is_found = false;
        int index = 1;

        for (Route rt: routes) {
            if (rt.getTicketPrice() <= available_money) {
                if (!is_found) {
                    is_found = true;
                    System.out.println("Найдены следующие маршруты стоимостью до " + available_money + " рублей:");
                }

                System.out.print(index++ + ". ");
                rt.printRouteInfo();
            }
        }

        if (!is_found) {
            System.out.println("Подходящих маршрутов не найдено.");
        }
    }

    // Метод, предназначенный для покупки билетов.
    public void buyTicket(Route route) {
        if (account.getTicket() != null) {
            System.out.println("У Вас уже куплен билет, купить еще один невозможно.\n");
            return;
        }

        boolean is_not_enough_money = false;
        for (Route rt: routes) {
            if (rt.equals(route)) {
                if (account.getBalance() >= rt.getTicketPrice()) {
                    account.setTicket(route);
                    account.setBalance(account.getBalance() - rt.getTicketPrice());
                    System.out.println("Билет успешно куплен, на вашем счету осталось " +
                            account.getBalance() + " рублей.\n");
                    return;
                }
            }
            else {
                is_not_enough_money = true;
            }
        }
        if (is_not_enough_money) {
            System.out.println("На Вашем счету недостаточно средств для покупки билета.\n");
        }
        else {
            System.out.println("Данный маршрут пока недоступен.\n");
        }
    }

    // Метод, предназначенный для продажи билета.
    public void sellTicket() {
        if (account.getTicket() != null) {
            account.setBalance(account.getBalance() + account.getTicket().getTicketPrice());
            account.setTicket(null);
            System.out.println("Билет успешно продан, на Вашем счету " + account.getBalance() + " рублей.\n");
        }
        else {
            System.out.println("На Вашем счету нет купленных билетов.\n");
        }
    }

    // Метод, распечатывающий информацию о купленном билете.
    public final void printTicketInfo() {
        if (account.getTicket() != null) account.getTicket().printRouteInfo();
        else System.out.println("Билет не куплен, просмотреть информацию невозможно.");
    }
}
