import java.util.Arrays;

/**
 * @author Santhosh Kumar Tekuri
 */
public class ArithmeticProgression3 {
    static int[] arithmeticProgression(int a[]) {
        int n = a.length;
        for(int j=2; j<n; j++) { // a[j] is middle element
            int i=j-1, k=j+1;
            while(i>=0 && k<n) {
                if(a[i]+a[k]<2*a[j])
                    k++;
                else if(a[i]+a[k]>2*a[j])
                    i--;
                else
                    return new int[]{i, j, k};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        run(new int[]{1, 7, 10, 15, 27, 29});
        run(new int[]{1, 7, 10, 12, 22, 23});
        run(new int[]{1, 2, 5, 6});
        run(new int[]{});

        // Output:
        // [0, 3, 5]
        // [0, 3, 5]
        // null
        // null
    }

    static void run(int a[]) {
        System.out.println(Arrays.toString(arithmeticProgression(a)));
    }
}
