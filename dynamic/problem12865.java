package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 평범한 배낭
 * https://www.acmicpc.net/problem/12865
 */
public class problem12865 {
    static int N,K;
    static int[] V,W;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());//물품수
        K=Integer.parseInt(st.nextToken());//최대 적재 중량
        W=new int[N+1];
        V=new int[N+1];
        for (int i = 1; i <=N; i++) {
            st=new StringTokenizer(br.readLine()," ");
            W[i]= Integer.parseInt(st.nextToken());
            V[i]= Integer.parseInt(st.nextToken());
        }

        dp=new long[N+1][K+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if(W[i]>j){
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-W[i]]+V[i]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }

}
