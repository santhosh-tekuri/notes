import java.util.*;

/**
 * @author Santhosh Kumar Tekuri
 */
public class LongestZigZagSubsequence {
    static List<Integer> lzs(int x[]) {
        List<Integer> lzs = new ArrayList<>();
        for(int v: x) {
            if(lzs.isEmpty())
                lzs.add(v);
            else if(lzs.get(lzs.size()-1)!=v) {
                if(lzs.size()==1)
                    lzs.add(v);
                else if((v-lzs.get(lzs.size()-1))*(lzs.get(lzs.size()-1)-lzs.get(lzs.size()-2)) < 0)
                    lzs.add(v);
                else
                    lzs.set(lzs.size()-1, v);
            }
        }
        return lzs;
    }

    public static void main(String[] args) {
        System.out.println(lzs(new int[]{4, 7, 4, 3, 1, 6, 7, 5, 1, 7}));
        System.out.println(lzs(new int[]{10, 22, 9, 33, 49, 50, 31, 60}));

        // Output:
        // [4, 7, 1, 7, 1, 7]
        // [10, 22, 9, 50, 31, 60]
    }
}
