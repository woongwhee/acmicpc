package net.acmicpc.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int S = Integer.parseInt(split[1]);
        int[] sequence = new int[N];
        String[] queryString = br.readLine().split(" ");
        boolean has = false;
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(queryString[i]);
            if (sequence[i] >= S) {
                has = true;
            }
        }
        int result = 1;
        if (!has) {
            result = hasSequence(N, S, sequence);
        }

        System.out.println(result);
    }
//    Dp를 이용해 푼 버전 시간초과가 난다.
//    public static int hasSequence(int N, int S, int[] sequence) {
//        int[] dp = new int[N];
//        System.arraycopy(sequence,0,dp,0,N);
//        for (int i = 1; i < N; i++) {
//            for (int j = 0; j < N - i; j++) {
//                dp[j] += sequence[i + j];
//                if (dp[j] >= S) {
//                    return i + 1;
//                }
//            }
//        }
//        return 0;
//    }
    //투포인터를 이용한 버전
    public static int hasSequence(int N, int S, int[] sequence) {
        int start=0,end=0,sum=0,minLength=Integer.MAX_VALUE;
        while(true){
            if(sum>=S){
                minLength=Math.min(minLength,end-start);
                sum-=sequence[start];
                start++;
            }else if(end>=N){
                break;
            }else{
                sum+=sequence[end];
                end++;
            }
        }

        return minLength==Integer.MAX_VALUE?0:minLength;
    }
}
