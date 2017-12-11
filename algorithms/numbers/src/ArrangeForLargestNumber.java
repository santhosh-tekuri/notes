import java.util.*;

/**
 * @author Santhosh Kumar Tekuri
 */
public class ArrangeForLargestNumber {
    static void arrange(int a[]) {
        Collections.sort(new IntList(a), (n1, n2) -> {
            String s1 = String.valueOf(n1);
            String s2 = String.valueOf(n2);
            return (s2+s1).compareTo(s1+s2);
        });
    }

    public static void main(String[] args) {
        run(new int[]{3, 1, 4, 2});
        run(new int[]{54, 546, 548, 60});
        run(new int[]{1, 34, 3, 98, 9, 76, 45, 4});
        run(new int[]{123, 124, 56, 90});

        // Output:
        // [4, 3, 2, 1]
        // [60, 548, 546, 54]
        // [9, 98, 76, 45, 4, 34, 3, 1]
        // [90, 56, 124, 123]
    }

    static void run(int a[]) {
        arrange(a);
        System.out.println(Arrays.toString(a));
    }

    static class IntList extends AbstractList<Integer>{
        private int a[];

        IntList(int[] a) {
            this.a = a;
        }

        @Override
        public Integer get(int index) {
            return a[index];
        }

        @Override
        public int size() {
            return a.length;
        }

        @Override
        public Integer set(int index, Integer element) {
            int old = a[index];
            a[index] = element;
            return old;
        }
    };
}
