import java.awt.desktop.SystemSleepEvent;

public class Main {
    public static void main(String[] args) {
        Timestamp timestamp_demo = new Timestamp(30, 11, 2022, 22, 20); // Намеренно введена дата в прошлом.
        System.out.println(timestamp_demo.toString() + "\n");
        timestamp_demo.changeData(30, 11, 2024, 22, 20); // Время изменено на действительное в будущем.
        System.out.println(timestamp_demo.toString() + "\n");

        Account account_demo = new Account("NotFullName", "NotEmail", "NotTelephone", 5); // Намеренно записаны недействительные данные.
        TravelService ts = new TravelService(account_demo);
        ts.displayAccountInfo();
        ts.changeAccountData("Totally Full Name", "totallyvalid@email.com", "+89138881122", 25); // Изменены на правильные.
        ts.displayAccountInfo();

        // Вывод одномерного массива экземпляров класса.
        Timestamp[] one_dim_array = new Timestamp[] {
                new	Timestamp(10, 12, 2023, 14, 10),
                new Timestamp(31, 12, 2023, 23, 59),
                new Timestamp(28, 12, 2023, 10, 10)
        };

        for (Timestamp tm: one_dim_array) {
            System.out.println(tm.toString());
        }
        System.out.println();

        Timestamp[][] two_dim_array = new Timestamp[][] {
                { new Timestamp(10, 8, 2025, 0, 1), new Timestamp(11, 9, 2025, 1, 2) },
                { new Timestamp(12, 10, 2025, 2, 3), new Timestamp(13, 11, 2025, 3, 4) }
        };

        // Вывод двумерного массива экземпляров класса.
        for (Timestamp[] row: two_dim_array) {
            for (Timestamp tm: row) {
                System.out.print(tm.toString() + " ");
            }
            System.out.println();
        }
    }
}