import java.util.Random;

public class Demo {
    public static int randomInteger() {
        Random rand = new Random();
        return rand.nextInt(0, Integer.MAX_VALUE);
    }
}
