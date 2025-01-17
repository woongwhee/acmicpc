package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;

public class problem9254 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] M = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                M[i][j] = Integer.parseInt(split[j]);
            }
        }
        Matrix matrix = new Matrix(M);
        if (matrix.getDet() == 0) {
            System.out.println("no inverse");
            System.out.println(matrix.getDet());
            return;
        }
        DecimalFormat formatter = new DecimalFormat("#.##");
        double[][] inverseMatrix = matrix.getInverseMatrix();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(formatter.format(inverseMatrix[i][j]));
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

    //정수 행렬에 대한 행렬식값
    static class Matrix {
        private int[][] matrix;
        private int size;
        private int det;
        HashMap<String, Integer> dp;

        public Matrix(int[][] M) {
            for (int i = 0; i < M.length; i++) {
                if (M.length != M[i].length)
                    throw new IllegalArgumentException("The matrix is not square: The number of rows and columns does not match");
            }
            this.matrix = M;
            this.size = matrix.length;
            dp = new HashMap<>();
            this.det = determinant();
        }

        public int determinant() {
            boolean[] rows = new boolean[size];
            rows[0] = true;
            boolean[] cols = new boolean[size];
            return cofactor(rows, cols, 0, size);
        }

        public int getDet() {
            return det;
        }

        /**
         * 부분행렬에 대한 식별 키 구성되있는 행,행,행,-열,열,열, 로 이뤄저있다.
         *
         * @param rows
         * @param cols
         * @return
         */
        String createKey(boolean[] rows, boolean[] cols) {
            StringBuffer key = new StringBuffer();
            for (int i = 0; i < rows.length; i++) {
                if (!rows[i]) {
                    key.append(i);
                    key.append(',');
                }
            }
            key.append("&");
            for (int i = 0; i < cols.length; i++) {
                if (!cols[i]) {
                    key.append(i);
                    key.append(',');
                }
            }
            return key.toString();
        }

        /**
         * @param rows       제외된 행은 true 제외되지 않은 행은 false
         * @param cols       제외된 열은 true 제외되지 않은 열은 false
         * @param row        현재 선택된 행
         * @param matrixSize 부분행렬의 크기
         * @return
         */
        private int cofactor(boolean[] rows, boolean[] cols, int row, int matrixSize) {
            if (matrixSize == 1) {
                for (int i = 0; i < size; i++) {
                    if (!cols[i]) {
                        return matrix[row][i];
                    }
                }
            }
            int det = 0;
            int sign = 1;
            int nextRow = 0;
            rows[row]=true;
            for (int i = 0; i < size; i++) {
                if (!rows[i]) {
                    nextRow = i;
                    break;
                }
            }
            for (int i = 0; i < size; i++) {
                if (!cols[i]) {
                    cols[i] = true;
                    String key = createKey(rows, cols);
                    int cofactor = 0;
                    if (dp.containsKey(key)) {
                        cofactor = dp.get(key);
                    } else {
                        cofactor = cofactor(rows, cols, nextRow, matrixSize - 1);
                        dp.put(key,cofactor);
                    }
                    det += sign * matrix[row][i] * cofactor;
                    sign = -sign;
                    cols[i] = false;
                }
            }
            rows[row] = false;
            return det;
        }


        public double[][] getInverseMatrix() {
            if (size == 1) {
                double[][] inverse = {{(double) 1 / matrix[0][0]}};
                return inverse;
            }
            double[][] inverse = new double[matrix.length][matrix.length];
            boolean[] rows = new boolean[size];
            boolean[] cols = new boolean[size];
            int sign;
            int startRow = 1;
            rows[0] = true;
            for (int i = 0; i < size; i++) {
                if (startRow == i) {
                    startRow = startRow == 0 ? 1 : 0;
                    if (startRow == 1) {
                        rows[0] = true;
                        rows[1] = false;
                    } else {
                        rows[1] = true;
                        rows[0] = false;
                    }
                }
                rows[i] = true;
                sign = i % 2 == 0 ? 1 : -1;
                for (int j = 0; j < size; j++) {
                    cols[j] = true;
                    String key = createKey(rows, cols);
                    int cofactor = 0;
                    if (dp.containsKey(key)) {
                        cofactor = dp.get(key);
                    } else {
                        cofactor = cofactor(rows, cols, startRow, size - 1);
                        dp.put(key,cofactor);
                    }
                    inverse[j][i] = (double) sign * cofactor / det;
                    cols[j] = false;
                    sign = -sign;
                }
                rows[i] = false;
            }
            return inverse;
        }
    }

}
