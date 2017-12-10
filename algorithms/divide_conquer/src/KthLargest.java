/**
 * @author Santhosh Kumar Tekuri
 */
public class KthLargest {
    static int kthLargest(int a[], int k) {
        int pos = a.length - k;    // k-th largest element lies at a[pos] when sorted

        int lo=0; int hi=a.length-1;
        while(true) {
            if(lo==hi)
                return a[lo];
            int p = partition(a, lo, hi);
            if(p==pos)
                return a[p];
            else if(pos<p)
                hi = p-1;
            else
                lo = p+1;
        }
    }

    public static void main(String[] args) {
        System.out.println(kthLargest(new int[]{7, 10, 4, 3, 20, 15}, 3));
        System.out.println(kthLargest(new int[]{7, 10, 4, 3, 20, 15}, 4));
        System.out.println(kthLargest(new int[]{12, 3, 5, 7, 19}, 2));

        // Output:
        // 10
        // 7
        // 12
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

    static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
