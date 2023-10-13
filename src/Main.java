public class Main {
    public static void main(String[] args) {
        FlightBookingService fbs = FlightBookingService.createFromConsole();
        fbs.printAccountInfo();
        fbs.changeAccountInfo("Timofey Tagaev", "timofey.tagaev.2004.12@gmail.com", "88005553535", 18);
        fbs.printAccountInfo();

        Route[] routes = {
                new Route(20000, "Barnaul", "Salt Lake City", new Timestamp(12, 10, 23, 11), new Timestamp(18, 00, 23, 11)),
                new Route(60000, "Barnaul", "Stockholm", new Timestamp(18, 45, 10, 12), new Timestamp(2, 0, 11, 12)),
                new Route(40000, "Barnaul", "Moscow", new Timestamp(10, 0, 21, 9), new Timestamp(14, 10, 21, 9))
        };

        for (Route route: routes) fbs.addRoute(route);

        fbs.printAvailableRoutes();
        fbs.searchTicketsByPrice(fbs.getBalance());
        fbs.buyTicket(routes[2]);
        fbs.printTicketInfo();
        fbs.buyTicket(routes[1]);
        fbs.sellTicket();
        fbs.searchTicketsByCity("Stockholm");
        fbs.buyTicket(routes[1]);
        fbs.topUpBalance(10000);
        fbs.buyTicket(routes[1]);
        fbs.removeRoute(routes[2]);
        fbs.printAvailableRoutes();
    }
}