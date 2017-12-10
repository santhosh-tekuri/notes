import java.util.Arrays;

/**
 * @author Santhosh Kumar Tekuri
 */
public class QuickSortV1 {
    static void quickSort(int a[]) {
        quickSort(a, 0, a.length-1);
    }

    static void quickSort(int a[], int lo, int hi) {
        if(lo<hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);
        }
    }

    static int partition(int a[], int lo, int hi) {
        int p = a[hi]; // last element is pivot
        int i = lo-1;
        for(int j=lo; j<hi; j++){
            // a[lo..i] is left partition. a[i+1..j-1] is right partition
            if(a[j]<p){
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i+1, hi);
        return i+1; // index of pivot
    }

    public static void main(String[] args) {
        run(new int[]{10, 7, 8, 9, 1, 5});
        run(new int[]{10, 80, 30, 90, 40, 50, 70});

        // Output:
        // [1, 5, 7, 8, 9, 10]
        // [10, 30, 40, 50, 70, 80, 90]
    }

    static void run(int a[]) {
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

    static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
