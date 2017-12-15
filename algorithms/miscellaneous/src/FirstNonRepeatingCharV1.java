/**
 * @author Santhosh Kumar Tekuri
 */
public class FirstNonRepeatingCharV1 {
    static int firstNonRepeating(String str) {
        int count[] = new int[256];
        for(char ch: str.toCharArray())
            count[ch]++;
        for(int i=0; i<str.length(); i++) {
            if(count[str.charAt(i)]==1)
                return i;
        }
        return -1;
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
