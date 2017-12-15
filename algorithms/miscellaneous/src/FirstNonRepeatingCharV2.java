/**
 * @author Santhosh Kumar Tekuri
 */
public class FirstNonRepeatingCharV2 {
    static int firstNonRepeating(String str) {
        int count[] = new int[256];
        int firstIndex[] = new int[256];
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            count[ch]++;
            if(count[ch]==1)
                firstIndex[ch] = i;
        }

        int ans = -1;
        for(int ch=0; ch<256; ch++) {
            if(count[ch]==1)
                ans = ans==-1 ? firstIndex[ch] : Math.min(ans, firstIndex[ch]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(firstNonRepeating("GeeksforGeeks"));
        System.out.println(firstNonRepeating("GeeksQuiz"));
        System.out.println(firstNonRepeating("step on no pets"));

        // Output:
        // 5
        // 0
        // -1
    }
}
