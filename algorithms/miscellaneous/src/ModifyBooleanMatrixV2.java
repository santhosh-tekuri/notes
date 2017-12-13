/**
 * @author Santhosh Kumar Tekuri
 */
public class ModifyBooleanMatrixV2 {
    static void modifyBooleanMatrix(int A[][]) {
        int m = A.length;
        int n = m==0 ? 0 : A[0].length;

        int row0 = 0;
        for(int j=0; j<n; j++)
            row0 |= A[0][j];

        int col0 = 0;
        for(int i=0; i<m; i++)
            col0 |= A[i][0];

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                A[0][j] |= A[i][j];
                A[i][0] |= A[i][j];
            }
        }

        for(int i=1; i<m; i++)
            for(int j=1; j<n; j++)
                A[i][j] = A[i][0] | A[0][j];

        // modify 0th row if there were any 1
        if(row0==1) {
            for(int j=0; j<n; j++)
                A[0][j] = 1;
        }

        // modify 0th col if there were any 1
        if(col0==1) {
            for(int i=0; i<m; i++)
                A[i][0] = 1;
        }
    }
    public static void main(String[] args) {
        run(new int[][]{
            {1, 0},
            {0, 0}
        });
        run(new int[][]{
            {0, 0, 0},
            {0, 0, 1}
        });
        run(new int[][]{
            {1, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 0, 0, 0},
        });

        // Output:
        // 1 1
        // 1 0
        //
        // 0 0 1
        // 1 1 1
        //
        // 1 1 1 1
        // 1 1 1 1
        // 1 0 1 1
    }

    static void run(int A[][]) {
        modifyBooleanMatrix(A);
        for(int i=0; i<A.length; i++) {
            for (int j = 0; j < A[i].length; j++)
                System.out.print(A[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
