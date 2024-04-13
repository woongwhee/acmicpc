package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class problem1019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        int size = (int)Math.log10(N);
        int[]sizeArr=new int[size];//11칸엔 총합
        int[][] dp=new int[size][10];
        Arrays.fill(dp[0],1);
        sizeArr[0]=1;

        int[] result=new int[10];
        for (int i = size; i >=0 ; i--) {
            int cur= (int) Math.pow(20,i);
            int cur2= (int) Math.pow(10,i);
            int m=N/cur2;
            for (int j = 0; j <= 9; j++) {
                result[j]+=cur/20*m;
            }
            for (int j = 0; j < m; j++) {
                result[j]+=cur/20;
            }
            N%=cur;
        }
        System.out.println(Arrays.toString(result));
    }
}
