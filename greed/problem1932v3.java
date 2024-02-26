package net.acmicpc.greed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//객체지향적으로 다시작성해본코드
public class problem1932v3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> inputLines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            inputLines.add(br.readLine());
        }
        Triangle triangle = new Triangle(inputLines);

        System.out.println(triangle.findMaximumTotal());


    }

    static class Triangle {
        private int[][] triangle;
        private int size;
        private int[][] dp;
        private boolean hasCalculate;

        public Triangle(List<String> inputLines) {
            initializeTriangle(inputLines);
        }

        private void initializeTriangle(List<String> inputLines) {
            this.size = inputLines.size();
            this.triangle = new int[size][size];
            this.dp = new int[size][size];
            for (int i = 0; i < size; i++) {
                String[] numbers = inputLines.get(i).split(" ");
                for (int j = 0; j <= i; j++) {
                    triangle[i][j] = Integer.parseInt(numbers[j]);
                }
            }
        }

        public int findMaximumTotal() {
            if (hasCalculate) {
                return dp[0][0];
            }
            for (int i = triangle.length - 2; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
                }
            }
            hasCalculate=true;
            return dp[0][0];
        }
    }
}
