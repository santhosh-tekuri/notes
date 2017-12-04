import java.util.Arrays;

/**
 * @author Santhosh Kumar Tekuri
 */
public class NearestSmallerValuesV2 {
    static int[] nearestSmallerValues(int a[]) {
        int n = a.length;
        int b[] = new int[n];
        for(int i=0; i<n; i++) {
            int j=i-1;
            while(j!=-1 && a[j]>=a[i])
                j = b[j];
            b[i] = j;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nearestSmallerValues(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15})));
        System.out.println(Arrays.toString(nearestSmallerValues(new int[]{1, 6, 4, 10, 2, 5})));
        System.out.println(Arrays.toString(nearestSmallerValues(new int[]{1, 3, 0, 2, 5})));
    }
}
