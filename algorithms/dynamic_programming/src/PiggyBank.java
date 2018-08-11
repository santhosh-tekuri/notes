public class PiggyBank {
    static int minAmount(int E, int F, int p[], int w[]) {
        int m[] = new int[F-E+1];
        m[0] = 0;
        for(int k=1; k<m.length; k++) {
            m[k] = -1;
            for(int i=0; i<p.length; i++) {
                if(w[i]<=k && m[k-w[i]]!=-1 && (m[k]==-1 || m[k-w[i]] + p[i]<m[k])) {
                    m[k] = m[k-w[i]] + p[i];
                }
            }
        }
        return m[m.length-1];
    }

    public static void main(String[] args) {
        System.out.println(minAmount(10, 110, new int[]{1, 30}, new int[]{1, 50}));
        System.out.println(minAmount(10, 110, new int[]{1, 50}, new int[]{1, 30}));
        System.out.println(minAmount(1, 6, new int[]{10, 20}, new int[]{3, 4}));

        // Output:
        // 60
        // 100
        // -1
    }
}
