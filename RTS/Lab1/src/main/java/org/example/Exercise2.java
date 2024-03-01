package org.example;

public class Exercise2 {
    static class Matrix {
        private int[][] mat;

        public Matrix(int[][] data) {
            this.mat = data;
        }

        public int[][] getMat() {
            return mat;
        }
        public Matrix add(Matrix other) {
            int[][] result = new int[mat.length][mat[0].length];
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    result[i][j] = this.mat[i][j] + other.getMat()[i][j];
                }
            }
            return new Matrix(result);
        }
        public Matrix multiply(Matrix other) {
            int[][] result = new int[mat.length][other.getMat()[0].length];
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < other.getMat()[0].length; j++) {
                    for (int k = 0; k < mat[0].length; k++) {
                        result[i][j] += this.mat[i][k] * other.getMat()[k][j];
                    }
                }
            }
            return new Matrix(result);
        }

        public void print() {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    System.out.print(mat[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    public static void main(String[] args) {
        int[][] data1 = {{2, 3, 1}, {7, 1, 6}, {9, 2, 4}};
        int[][] data2 = {{8, 5, 3}, {3, 9, 2}, {2, 7, 3}};

        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);

        System.out.println("Matrix 1:");
        matrix1.print();
        System.out.println();

        System.out.println("Matrix 2:");
        matrix2.print();
        System.out.println();

        System.out.println("Sum:");
        Matrix sum = matrix1.add(matrix2);
        sum.print();
        System.out.println();

        System.out.println("Product:");
        Matrix product = matrix1.multiply(matrix2);
        product.print();
    }
}
