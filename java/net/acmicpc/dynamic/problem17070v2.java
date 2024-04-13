package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem17070v2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = split[j].charAt(0) == '0';
            }
        }
        int result = dynamic(map);
        System.out.println(result);
    }

    public static int dynamic(boolean[][] map) {
        int[][][] dp = new int[3][map.length][map.length];//가로,세로,대각선
        int n = map.length;
        //첫줄 초기화 첫줄은 무조건 가로로만 도달할수 있다.
        Arrays.fill(dp[0][0], 1);
        dp[0][0][0] = 0;
        //만약 첫줄에서 벽에 부딛치면 더이상 진행하지않음
        for (int i = 2; i < n; i++) {
            if (!map[0][i]) {
                for (int j = i; j < n; j++) {
                    dp[0][0][j] = 0;
                }
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (!map[i][j]) {
                    continue;
                }
                dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1];
                dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
                if (map[i - 1][j] && map[i][j - 1]) {
                    dp[2][i][j] = dp[0][i-1][j - 1] + dp[1][i - 1][j-1] + dp[2][i - 1][j - 1];
                }
            }
        }
        n--;
        return dp[0][n][n] + dp[1][n][n] + dp[2][n][n];
    }


}
