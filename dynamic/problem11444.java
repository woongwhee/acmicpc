package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class problem11444 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BigInteger n = new BigInteger(br.readLine());
        long nl=(long)(Integer.MAX_VALUE)*2+10007;
        BigInteger n=BigInteger.valueOf(nl);
        long fibonachi = fibonachi(n);
        System.out.println(fibonachi);
    }

    public static long fibonachi(BigInteger n) {
        int limit=1000000007;
        int[] dp = new int[limit];
         dp[0] = 0;
         dp[1] = 1;
        long result = 1;
        n = n.subtract(BigInteger.valueOf(1));
        while (!n.equals(0)) {
            int beforeLast = dp[dp.length - 2];
            int last = dp[dp.length - 1];
            if(last==0){
                last=1;
            }
            result = last;
            if (n.compareTo(BigInteger.valueOf(limit-1)) > 0) {
                n = n.subtract(BigInteger.valueOf(limit-1));
                dp[0] = beforeLast + last <= 1000000007 ? beforeLast + last : (beforeLast + last) % 1000000007;
                dp[1] = last + dp[0] <= 1000000007 ? dp[0] + last : (dp[0] + last) % 1000000007;
                for (int i = 2; i < Integer.MAX_VALUE-1; i++) {
                    int cur = dp[i - 2] + dp[i - 1];
                    dp[i] = cur <= 1000000007 ? cur : cur % 1000000007;
                }

            } else {
                int size = n.intValue();
                n = new BigInteger("0");
                dp[0] = beforeLast + last <= 1000000007 ? beforeLast + last : (beforeLast + last) % 1000000007;
                dp[1] = last + dp[0] <= 1000000007 ? dp[0] + last : (dp[0] + last) % 1000000007;
                if (size < 2) {
                    result = dp[0];
                }
                for (int i = 2; i < size; i++) {
                    int cur = dp[i - 2] + dp[i - 1];
                    dp[i] = cur <= 1000000007 ? cur : cur % 1000000007;
                }
                result = dp[size - 1];
                break;
            }
        }
        return result;
    }
}
