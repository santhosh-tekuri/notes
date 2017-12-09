import java.util.Arrays;

/**
 * @author Santhosh Kumar Tekuri
 */
public class ProductArrayV2 {
    static int[] productArray(int a[]) {
        int n = a.length;
        int p[] = new int[n];

        // compute left[] into p[]
        p[0] = 1;
        for(int i=1; i<n; i++)
            p[i] = p[i-1]*a[i-1];

        int right = 1;
        for(int i=n-2; i>=0; i--) {
            right = right*a[i+1];
            p[i] = p[i]*right;
        }
        
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
