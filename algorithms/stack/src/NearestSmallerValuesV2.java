import java.util.Arrays;

/**
 * @author Santhosh Kumar Tekuri
 */
public class NearestSmallerValuesV2 {
    static int[] nearestSmallerValues(int a[]) {
        int n = a.length;
        int b[] = new int[n];
        for(int i=0; i<n; i++) {
            int j = i-1;
            while(j!=-1 && a[j]>=a[i])
                j = b[j];
            b[i] = j;
        }
        return b;
    }

    public static void main(String[] args) {
        run(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
        run(new int[]{1, 6, 4, 10, 2, 5});
        run(new int[]{1, 3, 0, 2, 5});

        // Output:
        // [-1, 0, 0, 2, 0, 4, 4, 6, 0, 8, 8, 10, 8, 12, 12, 14]
        // [-1, 0, 0, 2, 0, 4]
        // [-1, 0, -1, 2, 3]
    }

    static void run(int a[]) {
        System.out.println(Arrays.toString(nearestSmallerValues(a)));
    }
}
