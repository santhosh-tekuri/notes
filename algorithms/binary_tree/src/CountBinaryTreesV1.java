public class CountBinaryTreesV1 {
    static int nTrees(int n) {
        if(n==0)
            return 1;
        int s = 0;
        for(int m=0; m<n; m++)
            s += nTrees(m) * nTrees(n-1-m);
        return s;
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
