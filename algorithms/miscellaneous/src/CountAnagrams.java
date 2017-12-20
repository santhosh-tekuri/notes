/**
 * @author Santhosh Kumar Tekuri
 */
public class CountAnagrams {
    static int countAnagrams(char word[], char text[]) {
        int count[] = new int[256];
        int nonZeros = 0;
        for(char ch: word) {
            if(count[ch]==0)
                nonZeros++;
            count[ch]++;
        }

        int anagrams = 0;
        for(int i=0; i<text.length; i++) { // i is end of sliding window
            char ch = text[i];

            // add ch to sliding window
            if(count[ch]==0) nonZeros++;
            count[ch]--;
            if(count[ch]==0) nonZeros--;

            if(i>=word.length) {
                ch = text[i-word.length];
                // remove ch from sliding window
                if(count[ch]==0) nonZeros++;
                count[ch]++;
                if(count[ch]==0) nonZeros--;
            }

            if(nonZeros==0) {
                anagrams++;
                System.out.println("anagram found at position: "+ (i-word.length+1));
            }
        }
        return anagrams;
    }

    public static void main(String[] args) {
        run("for", "forxxorfxdofr");
        run("for", "forf");

        // Output:
        // anagram found at position: 0
        // anagram found at position: 5
        // anagram found at position: 10
        // 3
        // anagram found at position: 0
        // anagram found at position: 1
        // 2
    }

    static void run(String s1, String s2) {
        System.out.println(countAnagrams(s1.toCharArray(), s2.toCharArray()));
    }
}
