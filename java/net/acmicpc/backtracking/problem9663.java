package net.acmicpc.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem9663 {

    static boolean[][] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(chess(N));
    }

    public static int chess(int N) {
        int result = 0;
        check = new boolean[3][];
        check[0] = new boolean[N];
        check[1] = new boolean[N * 2 + 1];//오른쪽대각선 정보
        check[2] = new boolean[N * 2 + 1];//왼쪽 대각성 정보 N기준 +-
        for (int i = 0; i < N; i++) {
            check[0][i] = true;
            check[1][N + i] = true;
            check[2][i] = true;
            result += chess(1, i, 1, N);
            check[0][i] = false;
            check[1][N + i] = false;
            check[2][i] = false;
        }
        return result;
    }

    public static int chess(int x, int y, int count, int N) {
        int result = 0;
        if (count == N) {
            return 1;
        }
        for (int i = 0; i < N; i++) {
            if (!check[0][i] && !check[1][N + i - x] && !check[2][i + x]) {
                check[0][i] = true;
                check[1][N + i - x] = true;
                check[2][i + x] = true;
                result += chess(x + 1, i, count + 1, N);
                check[0][i] = false;
                check[1][N + i - x] = false;
                check[2][i + x] = false;
            }
        }
        return result;
    }
}
