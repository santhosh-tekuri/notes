/**
 * @author Santhosh Kumar Tekuri
 */
public class TrappingRainWaterV3 {
    static int findWater(int bar[]) {
        int water = 0;
        int left = 0, right = bar.length-1;
        int lmax = 0, rmax = 0;
        while(left<right) {
            if(bar[left]<bar[right]) { // 2...9
                if(bar[left]>lmax)
                    lmax = bar[left]; // water spills to left
                else {
                    // for bar[left], we know lmax. but we don't know exact value of rmax
                    // but we are sure that it is greater than lmax
                    // so min(lmax, rmax) evaluates to lmax
                    water += lmax - bar[left];
                }
                left++;
            } else { // 9..2
                if(bar[right]>rmax)
                    rmax = bar[right]; // water spills to right
                else {
                    // for bar[right], we know rmax. but we don't know exact value of lmax
                    // but we are sure that it is greater than rmax
                    // so min(lmax, rmax) evaluates to rmax
                    water += rmax - bar[right];
                }
                right--;
            }
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
