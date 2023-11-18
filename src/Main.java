
public class Main {
    public static void main(String[] args) {
        TravelService ts = new TravelService(new Account("Timofey Tagaev", "timofey.tagaev.2004.12@gmail.com", "+79293233802", 18, 100000));

        // Демонстрация работы с массивом объектов.
        Route[] routes = new Route[3];
        routes[0] = new Route(20000, "Barnaul", "Salt Lake City", new Timestamp(18, 11, 2023, 15, 25), new Timestamp(18, 11, 2023, 20, 25));
        routes[1] = new Route(60000, "Barnaul", "Stockholm", new Timestamp(10, 12, 2023, 12, 45), new Timestamp(11, 12, 2023, 0, 0));
        routes[2] = new Route(40000, "Barnaul", "Moscow", new Timestamp(21, 11, 2023, 14, 0), new Timestamp(21, 11, 2023, 18, 0));

        for (Route route: routes) ts.addRoute(route);

        // Демонстрация использования статического метода.
        TravelService.getCompanyProfit();
        ts.buyTicket(routes[0]);
        ts.buyTicket(routes[1]);
        TravelService.getCompanyProfit();

        // Демонстрация возврата целочисленного значения из метода вспомогательного класса.
        int randNum = Demo.randomInteger();
        System.out.println(randNum);
        randNum = Demo.randomInteger();
        System.out.println(randNum);

        // Демонстрация работы с классом String.
        String demo = "  some text  ";
        System.out.println(demo);
        demo = demo.trim(); // Удаление начальных и конечных пробельных символов
        System.out.println(demo);
        String[] split = demo.split(" "); // Разбиение строки по разделителю
        for (String str : split) System.out.print(str + " ");
        System.out.println();
        String cat = split[0].concat(split[1]); // Конкатенация
        System.out.println(cat);
        cat = cat.toUpperCase(); // Перевод всех символов в верхний регистр
        System.out.println(cat);


    }
}