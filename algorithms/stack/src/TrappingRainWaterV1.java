/**
 * @author Santhosh Kumar Tekuri
 */
public class TrappingRainWaterV1 {
    static int findWater(int bar[]) {
        int n = bar.length;

        int water = 0;
        for(int i=0; i<n; i++) {
            int lmax = 0;
            for(int j=0; j<=i; j++)
                lmax = Math.max(lmax, bar[j]);

            int rmax = 0;
            for(int j=i; j<n; j++)
                rmax = Math.max(rmax, bar[j]);

            water += Math.min(lmax, rmax) - bar[i];
        }
        return water;
    }

    public static void main(String[] args) {
        System.out.println(findWater(new int[]{}));
        System.out.println(findWater(new int[]{2, 0, 2}));
        System.out.println(findWater(new int[]{3, 0, 0, 2, 0, 4}));
        System.out.println(findWater(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

        // Output:
        // 0
        // 2
        // 10
        // 6
    }
}
