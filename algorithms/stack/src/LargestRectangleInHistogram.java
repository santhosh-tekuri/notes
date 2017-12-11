import java.util.Stack;

/**
 * @author Santhosh Kumar Tekuri
 */
public class LargestRectangleInHistogram {
    static int maxArea(int hist[]) {
        int n = hist.length;

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<=n; i++) {
            while(!stack.isEmpty() && (i==n || hist[stack.peek()]>hist[i])) {
                int minBar = hist[stack.pop()];
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int area = minBar*(i-leftIndex-1);
                if(area>maxArea)
                    maxArea = area;
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{6, 2, 5, 4, 5, 1, 6}));
        System.out.println(maxArea(new int[]{7, 2, 1, 4, 5, 1, 3, 3}));
        System.out.println(maxArea(new int[]{4, 1000, 1000, 1000, 1000}));

        // Output:
        // 12
        // 8
        // 4000
    }
}
