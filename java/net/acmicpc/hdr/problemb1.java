package net.acmicpc.hdr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problemb1 {
    public static double log2(long x) {
        return Math.log(x) / Math.log(2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer query = new StringTokenizer(br.readLine());
        int[] dp = new int[64];
        for (int i = 0; i < n; i++) {
            long cur = Long.parseLong(query.nextToken());
            if (cur != 0) {
                dp[(int) log2(cur)]++;
            }
        }
        long cur = 1;
        long max = 0;
        if (dp[0] != 0) {
            dp[1]=dp[0]/2;
            max=1;
        }
        for (int i = 1; i < 64; i++) {
            cur *= 2;
            if (dp[i] == 0) {
                continue;
            }
            max = cur;
            dp[i + 1] += dp[i] / 2;
        }
        System.out.println(max);
    }
}
