/**
 * @author Santhosh Kumar Tekuri
 */
public class MinJumpsV1 {
    static int minJumps(int a[]) {
        int n = a.length;
        int m[] = new int[n], b[]=new int[n];
        m[n-1] = 0;
        b[n-1] = n-1;
        for(int i=n-2; i>=0; i--) {
            if(a[i]==0)
                m[i] = Integer.MAX_VALUE;
            else if(i+a[i]>=n-1) {
                m[i] = 1;
                b[i] = n-1;
            } else {
                m[i] = Integer.MAX_VALUE;
                for(int k=1; k<=a[i]; k++) {
                    if(m[i+k]<m[i]) {
                        m[i] = m[i+k];
                        b[i] = i+k;
                    }
                }
                m[i]++;
            }
        }
        if(m[0]!=Integer.MAX_VALUE)
            printPath(b);
        return m[0]==Integer.MAX_VALUE ? -1 : m[0];
    }

    static void printPath(int b[]) {
        int n = b.length;
        int i = 0;
        while(i!=n-1) {
            System.out.print(i+" ");
            i = b[i];
        }
        System.out.println(n-1);
    }

    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{1,3,5,8,9,2,6,7,6,8,9}));
        System.out.println(minJumps(new int[]{5,4,1,1,1,3,5}));

        // Output:
        // 0 1 3 10
        // 3
        // 0 5 6
        // 2
    }
}
