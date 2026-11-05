import java.util.Arrays;
import java.util.Scanner;

public class SearchModule {
    private final Scanner sc;
    private int[] data = {10,20,30,40,50,60,70,80,90,100};

    public SearchModule(Scanner sc){ this.sc=sc; }

    public void run(){
        boolean back=false;
        while(!back){
            System.out.println("\n--- SEARCHING MODULE ---");
            System.out.println("Current array: "+Arrays.toString(data)+" (sorted for binary/interpolation)");
            System.out.println("1. Set/Enter array");
            System.out.println("2. Linear Search");
            System.out.println("3. Binary Search");
            System.out.println("4. Interpolation Search");
            System.out.println("5. Compare all three");
            System.out.println("0. Return to Main Menu");
            int c=ConsoleInput.readInt(sc,"Choice: ",0,5);
            switch(c){
                case 1 -> setArray();
                case 2 -> linearSearch();
                case 3 -> binarySearch();
                case 4 -> interpolationSearch();
                case 5 -> compareAll();
                case 0 -> back=true;
            }
        }
    }
    private void setArray(){
        int n=ConsoleInput.readInt(sc,"Enter size (1-20): ",1,20);
        data=new int[n];
        for(int i=0;i<n;i++) data[i]=ConsoleInput.readAnyInt(sc,"data["+i+"] = ");
        System.out.println("Array set. For binary/interpolation, array will be sorted automatically when needed.");
    }
    private void linearSearch(){
        int key=ConsoleInput.readAnyInt(sc,"Search key: ");
        int comps=0;
        int pos=-1;
        for(int i=0;i<data.length;i++){ comps++; if(data[i]==key){ pos=i; break; } }
        if(pos!=-1) System.out.println("Found at index "+pos+" with "+comps+" comparisons. Time: O(n)");
        else System.out.println("Not found after "+comps+" comparisons.");
    }
    private int[] getSortedCopy(){ 
        int[] copy=Arrays.copyOf(data,data.length); Arrays.sort(copy); return copy; 
    }

    private void binarySearch(){
        int key=ConsoleInput.readAnyInt(sc,"Search key: ");
        int[] sorted=getSortedCopy();
        System.out.println("Sorted for binary: "+Arrays.toString(sorted));
        int low=0, high=sorted.length-1, comps=0, pos=-1;
        while(low<=high){ comps++; int mid=(low+high)/2; 
            if(sorted[mid]==key){pos=mid; break;} else if(sorted[mid]<key) low=mid+1; 
            else high=mid-1; }
        if(pos!=-1) 
            System.out.println("Found at sorted index "+pos+" with "+comps+" comparisons. Time: O(log n)");
        else System.out.println("Not found after "+comps+" comparisons.");
    }
    private void interpolationSearch(){
        int key=ConsoleInput.readAnyInt(sc,"Search key: ");
        int[] sorted=getSortedCopy();
        System.out.println("Sorted for interpolation: "+Arrays.toString(sorted));
        int low=0, high=sorted.length-1, comps=0, pos=-1;
        while(low<=high && key>=sorted[low] && key<=sorted[high]){
            comps++;
            if(low==high){ if(sorted[low]==key) pos=low; break; }
            int idx = low + ((key - sorted[low]) * (high - low) / (sorted[high] - sorted[low]));
            if(idx<low || idx>high) break;
            if(sorted[idx]==key){ pos=idx; break; }
            if(sorted[idx]<key) low=idx+1; else high=idx-1;
        }
        if(pos!=-1) System.out.println("Found at sorted index "+pos+" with "+comps+" comparisons. Time: O(log log n) best, O(n) worst");
        else System.out.println("Not found after "+comps+" comparisons. Note: Interpolation works best on uniformly distributed data.");
    }
    private void compareAll(){
        int key=ConsoleInput.readAnyInt(sc,"Search key for comparison: ");
        // linear
        int compsL=0; for(int i=0;i<data.length;i++){compsL++; if(data[i]==key) break;}
        int[] sorted=getSortedCopy();
        int low=0, high=sorted.length-1, compsB=0; while(low<=high){compsB++; int mid=(low+high)/2; if(sorted[mid]==key) break; else if(sorted[mid]<key) low=mid+1; else high=mid-1;}
        low=0; high=sorted.length-1; int compsI=0; while(low<=high && key>=sorted[low] && key<=sorted[high]){compsI++; if(low==high) break; int idx = low + ((key - sorted[low]) * (high - low) / Math.max(1,(sorted[high]-sorted[low]))); if(sorted[idx]==key) break; if(sorted[idx]<key) low=idx+1; else high=idx-1;}
        System.out.println("Comparisons -> Linear: "+compsL+" | Binary: "+compsB+" | Interpolation: "+compsI);
    }
}
