import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BaseAccount baseAcc = new BaseAccount("Nill Viktorovich Kiggers", "some@inbox.com", "+12345678901", 25);
        TravelService ts = new TravelService(baseAcc);

        Route<String> rt1 = new Route<>(1000, "a", "b", new Timestamp(), new Timestamp(), null);
        Route<String> rt2 = new Route<>(2000, "c", "d", new Timestamp(), new Timestamp(), null);
        Route<String> rt3 = new Route<>(3000, "e", "f", new Timestamp(), new Timestamp(), null);
        ts.addRoute(rt3);
        ts.addRoute(rt2);
        ts.addRoute(rt1);

        ts.displayAvailableRoutes();
        ts.sortTicketsByPrice();
        ts.displayAvailableRoutes();

        BaseAccount acc = new BaseAccount();
        PremiumAccount acc2 = new PremiumAccount();
        Account acc3 = new BaseAccount();

        ArrayList<Account> container_demo = new ArrayList<>();
        container_demo.add(acc);
        container_demo.add(acc2);
        container_demo.add(acc3);

    }
}