package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        br.close();
        int[] arr=new int[n+1];
        int[] dp=new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }
        int result=Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            dp[i]=1;
            for (int j = i-1; j > 0; j--) {
                if(arr[i]>arr[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            result=Math.max(dp[i],result);
        }
        System.out.println(result);
    }

}
