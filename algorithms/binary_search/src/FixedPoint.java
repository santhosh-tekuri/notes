/**
 * @author Santhosh Kumar Tekuri
 */
public class FixedPoint {
    static int fixedPoint(int a[]) {
        int lo=0, hi=a.length-1;
        while(lo<=hi) {
            int m = lo+(hi-lo)/2;
            if(a[m]==m)
                return m;
            if(a[m]<m)
                lo = m+1;
            else
                hi = m-1;
        }
        return -1; // no fixed point
    }

    public static void main(String[] args) {
        System.out.println(fixedPoint(new int[]{-10, -5, 0, 3, 7}));
        System.out.println(fixedPoint(new int[]{0, 2, 5, 8, 17}));
        System.out.println(fixedPoint(new int[]{-10, -5, 3, 4, 7, 9}));

        // Output:
        // 3
        // 0
        // -1
    }
}
