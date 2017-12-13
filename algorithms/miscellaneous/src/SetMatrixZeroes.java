/**
 * @author Santhosh Kumar Tekuri
 */
public class SetMatrixZeroes {
    static void setZeroes(int A[][]) {
        int m = A.length;
        int n = m==0 ? 0 : A[0].length;

        boolean row0 = false;
        for(int j=0; j<n; j++)
            row0 |= A[0][j]==0;

        boolean col0 = false;
        for(int i=0; i<m; i++)
            col0 |= A[i][0]==0;

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(A[i][j]==0) {
                    A[0][j] = 0;
                    A[i][0] = 0;
                }
            }
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(A[i][0]==0 || A[0][j]==0)
                    A[i][j] = 0;
            }
        }

        // modify 0th row if there were any 1
        if(row0) {
            for(int j=0; j<n; j++)
                A[0][j] = 0;
        }

        // modify 0th col if there were any 1
        if(col0) {
            for(int i=0; i<m; i++)
                A[i][0] = 0;
        }
    }

    public static void main(String[] args) {
        run(new int[][]{
                {0, 5},
                {1, 7}
        });

        // Output:
        // 0 0
        // 0 7
        //
    }

    static void run(int A[][]) {
        setZeroes(A);
        for(int i=0; i<A.length; i++) {
            for (int j = 0; j < A[i].length; j++)
                System.out.print(A[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
