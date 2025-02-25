import java.util.Scanner;

public class MatrixMultiplication {
    
    public static void onMult(int size) {
        double[][] A = new double[size][size];
        double[][] B = new double[size][size];
        double[][] C = new double[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = 1.0;
                B[i][j] = i + 1;
            }
        }
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double temp = 0;
                for (int k = 0; k < size; k++) {
                    temp += A[i][k] * B[k][j];
                }
                C[i][j] = temp;
            }
        }
        
        long endTime = System.nanoTime();
        System.out.printf("Time: %.3f seconds\n", (endTime - startTime) / 1e9);
        
        System.out.println("Result matrix: ");
        for (int j = 0; j < Math.min(10, size); j++) {
            System.out.print(C[0][j] + " ");
        }
        System.out.println();
    }
    
    public static void onMultLine(int size) {
        double[][] A = new double[size][size];
        double[][] B = new double[size][size];
        double[][] C = new double[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = 1.0;
                B[i][j] = i + 1;
            }
        }
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                double temp = A[i][k];
                for (int j = 0; j < size; j++) {
                    C[i][j] += temp * B[k][j];
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.printf("Time: %.3f seconds\n", (endTime - startTime) / 1e9);
        
        System.out.println("Result matrix: ");
        for (int j = 0; j < Math.min(10, size); j++) {
            System.out.print(C[0][j] + " ");
        }
        System.out.println();
    }
    
    public static void onMultBlock(int size, int blockSize) {
        double[][] A = new double[size][size];
        double[][] B = new double[size][size];
        double[][] C = new double[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = 1.0;
                B[i][j] = i + 1;
            }
        }
        
        long startTime = System.nanoTime();
        
        for (int i_bk = 0; i_bk < size; i_bk += blockSize) {
            for (int k_bk = 0; k_bk < size; k_bk += blockSize) {
                for (int j_bk = 0; j_bk < size; j_bk += blockSize) {
                    for (int i = i_bk; i < Math.min(i_bk + blockSize, size); i++) {
                        for (int k = k_bk; k < Math.min(k_bk + blockSize, size); k++) {
                            double temp = A[i][k];
                            for (int j = j_bk; j < Math.min(j_bk + blockSize, size); j++) {
                                C[i][j] += temp * B[k][j];
                            }
                        }
                    }
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.printf("Time: %.3f seconds\n", (endTime - startTime) / 1e9);
        
        System.out.println("Result matrix: ");
        for (int j = 0; j < Math.min(10, size); j++) {
            System.out.print(C[0][j] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        
        do {
            System.out.println("\n1. Multiplication");
            System.out.println("2. Line Multiplication");
            System.out.println("3. Block Multiplication");
            System.out.print("Selection?: ");
            option = scanner.nextInt();
            
            if (option == 0) break;
            
            System.out.print("Dimensions: lins=cols ? ");
            int size = scanner.nextInt();
            
            switch (option) {
                case 1:
                    onMult(size);
                    break;
                case 2:
                    onMultLine(size);
                    break;
                case 3:
                    System.out.print("Block Size? ");
                    int blockSize = scanner.nextInt();
                    onMultBlock(size, blockSize);
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (option != 0);
        
        scanner.close();
    }
}
