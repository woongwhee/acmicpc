package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] token = new int[N];
        for (int i = 0; i < N; i++) {
            token[i]=Integer.parseInt(br.readLine());
        }
        int[] dp = new int[K+1];
        dp[0]=1;
        for (int i = 0; i <N; i++) {
            for (int j = token[i]; j <= K; j++) {
                dp[j]+=dp[j-token[i]];
            }
        }
        System.out.println(dp[K]);
    }
}
