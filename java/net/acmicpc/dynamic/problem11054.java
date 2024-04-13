package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class problem11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int length= Integer.parseInt(br.readLine());
        int[] sequence =new int[length];
        String[] split=br.readLine().split(" ");
        for (int i = 0; i <length ; i++) {
            sequence[i]= Integer.parseInt(split[i]);
        }
        int[][] dp=new int[2][length];
        Arrays.fill(dp[0],1);
        Arrays.fill(dp[1],1);
        for (int i = 1; i < length; i++) {
            for (int j = 0; j <i ; j++) {
                if(sequence[i]>sequence[j]){
                    dp[0][i]=Math.max(dp[0][j]+1,dp[0][i]);
                }
            }
        }
        for (int i = length-1; i >=0; i--) {
            for (int j = length-1; j>i ; j--) {
                if(sequence[i]>sequence[j]){
                    dp[1][i]=Math.max(dp[1][j]+1,dp[1][i]);
                }
            }
        }
        int max=0;
        for (int i = 0; i < length; i++) {
            max=Math.max(max,dp[0][i]+dp[1][i]-1 );
        }
        System.out.println(max);
    }
}
