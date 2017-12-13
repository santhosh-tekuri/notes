/**
 * @author Santhosh Kumar Tekuri
 */
public class ModifyBooleanMatrixV1 {
    static void modifyBooleanMatrix(int A[][]) {
        int m = A.length;
        int n = m==0 ? 0 : A[0].length;

        int row[] = new int[m], col[] = new int[n]; // initially zeroed
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                row[i] |= A[i][j];
                col[j] |= A[i][j];
            }
        }

        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++)
                A[i][j] = row[i] | col[j];
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
