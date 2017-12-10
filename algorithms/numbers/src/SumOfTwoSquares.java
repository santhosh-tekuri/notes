/**
 * @author Santhosh Kumar Tekuri
 */
public class SumOfTwoSquares {
    static void sumOfSquares(int n) {
        int x = 0; // min value possible
        int y = (int)Math.sqrt(n); // max value possible
        while(x<=y) {
            int sum = x*x + y*y;
            if(sum==n) {
                System.out.println(x+", "+y);
                x++; y--;
            } else if(sum<n)
                x++;
            else
                y--;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        sumOfSquares(10);
        sumOfSquares(25);
        sumOfSquares(100);

        // Output:
        // 1, 3
        //
        // 0, 5
        // 3, 4
        //
        // 0, 10
        // 6, 8
    }
}
