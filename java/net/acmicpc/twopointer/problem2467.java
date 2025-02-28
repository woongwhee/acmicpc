package net.acmicpc.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        String[] input=br.readLine().split(" ");
        int[] seq=new int[n];
        for(int i=0;i<n;i++){
            seq[i]=Integer.parseInt(input[i]);
        }
        int left=0;
        int right=n-1;
        int min=Integer.MAX_VALUE;
        int minLeft=left;
        int minRight=right;
        while(left<right){
            int sum=seq[left]+seq[right];
            if(Math.abs(sum)<min){
                min=Math.abs(sum);
                minLeft=left;
                minRight=right;
            }
            int absLeft=Math.abs(seq[left]);
            int absRight=Math.abs(seq[right]);
            if(absLeft>absRight){
                left++;
            }else{
                right--;
            }
        }
        System.out.println(seq[minLeft]+" "+seq[minRight]);

    }
}
