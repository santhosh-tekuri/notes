import java.util.*;

/**
 * @author Santhosh Kumar Tekuri
 */
public class SubsequenceWithPairsGEk {
    static List<Integer> findSubsequence(int A[], int k) {
        List<Integer> solution = new ArrayList<>();

        // include all items that are >=k/2
        for(int item:A) {
            if(2*item>=k)
                solution.add(item);
        }

        if(solution.isEmpty())
            return null;

        // include any one item that is <k/2 satisfying constraint
        int m = min(solution);
        for(int item:A) {
           if(2*item<k && item+m>=k) {
               solution.add(item);
               break;
           }
        }

        return solution.size()==1 ? null : solution;
    }

    public static void main(String[] args) {
        System.out.println(findSubsequence(new int[]{-10, -100, 5, 2, 4, 7, 10, 23, 81, 5, 25}, 20));

        // Output:
        // [10, 23, 81, 25]
    }

    static int min(List<Integer> list) {
        int min = list.get(0);
        for(int v: list)
            min = Math.min(min, v);
        return min;
    }
}
