import java.util.Stack;

/**
 * @author Santhosh Kumar Tekuri
 */
public class TrappingRainWaterV4 {
    static int findWater(int bar[]) {
        int n = bar.length;

        int water = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && bar[i]>bar[stack.peek()]) {
                int cur = stack.pop();
                if(!stack.isEmpty()) {
                    // compute water bounded by bar[cur]
                    int width = i - stack.peek() - 1;
                    int height = Math.min(bar[stack.peek()], bar[i]) - bar[cur];
                    water += width * height;
                }
            }
            stack.push(i);
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
