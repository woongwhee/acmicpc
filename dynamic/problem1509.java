package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        PalindromePartitioner pp=new PalindromePartitioner(str);
        System.out.println(pp.minPartition());
    }
    static class PalindromePartitioner{
        private char[] cArr;
        private boolean[][] palindrome;
        private int[] dp;
        public PalindromePartitioner(String s){
            this.cArr = s.toCharArray();
            this.palindrome=new boolean[s.length()+1][s.length()+1];
            this.dp=new int[s.length()+1];
            preProcess();
        }
        private boolean isInitialized = false;
        private boolean preProcess() {
            if (isInitialized) {
                for (int i = 0; i < cArr.length; i++) {
                    findPalindromeLength(i,i);
                    findPalindromeLength(i,i+1);
                }
                findMinPartition();
                isInitialized = true;
                return true;
            }
            return false;
        }
        private void findMinPartition(){
            for (int end = 1; end <= cArr.length; end++) {
                dp[end] = Integer.MAX_VALUE;
                for (int start = 1; start <= end; start++) {
                    if (palindrome[start][end]) {
                        dp[end] = min(dp[end], dp[start - 1] + 1);
                    }
                }
            }
        }
        private void findPalindromeLength(int start,int end){
            while(start>=0&&end<cArr.length&&cArr[start]==cArr[end]){
                palindrome[start+1][end+1]=true;
                start--;
                end++;
            }
        }
        private static int min(int a,int b){
            return a<b?a:b;
        }
        public int minPartition(){
            return dp[cArr.length];
        }
    }


}
