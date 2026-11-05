import java.util.Scanner;

public class ConsoleInput {
    public static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                System.out.printf("Invalid input. Enter a number from %d to %d.%n", min, max);
                continue;
            }
            try {
                int v = Integer.parseInt(line);
                if (v >= min && v <= max) return v;
                System.out.printf("Out of range. Enter a number from %d to %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. Enter a number from %d to %d.%n", min, max);
            }
        }
    }

    public static int readAnyInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (line.isEmpty()) { System.out.println("Input cannot be empty."); continue; }
            try { return Integer.parseInt(line); }
            catch (NumberFormatException e) { System.out.println("Invalid number. Try again."); }
        }
    }

    public static String readNonEmpty(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("Input cannot be blank.");
        }
    }

    public static void pause(Scanner sc) {
        System.out.print("\nPress Enter to continue...");
        sc.nextLine();
    }
}
