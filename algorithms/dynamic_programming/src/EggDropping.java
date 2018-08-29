public class EggDropping {
    static int minDrops(int n, int k) {
        int d[][] = new int[n+1][k+1];
        for(int j=1; j<=k; j++)
            d[1][j] = j;
        for(int i=2; i<=n; i++) {
            for(int j=1; j<=k; j++) {
                d[i][j] = Integer.MAX_VALUE;
                for(int x=1; x<=j; x++)
                    d[i][j] = Math.min(d[i][j], Math.max(d[i - 1][x - 1], d[i][j - x]));
                d[i][j] += 1;
            }
        }
        return d[n][k];
    }

    public static void main(String[] args) {
        System.out.println(minDrops(2, 10));
        System.out.println(minDrops(2, 36));
        System.out.println(minDrops(2, 100));
        System.out.println(minDrops(3, 14));

        // Output:
        // 4
        // 8
        // 14
        // 4
    }
}
