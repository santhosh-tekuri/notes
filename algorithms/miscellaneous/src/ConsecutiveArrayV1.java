/**
 * @author Santhosh Kumar Tekuri
 */
public class ConsecutiveArrayV1 {
    static boolean isConsecutive(int a[]) {
        int n = a.length;

        if(n<2)
            return true;

        int max = max(a);
        int min = min(a);

        if(max-min+1!=a.length)
            return false;

        // check duplicates
        boolean visited[] = new boolean[n];
        for(int x: a) {
            if(visited[x-min])
                return false; // found duplicate
            visited[x-min] = true;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isConsecutive(new int[]{5, 2, 3, 1, 4}));
        System.out.println(isConsecutive(new int[]{83, 78, 80, 81, 79, 82}));
        System.out.println(isConsecutive(new int[]{34, 23, 52, 12, 3}));
        System.out.println(isConsecutive(new int[]{7, 6, 5, 5, 3, 4}));
        System.out.println(isConsecutive(new int[]{5, 2, 2, 1, 3, 8, 7, 8}));
        System.out.println(isConsecutive(new int[]{ 2, 4, 4, 6, 4}));
        System.out.println(isConsecutive(new int[]{2, 1, 0, -3, -1, -2}));

        // Output:
        // true
        // true
        // false
        // false
        // false
        // false
        // true
    }

    static int min(int a[]) {
        int r = a[0];
        for(int v: a)
            r = Math.min(r, v);
        return r;
    }

    static int max(int a[]) {
        int r = a[0];
        for(int v: a)
            r = Math.max(r, v);
        return r;
    }
}
