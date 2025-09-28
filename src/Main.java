import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите, что запустить:");
        System.out.println("1 - Deadlock");
        System.out.println("2 - Livelock");
        System.out.println("3 - Alternating Print");
        System.out.print("Ваш выбор: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                DeadlockExample.main(null);
                break;
            case 2:
                LivelockExample.main(null);
                break;
            case 3:
                AlternatingPrint.main(null);
                break;
            default:
                System.out.println("Неверный выбор!");
        }

        scanner.close();
    }
}