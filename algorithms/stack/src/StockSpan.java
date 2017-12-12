import java.util.*;

/**
 * @author Santhosh Kumar Tekuri
 */
public class StockSpan {
    static int[] computeSpan(int price[]) {
        int n = price.length;
        int span[] = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && price[stack.peek()]<price[i])
                stack.pop();
            int peekIndex = stack.isEmpty() ? -1 : stack.peek();
            span[i] = i-peekIndex;
            stack.push(i);
        }
        return span;
    }

    public static void main(String[] args) {
        run(new int[]{100, 80, 60, 70, 60, 75, 85});
        run(new int[]{10, 4, 5, 90, 120, 80});

        // Output:
        // [1, 1, 1, 2, 1, 4, 6]
        // [1, 1, 2, 4, 5, 1]
    }

    static void run(int price[]) {
        System.out.println(Arrays.toString(computeSpan(price)));
    }
}
