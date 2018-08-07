public class LongestPalindromicSubsequence {
    static int lps(char s[]) {
        int n = s.length;
        int L[][] = new int[n][n];
        for(int i=0; i<n; i++)
            L[i][i] = 1;
        for(int len=2; len<=n; len++) {
            for(int i=0,j=i+len-1; j<n; i++,j++) {
                if(s[i]==s[j]) {
                    L[i][j] = 2;
                    if(len>2)
                        L[i][j] += L[i+1][j-1];
                } else
                    L[i][j] = Math.max(L[i+1][j], L[i][j-1]);
            }
        }
        return L[0][n-1];
    }

    public static void main(String[] args) {
        System.out.println(lps("bbbab".toCharArray()));
        System.out.println(lps("cbbd".toCharArray()));
        System.out.println(lps("BBABCBCAB".toCharArray()));
        System.out.println(lps("MAHDYNAMICPROGRAMZLETMESHOWYOUTHEM".toCharArray()));

        // Output:
        // 4
        // 2
        // 7
        // 11
    }
}
