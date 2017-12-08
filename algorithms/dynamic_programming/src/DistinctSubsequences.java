/**
 * @author Santhosh Kumar Tekuri
 */
public class DistinctSubsequences {
    static int countSubsequences(char x[], char z[]) {
        int m = x.length;
        int k = z.length;
        int c[][] = new int[k+1][m+1];
        for (int i=0; i<=k; i++) {
            for (int j=i; j<=m; j++) {
                if (i==0)
                    c[i][j] = 1;
                else if (j == 0)
                    c[i][j] = 0;
                else {
                    c[i][j] = c[i][j-1];
                    if (z[i-1] == x[j-1])
                        c[i][j] += c[i-1][j-1];
                }
            }
        }
        return c[k][m];
    }

    public static void main(String[] args) {
        run("babgbag", "bag");
        run("rabbbit", "rabbit");
        run("wweellccoommee to code qps jam", "welcome to code jam");
        run("So you've registered. We sent you a welcoming email, to welcome you to code jam. " +
                "But it's possible that you still don't feel welcomed to code jam. That's why we decided to name " +
                "a problem \"welcome to code jam.\" After solving this problem, we hope that you'll feel very " +
                "welcome. Very welcome, that is, to code jam", "welcome to code jam");

        // Output:
        // 5
        // 3
        // 256
        // 400263727
    }

    static void run(String x, String z) {
        System.out.println(countSubsequences(x.toCharArray(), z.toCharArray()));
    }
}
