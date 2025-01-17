package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class problem27519 {
    public static boolean[] primeNum(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        int count = 0;
        int t= (int) Math.sqrt(n)+1;
        for (int i = 2; i <= t; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    public static long[] countCase(int n) {
        boolean[] isPrime = primeNum(n);
        long[] dp = new long[n+1];
        dp[0]=1;
        for (int i = 2; i <= n; i++) {
            if(isPrime[i]){
                for (int j = i; j <= n; j++) {
                    dp[j] += dp[j-i];
                    dp[j] %=1000000007L;
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        StringBuffer sb=new StringBuffer();
        int[] caseArr=new int[N];
        int max=1;
        for (int i = 0; i < N; i++) {
            caseArr[i]=Integer.parseInt(br.readLine());
            max=Math.max(max,caseArr[i]);
        }
        long[] dp=countCase(max);
        for (int i = 0; i < N; i++) {
            sb.append(dp[caseArr[i]]);
            sb.append("\n");
        }
        System.out.println(sb.toString());


    }
}
