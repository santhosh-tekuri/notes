/**
 * @author Santhosh Kumar Tekuri
 */
public class MinJumpsV2 {
    static int minJumps(int a[]) {
        int n = a.length;
        int i=0, m=0, maxReach=a[0];
        System.out.println("position 0");
        if(n!=1) {
            while(true) {
                m++;
                if(maxReach>=n-1)
                    break;
                if(i==maxReach)
                    return -1; // impossible
                int v=0, maxk=0;
                for(int k=i+1; k<=maxReach; k++) {
                    if(k+a[k]>v) {
                        v = k+a[k];
                        maxk = k;
                    }
                }
                System.out.println("position "+maxk);
                i = maxReach;
                maxReach = v;
            }
            System.out.println("position "+(n-1));
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{1,3,5,8,9,2,6,7,6,8,9}));
        System.out.println(minJumps(new int[]{5,4,1,1,1,3,5}));

        // Output:
        // position 0
        // position 1
        // position 4
        // position 10
        // 3
        // position 0
        // position 5
        // position 6
        // 2
    }
}
