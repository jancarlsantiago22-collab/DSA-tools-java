import java.util.Arrays;
import java.util.Scanner;

public class ArraysModule {
    private final Scanner sc;
    private int[] arr1D = new int[0];
    private int[][] arr2D = new int[0][0];
    private int rows = 0, cols = 0;

    public ArraysModule(Scanner sc) { this.sc = sc; }

    public void run() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- ARRAYS MODULE (1D & 2D) ---");
            System.out.println("1. Create / Resize 1D Array");
            System.out.println("2. Display 1D Array");
            System.out.println("3. Insert into 1D Array");
            System.out.println("4. Delete from 1D Array");
            System.out.println("5. Update 1D Array Element");
            System.out.println("6. Search 1D Array (Linear)");
            System.out.println("7. Statistics (Sum, Avg, Min, Max)");
            System.out.println("8. Create 2D Array");
            System.out.println("9. Display 2D Array & Row/Col Totals");
            System.out.println("10. Transpose 2D Array");
            System.out.println("0. Return to Main Menu");
            int c = ConsoleInput.readInt(sc, "Choice: ", 0, 10);
            switch (c) {
                case 1 -> create1D();
                case 2 -> display1D();
                case 3 -> insert1D();
                case 4 -> delete1D();
                case 5 -> update1D();
                case 6 -> search1D();
                case 7 -> stats1D();
                case 8 -> create2D();
                case 9 -> display2D();
                case 10 -> transpose2D();
                case 0 -> back = true;
            }
        }
    }
    private void create1D() {
        int n = ConsoleInput.readInt(sc, "Enter size of 1D array (1-50): ", 1, 50);
        arr1D = new int[n];
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) arr1D[i] = ConsoleInput.readAnyInt(sc, "arr["+i+"] = ");
        System.out.println("1D Array created.");
    }
    private void display1D() {
        if (arr1D.length == 0) { System.out.println("Array is empty. Create first."); return; }
        System.out.println("1D Array: " + Arrays.toString(arr1D));
    }
    private void insert1D() {
        if (arr1D.length == 0) { System.out.println("Create array first."); return; }
        int pos = ConsoleInput.readInt(sc, "Insert position (0-" + arr1D.length + "): ", 0, arr1D.length);
        int val = ConsoleInput.readAnyInt(sc, "Value to insert: ");
        int[] newArr = new int[arr1D.length + 1];
        System.arraycopy(arr1D, 0, newArr, 0, pos);
        newArr[pos] = val;
        System.arraycopy(arr1D, pos, newArr, pos+1, arr1D.length - pos);
        arr1D = newArr;
        System.out.println("Inserted. New array: " + Arrays.toString(arr1D));
    }
    private void delete1D() {
        if (arr1D.length == 0) { System.out.println("Array empty."); return; }
        int pos = ConsoleInput.readInt(sc, "Delete position (0-" + (arr1D.length-1) + "): ", 0, arr1D.length-1);
        int[] newArr = new int[arr1D.length - 1];
        System.arraycopy(arr1D, 0, newArr, 0, pos);
        System.arraycopy(arr1D, pos+1, newArr, pos, arr1D.length - pos -1);
        arr1D = newArr;
        System.out.println("Deleted. New array: " + Arrays.toString(arr1D));
    }
    private void update1D() {
        if (arr1D.length == 0) { System.out.println("Array empty."); return; }
        int pos = ConsoleInput.readInt(sc, "Update position (0-" + (arr1D.length-1) + "): ", 0, arr1D.length-1);
        int val = ConsoleInput.readAnyInt(sc, "New value: ");
        arr1D[pos] = val;
        System.out.println("Updated: " + Arrays.toString(arr1D));
    }
    private void search1D() {
        if (arr1D.length == 0) { System.out.println("Array empty."); return; }
        int key = ConsoleInput.readAnyInt(sc, "Search value: ");
        boolean found = false;
        for (int i = 0; i < arr1D.length; i++) if (arr1D[i] == key) { System.out.println("Found at index " + i); found = true; }
        if (!found) System.out.println("Not found.");
    }
    private void stats1D() {
        if (arr1D.length == 0) { System.out.println("Array empty."); return; }
        int sum = 0, min = arr1D[0], max = arr1D[0];
        for (int v : arr1D) { sum += v; if (v < min) min = v; if (v > max) max = v; }
        double avg = (double) sum / arr1D.length;
        System.out.printf("Sum=%d, Avg=%.2f, Min=%d, Max=%d%n", sum, avg, min, max);
    }
    private void create2D() {
        rows = ConsoleInput.readInt(sc, "Rows (1-10): ", 1, 10);
        cols = ConsoleInput.readInt(sc, "Cols (1-10): ", 1, 10);
        arr2D = new int[rows][cols];
        for (int i = 0; i < rows; i++) for (int j = 0; j < cols; j++) arr2D[i][j] = ConsoleInput.readAnyInt(sc, "a["+i+"]["+j+"] = ");
        System.out.println("2D Array created.");
    }
    private void display2D() {
        if (rows == 0) { System.out.println("Create 2D array first."); return; }
        System.out.println("2D Array:");
        for (int i = 0; i < rows; i++) {
            int rowSum = 0;
            for (int j = 0; j < cols; j++) { System.out.printf("%5d", arr2D[i][j]); rowSum += arr2D[i][j]; }
            System.out.println(" | Row sum: " + rowSum);
        }
        System.out.print("Col totals: ");
        for (int j = 0; j < cols; j++) { int s = 0; for (int i = 0; i < rows; i++) s += arr2D[i][j]; System.out.print(s + " "); }
        System.out.println();
    }
    private void transpose2D() {
        if (rows == 0) { System.out.println("Create 2D array first."); return; }
        int[][] t = new int[cols][rows];
        for (int i = 0; i < rows; i++) for (int j = 0; j < cols; j++) t[j][i] = arr2D[i][j];
        System.out.println("Transpose:");
        for (int i = 0; i < cols; i++) { for (int j = 0; j < rows; j++) System.out.printf("%5d", t[i][j]); System.out.println(); }
    }
}
