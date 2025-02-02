package Tasks1;
public class MatrixMultiplication {

    // Method to multiply two matrices using multithreading
    public int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) throws InterruptedException {
        int m = matrixA.length;    // Number of rows in matrixA
        int n = matrixA[0].length; // Number of columns in matrixA
        int p = matrixB[0].length; // Number of columns in matrixB
        
        // Resultant matrix of size m x p
        int[][] result = new int[m][p];
        
        // Create threads for each row of the result matrix
        Thread[] threads = new Thread[m];
        
        for (int i = 0; i < m; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < p; j++) {
                    result[row][j] = 0;
                    for (int k = 0; k < n; k++) {
                        result[row][j] += matrixA[row][k] * matrixB[k][j];
                    }
                }
            });
        }

        // Start each thread
        for (int i = 0; i < m; i++) {
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < m; i++) {
            threads[i].join();
        }

        return result;
    }

    public static void main(String[] args) {
        MatrixMultiplication matrixMultiplication = new MatrixMultiplication();

        // Sample Input Matrices
        int[][] matrixA = {
            {1, 2},
            {3, 4}
        };

        int[][] matrixB = {
            {2, 0},
            {1, 2}
        };

        try {
            // Perform Matrix Multiplication
            int[][] result = matrixMultiplication.multiplyMatrices(matrixA, matrixB);
            
            // Print the result matrix
            System.out.println("Result of the multiplication:");
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
