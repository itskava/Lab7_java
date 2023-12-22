import java.sql.Time;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        BaseAccount baseAcc = new BaseAccount("Nill Viktorovich Kiggers", "some@inbox.com", "+12345678901", 25);

        baseAcc.overloadWithoutCall(); // Перегруженный метод без вызова метода базового класса
        baseAcc.overloadWithCall(); // Перегруженный метод с вызовом метода базового класса
        System.out.println();

        AdditionalRouteInfo info = new AdditionalRouteInfo("sunny", "fine");
        Route<AdditionalRouteInfo> route = new Route<>(1000, "a", "b", new Timestamp(), new Timestamp(), info);
        route.displayAdditionalInfo();

        // Демонстрация поверхностного копирования
        Route<String> copy1 = new Route<>(1000, "a", "b", new Timestamp(), new Timestamp(), null);
        Route<String> shallowCopy = copy1;
        shallowCopy.setAdditionalInfo("cat");
        shallowCopy.displayAdditionalInfo();
        copy1.displayAdditionalInfo();

        // Демонстранция глубокого копирования
        Route<String> copy2 = new Route<>(1000, "c", "d", new Timestamp(), new Timestamp(), null);
        Route<String> deepCopy = (Route<String>)copy2.clone();
        deepCopy.setAdditionalInfo("dog");
        deepCopy.displayAdditionalInfo();
        copy1.displayAdditionalInfo();
    }
}