package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 동전 2
 * https://www.acmicpc.net/problem/2294
 */


public class problem2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        int[] nArr=new int[n];
        int[] arr=new int[100001];
        for (int i = 0; i < n; i++) {
            int num=Integer.parseInt(br.readLine());
            if(arr[num]>0){
                n--;
                i--;
            }else{
                nArr[i]=num;
                arr[num]++;
            }
        }
        int[] token=new int[n];
        System.arraycopy(nArr,0,token,0,n);
        int[] dp=new int[k+1];
        for (int i = 0; i < n; i++) {
            if(token[i]<=k)
            dp[token[i]]=1;
        }
        for (int i = 0; i <= k ; i++) {
            if(dp[i]==0)continue;
            for (int j = 0; j <n ; j++) {
                if(i+token[j]>k)continue;
                if(dp[i+token[j]]==0)dp[i+token[j]]=dp[i]+1;
                else dp[i+token[j]]=Math.min(dp[i]+1,dp[i+token[j]]);
            }
        }
        System.out.println(dp[k]!=0?dp[k]:-1);


    }
}
