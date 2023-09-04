package net.acmicpc.programmers;

import java.util.Arrays;

public class problem178870 {
//    public int[] solution(int[] sequence, int k) {
//        int[] answer;
//        int[][] dp = new int[2][sequence.length];
//        int pivot = sequence.length;
//        for (int i = 0; i < pivot; i++) {
//            if (sequence[i] == k) {
//                return new int[]{i, i};
//            } else if (sequence[i] > k) {
//                pivot = k;
//                break;
//            }
//            dp[0][i] = sequence[i];
//        }
//        for (int i = 1; i < sequence.length; i++) {
//            int p = i % 2;
//            int e = p == 0 ? 1 : 0;
//            for (int j = i; j < pivot; j++) {
//                dp[p][j] = dp[e][j - 1] + sequence[j];
//                if (dp[p][j]==k){
//                    return new int[]{j-i,j};
//                }
//                if(dp[p][j]>k){
//                    pivot=j;
//                    break;
//                }
//            }
//        }
//        return new int[]{0,0};
//    }
    //O(N^2)알고리즘을 사용시작

    public static void main(String[] args) {
//        int[] solution = new problem178870().solution(new int[]{1, 1, 1, 2, 3, 4, 5}, 5);
//        System.out.println(solution[0]+","+solution[1]);
    }
}
