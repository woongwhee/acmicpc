package net.acmicpc.dynamic;

import java.util.*;
import java.io.*;

public class problem14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n];
        int[] point = new int[n];
        int answer=0;
        int current=0;
        for (int i = 0; i < n; i++) {
            dp[i]=1;
            for (int j = i - 1; j >= 0; j--) {
                if(arr[i]>arr[j]){
                    if(dp[i]<dp[j]+1){
                        dp[i]=dp[j]+1;
                        point[i]=j;
                    }
                    if(dp[j]==dp[i]){
                        point[i]=Math.min(point[i],j);
                    }
                }
            }
            if(dp[i]>answer) {
                answer = dp[i];
                current=i;
            }
        }
        StringBuffer sb=new StringBuffer();
        sb.insert(0,arr[current]);
        sb.insert(0," ");
        for (int i = 1; i < answer ; i++) {
            current=point[current];
            sb.insert(0,arr[current]);
            sb.insert(0," ");
        }
        sb.insert(1,"\n");
        sb.insert(0,answer);
        System.out.println(sb.toString());
    }
}
