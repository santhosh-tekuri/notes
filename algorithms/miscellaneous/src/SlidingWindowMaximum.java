import java.util.*;

/**
 * @author Santhosh Kumar Tekuri
 */
public class SlidingWindowMaximum {
    static int[] maxSlidingWindow(int a[], int w) {
        int n = a.length;
        assert w>0 && w>=n;
        Deque<Integer> q = new ArrayDeque<>(w);

        // initialize initial sliding window
        for(int i=0; i<w; i++){
            // remove all elements from end of q
            // that are < a[i]
            while(!q.isEmpty() && a[q.peekLast()]<a[i])
                q.removeLast();

            // add to window
            q.addLast(i);
        }

        int b[] = new int[n-w+1];
        for(int i=w; i<n; i++){
            b[i-w] = a[q.peekFirst()];

            // remove one element from sliding window
            if(!q.isEmpty() && q.peekFirst()<=i-w)
                q.removeFirst();

            // remove all elements from end of q
            // that are <= a[i]
            while(!q.isEmpty() && a[q.peekLast()]<a[i])
                q.removeLast();

            // add to window
            q.addLast(i);
        }

        // max of last window
        b[n-w] = a[q.peekFirst()];

        return b;
    }

    public static void main(String[] args) {
        run(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        run(new int[]{1, 2, 3, 1, 4, 5, 2, 3, 6}, 3);
        run(new int[]{8, 5, 10, 7, 9, 4, 15, 12, 90, 13}, 4);

        // Output:
        // [3, 3, 5, 5, 6, 7]
        // [3, 3, 4, 5, 5, 5, 6]
        // [10, 10, 10, 15, 15, 90, 90]
    }

    static void run(int a[], int w) {
        System.out.println(Arrays.toString(maxSlidingWindow(a, w)));
    }
}
