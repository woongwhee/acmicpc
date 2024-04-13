package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2208 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] arr=br.readLine().split(" ");
        int n =Integer.parseInt(arr[0]);
        int l =Integer.parseInt(arr[1]);
        int[] map=new int[n];
        int[] trap=new int[n];
        int trapCount=0;
        for (int i = 0; i <n; i++) {
            map[i]=Integer.parseInt(br.readLine());
        }
        int[] dp=new int[n];
        for (int i = 0; i < l; i++) {
            dp[l]+=map[i]+trap[i];
        }
        for(int i=l;i<n;i++){

        }


    }
}
