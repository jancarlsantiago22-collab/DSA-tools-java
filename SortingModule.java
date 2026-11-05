import java.util.Arrays;
import java.util.Scanner;

public class SortingModule {
    private final Scanner sc;
    private int[] data = {64,34,25,12,22,11,90};

    public SortingModule(Scanner sc){ this.sc=sc; }

    public void run(){
        boolean back=false;
        while(!back){
            System.out.println("\n--- SORTING MODULE ---");
            System.out.println("Current array: "+Arrays.toString(data));
            System.out.println("1. Set/Enter array");
            System.out.println("2. Bubble Sort");
            System.out.println("3. Insertion Sort");
            System.out.println("4. Selection Sort");
            System.out.println("5. Quick Sort");
            System.out.println("0. Return to Main Menu");
            int c=ConsoleInput.readInt(sc,"Choice: ",0,5);
            switch(c){
                case 1 -> setArray();
                case 2 -> bubbleSort();
                case 3 -> insertionSort();
                case 4 -> selectionSort();
                case 5 -> quickSort();
                case 0 -> back=true;
            }
        }
    }
    private void setArray(){
        int n=ConsoleInput.readInt(sc,"Size (1-20): ",1,20);
        data=new int[n];
        for(int i=0;i<n;i++) data[i]=ConsoleInput.readAnyInt(sc,"data["+i+"] = ");
    }
    private void bubbleSort(){
        int[] a=Arrays.copyOf(data,data.length);
        int comps=0, swaps=0;
        System.out.println("Before: "+Arrays.toString(a));
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-i-1;j++){
                comps++;
                if(a[j]>a[j+1]){ int t=a[j]; a[j]=a[j+1]; a[j+1]=t; swaps++; }
            }
        }
        System.out.println("After:  "+Arrays.toString(a));
        System.out.println("Comparisons: "+comps+", Swaps: "+swaps+" | Time: O(n^2), Space: O(1)");
    }
    private void insertionSort(){
        int[] a=Arrays.copyOf(data,data.length);
        int comps=0, shifts=0;
        System.out.println("Before: "+Arrays.toString(a));
        for(int i=1;i<a.length;i++){
            int key=a[i]; int j=i-1;
            while(j>=0 && a[j]>key){ comps++; a[j+1]=a[j]; shifts++; j--; }
            if(j>=0) comps++;
            a[j+1]=key;
        }
        System.out.println("After:  "+Arrays.toString(a));
        System.out.println("Comparisons: "+comps+", Shifts: "+shifts+" | Time: O(n^2) worst, O(n) best, Space: O(1)");
    }
    private void selectionSort(){
        int[] a=Arrays.copyOf(data,data.length);
        int comps=0, swaps=0;
        System.out.println("Before: "+Arrays.toString(a));
        for(int i=0;i<a.length-1;i++){
            int min=i;
            for(int j=i+1;j<a.length;j++){ comps++; if(a[j]<a[min]) min=j; }
            if(min!=i){ int t=a[i]; a[i]=a[min]; a[min]=t; swaps++; }
        }
        System.out.println("After:  "+Arrays.toString(a));
        System.out.println("Comparisons: "+comps+", Swaps: "+swaps+" | Time: O(n^2), Space: O(1)");
    }
    private void quickSort(){
        int[] a=Arrays.copyOf(data,data.length);
        System.out.println("Before: "+Arrays.toString(a));
        Counter counter=new Counter();
        quick(a,0,a.length-1,counter);
        System.out.println("After:  "+Arrays.toString(a));
        System.out.println("Comparisons: "+counter.comps+", Swaps: "+counter.swaps+" | Time: O(n log n) avg, O(n^2) worst, Space: O(log n)");
    }
    static class Counter{int comps=0, swaps=0;}
    private void quick(int[] a,int low,int high,Counter c){
        if(low<high){
            int pi=partition(a,low,high,c);
            quick(a,low,pi-1,c);
            quick(a,pi+1,high,c);
        }
    }
    private int partition(int[] a,int low,int high,Counter c){
        int pivot=a[high]; int i=low-1;
        for(int j=low;j<high;j++){ c.comps++; if(a[j]<=pivot){ i++; int t=a[i]; a[i]=a[j]; a[j]=t; c.swaps++; } }
        int t=a[i+1]; a[i+1]=a[high]; a[high]=t; c.swaps++;
        return i+1;
    }
}
