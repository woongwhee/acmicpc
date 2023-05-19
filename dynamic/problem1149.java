package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[][] price=new int[n+1][3];
        int[][] dp=new int[n+1][3];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st=new StringTokenizer(br.readLine()," ");
            for (int j = 0; j <3; j++) {
                price[i][j]= Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0]=Math.min(dp[i-1][1],dp[i-1][2])+price[i][0];
            dp[i][1]=Math.min(dp[i-1][0],dp[i-1][2])+price[i][1];
            dp[i][2]=Math.min(dp[i-1][0],dp[i-1][1])+price[i][2];
        }
        int result=Math.min(dp[n][0],Math.min(dp[n][1],dp[n][2]));
        System.out.println(result);

    }
}
