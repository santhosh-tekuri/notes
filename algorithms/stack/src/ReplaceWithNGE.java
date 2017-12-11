import java.util.*;

/**
 * @author Santhosh Kumar Tekuri
 */
public class ReplaceWithNGE {
    static void replaceWithNGE(int a[]) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<a.length; i++) {
            while(!stack.isEmpty() && a[stack.peek()]<a[i])
                a[stack.pop()] = a[i];
            stack.push(i);
        }

        while(!stack.isEmpty())
            a[stack.pop()] = -1;
    }

    public static void main(String[] args) {
        run(new int[]{4, 5, 2, 25});
        run(new int[]{13, 7, 6, 12, 15});

        // Output:
        // [5, 25, 25, -1]
        // [15, 12, 12, 15, -1]
    }

    static void run(int a[]) {
        replaceWithNGE(a);
        System.out.println(Arrays.toString(a));
    }
}
