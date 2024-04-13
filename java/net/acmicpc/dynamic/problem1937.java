package net.acmicpc.dynamic;

import java.util.*;
import java.io.*;

/**
 * 욕심쟁이 판다 https://www.acmicpc.net/problem/1937
 */
public class problem1937 {
    final static int[] dx = {1, 0, -1, 0};
    final static int[] dy = {0, 1, 0, -1};
    static int n;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))){
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }
        bw.write(max+"\n");
        bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static int dfs(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        dp[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && ny < n && nx < n) {
                if (map[x][y] < map[nx][ny]) {
                    dp[x][y] = Math.max(dp[x][y], dfs(nx,ny)+1);
                }
            }
        }
        return dp[x][y];
    }
}
