import java.util.Stack;

/**
 * @author Santhosh Kumar Tekuri
 */
public class LargestRectangleInBinaryMatrix {
    static int maxArea(int m[][]) {
        int r = m.length;
        int c = r==0 ? 0 : m[0].length;

        int hist[] = new int[c];

        for(int j=0; j<c; j++)
            hist[j] = m[0][j];
        int ans = maxArea(hist);

        for(int i=1; i<r; i++) {
            for(int j=0; j<c; j++)
                hist[j] = m[i][j]==1 ? hist[j]+1 : 0;
            ans = Math.max(ans, maxArea(hist));
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[][]{
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
        }));
        System.out.println(maxArea(new int[][]{
            {1, 0, 1, 0, 0},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0},
        }));

        // Output:
        // 8
        // 6
    }

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
}
