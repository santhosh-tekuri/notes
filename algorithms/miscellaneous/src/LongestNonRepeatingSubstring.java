/**
 * @author Santhosh Kumar Tekuri
 */
public class LongestNonRepeatingSubstring {
    // returns first and last index
    static int[] longestSubstringWithoutRepeating(char a[]){
        int n = a.length;
        int lastIndex[] = new int[256];
        for(int i=0; i<lastIndex.length; i++)
            lastIndex[i] = -1;

        int i = 0, j = 0; // ch[i..j-1] is the non-repeating substring
        int maxi = 0, maxj = 0;
        while(j<n) {
            char ch = a[j];
            if(lastIndex[ch]>=i) { // ch is repeated
                if(j-i>maxj-maxi) {
                    maxi = i;
                    maxj = j;
                }
                i = lastIndex[ch]+1;
            }
            lastIndex[ch] = j;
            j++;
        }
        if(j-i>maxj-maxi) {
            maxi = i;
            maxj = j;
        }
        return new int[]{maxi, maxj-1};
    }

    public static void main(String[] args) {
        run("ABDEFGABEF");
        run("BBBB");
        run("GEEKSFORGEEKS");

        // Output:
        // ABDEFG
        // B
        // EKSFORG
    }

    static void run(String str) {
        int index[] = longestSubstringWithoutRepeating(str.toCharArray());
        System.out.println(str.substring(index[0], index[1]+1));
    }
}
