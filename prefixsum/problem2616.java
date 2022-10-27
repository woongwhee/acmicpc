package net.acmicpc.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        int[] S=new int[N+1];
        for (int i = 1; i <=N; i++) {
            int num=Integer.parseInt(st.nextToken());
            S[i]=num+S[i-1];
        }
        int M=Integer.parseInt(br.readLine());
        int[][] dp=new int[4][N+1];

        for (int i = 1; i <= 3; i++) {
            for (int j = i*M; j <= N; j++) {
                dp[i][j]= Math.max(dp[i][j-1],dp[i-1][j-M]+(S[j]-S[j-M]));
            }
        }
        System.out.printf("%d",dp[3][N]);
    }

}
