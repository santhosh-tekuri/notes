/**
 * @author Santhosh Kumar Tekuri
 */
public class CheckAnagrams {
    static boolean areAnagrams(char s1[], char s2[]) {
        int count[] = new int[256];
        int nonZeros = 0;
        for(char ch: s1) {
            if(count[ch]==0)
                nonZeros++;
            count[ch]++;
        }

        for(char ch: s2) {
            if(count[ch]==0)
                nonZeros++;
            count[ch]--;
            if(count[ch]==0)
                nonZeros--;
        }

        return nonZeros==0;
    }

    public static void main(String[] args) {
        run("anagram", "nagaram");
        run("anagram", "xagaram");

        // Output:
        // true
        // false
    }

    static void run(String s1, String s2) {
        System.out.println(areAnagrams(s1.toCharArray(), s2.toCharArray()));
    }
}
