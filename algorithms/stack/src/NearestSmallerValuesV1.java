import java.util.Arrays;
import java.util.Stack;

/**
 * @author Santhosh Kumar Tekuri
 */
public class NearestSmallerValuesV1 {
    static int[] nearestSmallerValues(int a[]) {
        int n = a.length;
        int b[] = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && a[stack.peek()]>=a[i])
                stack.pop();
            b[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nearestSmallerValues(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15})));
        System.out.println(Arrays.toString(nearestSmallerValues(new int[]{1, 6, 4, 10, 2, 5})));
        System.out.println(Arrays.toString(nearestSmallerValues(new int[]{1, 3, 0, 2, 5})));
    }
}
