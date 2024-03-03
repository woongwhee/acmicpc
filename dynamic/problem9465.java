package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int testCount= Integer.parseInt(br.readLine());
        Data[] testCase=new Data[testCount];
        for (int i = 0; i < testCount; i++) {
            int n= Integer.parseInt(br.readLine());
            testCase[i]=new Data(new int[2][n]);
            for (int j = 0; j < 2; j++) {
                String[] input=br.readLine().split(" ");
                for (int k = 0; k < n; k++) {
                    testCase[i].sticker[j][k]= Integer.parseInt(input[k]);
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < testCount; i++) {
            int result=findMaxScore(testCase[i]);
            sb.append(result).append("\n");

        }
        System.out.println(sb.toString());
    }
    static class Data{
        int[][] sticker;
        public Data(int[][] sticker) {
            this.sticker = sticker;
        }
    }
    private static int findMaxScore(Data data){
        int[][] sticker=data.sticker;
        int n = sticker[0].length;
        if(n==1){
            return Math.max(sticker[0][0],sticker[1][0]);
        }
        int[][] dp=new int[2][n];
        dp[0][0]=sticker[0][0];
        dp[1][0]=sticker[1][0];
        dp[0][1]=sticker[1][0]+sticker[0][1];
        dp[1][1]=sticker[0][0]+sticker[1][1];
        for (int j = 2; j < n; j++) {
            for (int i = 0; i < 2; i++) {
                int k=i==0?1:0;// 상/하 반대쪽칸.
                dp[i][j]=Math.max(dp[k][j-1],dp[k][j-2])+sticker[i][j];
            }
        }
        return Math.max( dp[0][n -1],dp[1][n -1]);
    }


}
