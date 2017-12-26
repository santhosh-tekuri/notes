import java.util.*;

/**
 * @author Santhosh Kumar Tekuri
 */
public class DistanceMaximizingIncreasingPair {
    static int[] findPair(int a[]) {
        int n = a.length;

        // compute possible values for i in pair
        Stack<Integer> iValues = new Stack<>();
        iValues.push(0);
        for(int i=1; i<n; i++) {
            if(a[i]<a[iValues.peek()])
                iValues.push(i);
        }

        int maxi=0, maxj=0;
        for(int j=n-1; j>0; j--) {
            while(!iValues.isEmpty() && a[j]>a[iValues.peek()]) {
                int i = iValues.pop();
                if(j-i > maxj-maxi) {
                    maxi = i;
                    maxj = j;
                }
            }
            if(iValues.isEmpty())
                break;
        }

        if(maxj-maxi==0)
            return null;
        return new int[]{maxi, maxj};
    }

    public static void main(String[] args) {
        run(new int[]{4, 3, 5, 2, 1, 3, 2, 3});
        run(new int[]{34, 8, 10, 3, 2, 80, 30, 33, 1});
        run(new int[]{9, 2, 3, 4, 5, 6, 7, 8, 18, 0});
        run(new int[]{1, 2, 3, 4, 5, 6});
        run(new int[]{6, 5, 4, 3, 2, 1});
        run(new int[]{6, 5, 4, 3, 4, 5});

        // Output:
        // [3, 7]
        // [1, 7]
        // [0, 8]
        // [0, 5]
        // null
        // [2, 5]
    }

    static void run(int a[]) {
        System.out.println(Arrays.toString(findPair(a)));
    }
}
