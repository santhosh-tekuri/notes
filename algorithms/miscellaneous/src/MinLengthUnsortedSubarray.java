import java.util.Arrays;

/**
 * @author Santhosh Kumar Tekuri
 */
public class MinLengthUnsortedSubarray {
    static int[] minLengthUnsoredSubarray(int a[]) {
        int n = a.length;

        int s = 0;
        while(s<n-1 && a[s]<=a[s+1])
            s++;
        if(s==n-1)
            return null; // a[] is already sorted

        int e = n-1;
        while(e>0 && a[e]>=a[e-1])
            e--;
        assert e>s;

        int min=a[s], max=a[s];
        for(int i=s+1; i<=e; i++) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }

        while(s>0 && a[s-1]>min)
            s--;
        while(e<n-1 && a[e+1]<max)
            e++;
        return new int[]{s, e};
    }

    public static void main(String[] args) {
        run(new int[]{10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60});
        run(new int[]{0, 1, 15, 25, 6, 7, 30, 40, 50});
        run(new int[]{1, 2, 3, 4, 5});
        run(new int[]{1, 2, 3, 5, 4});
        run(new int[]{5, 4, 3, 2, 1});
        run(new int[]{1, 1, 1});

        // Output:
        // [3, 8]
        // [2, 5]
        // null
        // [3, 4]
        // [0, 4]
        // null
    }

    static void run(int a[]) {
        System.out.println(Arrays.toString(minLengthUnsoredSubarray(a)));
    }
}
