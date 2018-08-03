public class CountBinaryTreesV2 {
    static int nTrees(int n) {
        int c[] = new int[n+1];
        c[0] = 1;
        for(int i=1; i<=n; i++) {
            int s = 0;
            for(int m=0; m<i; m++)
                s += c[m] * c[i-1-m];
            c[i] = s;
        }
        return c[n];
    }

    public static void main(String[] args) {
        System.out.println(nTrees(0));
        System.out.println(nTrees(3));
        System.out.println(nTrees(10));

        // Output:
        // 1
        // 5
        // 16796
    }
}
