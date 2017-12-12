/**
 * @author Santhosh Kumar Tekuri
 */
public class TrappingRainWaterV2 {
    static int findWater(int bar[]) {
        int n = bar.length;

        if(n==0)
            return 0;

        int lmax[] = new int[n];
        lmax[0] = bar[0];
        for(int i=1; i<n; i++)
            lmax[i] = Math.max(lmax[i-1], bar[i]);

        int rmax[] = new int[n];
        rmax[n-1] = bar[n-1];
        for(int i=n-2; i>=0; i--)
            rmax[i] = Math.max(rmax[i+1], bar[i]);

        int water = 0;
        for(int i=0; i<n; i++)
            water += Math.min(lmax[i], rmax[i]) - bar[i];
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
