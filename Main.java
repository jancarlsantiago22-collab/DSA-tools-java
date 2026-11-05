import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        ArraysModule arraysModule = new ArraysModule(INPUT);
        StacksModule stacksModule = new StacksModule(INPUT);
        QueuesModule queuesModule = new QueuesModule(INPUT);
        LinkedListsModule linkedListsModule = new LinkedListsModule(INPUT);
        SearchModule searchModule = new SearchModule(INPUT);
        SortingModule sortingModule = new SortingModule(INPUT);
        TreesModule treesModule = new TreesModule(INPUT);

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = ConsoleInput.readInt(INPUT, "Enter your choice? ", 0, 8);
            switch (choice) {
                case 1 -> arraysModule.run();
                case 2 -> stacksModule.run();
                case 3 -> queuesModule.run();
                case 4 -> linkedListsModule.run();
                case 5 -> searchModule.run();
                case 6 -> sortingModule.run();
                case 7 -> treesModule.run();
                case 8 -> displayDevelopers();
                case 0 -> {
                    System.out.println("\nThank you for using the DSA Demonstration System. - Group 2");
                    running = false;
                }
            }
        }
        INPUT.close();
    }

    private static void printMainMenu() {
        System.out.println("\n========================================");
        System.out.println("DATA STRUCTURE & ALGORITHMS");
        System.out.println("Case Study Project - Group 02");
        System.out.println("========================================");
        System.out.println("MAIN MENU");
        System.out.println("----------------------------------------");
        System.out.println("1 Arrays (1D, 2D)");
        System.out.println("2 Stacks (Array, Linked List)");
        System.out.println("3 Queues (Array, Linked List)");
        System.out.println("4 Linked List (Singly, Doubly, Circular)");
        System.out.println("5 Search Algorithms (Linear, Binary, Interpolation)");
        System.out.println("6 Sorting Algorithms (Bubble, Insertion, Selection, Quick)");
        System.out.println("7 Binary Search Trees / Expression Trees (Infix, Postfix, Prefix)");
        System.out.println("8 Developers (Display Group Members)");
        System.out.println("0 Exit System");
        System.out.println("========================================");
    }

    private static void displayDevelopers() {
        System.out.println("\nDEVELOPERS - GROUP 02");
        System.out.println("BSIT - COLM Pulilan, Bulacan");
        System.out.println("----------------------------------------");
        System.out.println("1. Castro, James Ivan S. (2526-00631) - Documentation / Arrays Module");
        System.out.println("2. Santiago, Jan Carl Arante (2526-02214) - Stacks & Queues Modules");
        System.out.println("3. Victorino, Dave Lawrence P. (2526-01757) - Linked Lists Module");
        System.out.println("4. Barodi, Ibrahim Morallos (2526-00479) - Searching & Sorting Modules");
        System.out.println("5. Ortiz, Jb Kiel Calderon (2526-00185) - Trees Module / Tester");
        System.out.println("----------------------------------------");
        System.out.println("Course: CC104 Data Structures and Algorithms");
        System.out.println("Instructor: Edwin S. Garcia");
        ConsoleInput.pause(INPUT);
    }
}
