import java.util.Arrays;

/**
 * @author Santhosh Kumar Tekuri
 */
public class ProductArrayV1 {
    static int[] productArray(int a[]) {
        int n = a.length;

        int left[] = new int[n];
        left[0] = 1;
        for(int i=1; i<n; i++)
            left[i] = left[i-1]*a[i-1];

        int right[] = new int[n];
        right[n-1] = 1;
        for(int i=n-2; i>=0; i--)
            right[i] = right[i+1]*a[i+1];

        int p[] = new int[n];
        for(int i=0; i<n; i++)
            p[i] = left[i]*right[i];

        return p;
    }

    public static void main(String[] args) {
        run(new int[]{10, 3, 5, 6, 2});

        // Output: [180, 600, 360, 300, 900]
    }

    static void run(int a[]) {
        System.out.println(Arrays.toString(productArray(a)));
    }
}
