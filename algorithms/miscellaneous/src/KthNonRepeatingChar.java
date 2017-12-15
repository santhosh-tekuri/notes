import java.util.Arrays;

/**
 * @author Santhosh Kumar Tekuri
 */
public class KthNonRepeatingChar {
    static int kthNonRepating(String str, int k) {
        int n = str.length();

        int count[]=new int[256], firstIndex[]=new int[256];
        Arrays.fill(firstIndex, str.length());
        for(int i=0; i<n; i++) {
            char ch = str.charAt(i);
            count[ch]++;
            firstIndex[ch] = count[ch]==1 ? i : n;
        }

        Arrays.sort(firstIndex);
        return firstIndex[k-1]==n ? -1 : firstIndex[k-1];
    }

    public static void main(String[] args) {
        System.out.println(kthNonRepating("geeksforgeeks", 3));
        System.out.println(kthNonRepating("GeeksforGeeks", 1));
        System.out.println(kthNonRepating("GeeksforGeeks", 4));

        // Output:
        // 7
        // 5
        // -1
    }
}
