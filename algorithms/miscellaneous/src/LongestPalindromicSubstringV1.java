import java.util.Arrays;

/**
 * @author Santhosh Kumar Tekuri
 */
public class LongestPalindromicSubstringV1 {
    static char[] lps(char s[]) {
        int n = s.length;

        // beware of empty string check
        if(n==0)
            return new char[0];

        // construct transformed string
        char t[] = new char[2*n+1];
        for(int i=0; i<n; i++) {
            t[2*i] = '#';
            t[2*i+1] = s[i];
        }
        t[t.length-1] = '#';

        int m[] = new int[t.length];
        int maxi = 0;
        m[0]=0;
        int c=0, r=0; // reference palindrome's center and right
        for(int i=1; i<m.length; i++) {
            if(i<r) {
                int j = 2*c-i; // i's mirror
                m[i] = Math.min(r-i, m[j]);
            } else
                m[i] = 0;

            if(i+m[i]>=r) {
                // expand
                int x=i-m[i]-1, y=i+m[i]+1;
                while(x>=0 && y<t.length && t[x]==t[y]) {
                    m[i]++; x--; y++;
                }

                if(i+m[i]>r) {
                    c=i; r=i+m[i]; // update reference palindrome
                    if(m[i]>m[maxi])
                        maxi = i;
                }
            }
        }

        int i=maxi-m[maxi], j=maxi+m[maxi];
        assert t[i]=='#' && t[j]=='#';
        return Arrays.copyOfRange(s, (i+1)/2, (j-1)/2+1);
    }

    public static void main(String[] args) {
        run("forgeeksskeegfor");
        run("abaaba");
        run("abcbabcbabcba");
        run("baananas");
        run("abracadabra");

        // Output:
        // geeksskeeg
        // abaaba
        // abcbabcbabcba
        // anana
        // aca
    }

    static void run(String str) {
        System.out.println(new String(lps(str.toCharArray())));
    }
}
