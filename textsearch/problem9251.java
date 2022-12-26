package net.acmicpc.textsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * LCS
 */
public class problem9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        char[] cArr = origin.toCharArray();
        char[] cArr2 = br.readLine().toCharArray();
        int[][] dp = new int[cArr.length+1][cArr2.length+1];
        int max=Integer.MIN_VALUE;
        int maxEnd=0;
        for (int i = 1; i <= cArr.length; i++) {
            for (int j = 1; j <= cArr2.length; j++) {
                if(cArr[i-1]==cArr2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                    if(max<dp[i][j]){
                        max=dp[i][j];
                        maxEnd=i;
                    }
                }
            }
        }

        System.out.println(max);
        System.out.println(origin.substring(maxEnd-max,maxEnd));
    }
}
