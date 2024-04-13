package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이친수
 * https://www.acmicpc.net/problem/2193
 */
public class problem2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int length= Integer.parseInt(br.readLine());
        long[][] dp=new long[2][length];
        dp[1][0]=1;
        for (int i = 1; i < length; i++) {
            dp[0][i]=dp[0][i-1]+dp[1][i-1];
            dp[1][i]=dp[0][i-1];
        }
//        int count=permutation(1);
        System.out.println(dp[0][length-1]+dp[1][length-1]);
    }
    //시간초과
//    static int permutation(int deep){
//        int count=0;
//        if(deep==pinaryNumber.length) return 1;
//        count+=permutation(deep+1);
//        if(!pinaryNumber[deep-1]){
//            pinaryNumber[deep]=true;
//            count+=permutation(deep+1);
//            pinaryNumber[deep]=false;
//        }
//        return count;
//    }

}
