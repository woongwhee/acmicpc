package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class problem1562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();
        long[][][] dp = new long[N + 1][10][1 << 10];

        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }
        int mod = 1000000000;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1 << 10; k++) {
                    int ik = k | (1 << j);
                    if (j > 0&&dp[i - 1][j - 1][k] > 0 ) {
                        dp[i][j][ik] += dp[i - 1][j - 1][k];
                        dp[i][j][ik] %= mod;
                    }
                    if (j<9&&dp[i - 1][j + 1][k] > 0) {
                        dp[i][j][ik] += dp[i - 1][j + 1][k];
                        dp[i][j][ik] %= mod;
                    }
                }
            }
        }
        long result = 0;
        for (int l = 0; l <= 9; l++) {
            result += dp[N][l][(1 << 10) - 1];
            result %= mod;
        }
        System.out.println(result);
    }
}
