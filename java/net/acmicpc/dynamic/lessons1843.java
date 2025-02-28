package net.acmicpc.dynamic;

import java.util.Arrays;

public class lessons1843 {
    class Solution {
        public int solution(String arr[]) {
            int n = arr.length / 2 + 1;
            int[] num = new int[n];
            boolean[] isPlus = new boolean[n];
            for (int i = 0; i < arr.length; i++) {
                if (i % 2 == 0) {
                    num[i / 2] = Integer.parseInt(arr[i]);
                } else {
                    isPlus[i / 2] = arr[i].charAt(0) == '+';
                }
            }
            int[][] maxDp = new int[n + 1][];
            int[][] minDp = new int[n + 1][];
            maxDp[0] = new int[n + 1];
            minDp[0] = new int[n + 1];
            maxDp[1] = num;
            minDp[1] = num;
            for (int l = 2; l <= n; l++) {
                maxDp[l] = new int[n - l + 1];
                minDp[l] = new int[n - l + 1];
                Arrays.fill(maxDp[l], Integer.MIN_VALUE);
                Arrays.fill(minDp[l], Integer.MAX_VALUE);
                for (int i = 0; i <= n - l; i++) {
                    for (int j = 1; j < l; j++) {
                        if (isPlus[i + j - 1]) {
                            int max = maxDp[j][i] + maxDp[l - j][i + j];
                            int min =minDp[j][i] + minDp[l - j][i + j];
                            maxDp[l][i] = Math.max(maxDp[l][i], max);
                            minDp[l][i] = Math.min(minDp[l][i], min);
                        } else {
                            int max = maxDp[j][i] - minDp[l - j][i + j];
                            int min = minDp[j][i] - maxDp[l - j][i + j];
                            maxDp[l][i] = Math.max(maxDp[l][i], max);
                            minDp[l][i] = Math.min(minDp[l][i], min);
                        }
                    }
                }
            }


            return maxDp[n][0];
        }
    }

    public static void main(String[] args) {
        lessons1843 main = new lessons1843();
        lessons1843.Solution solution = main.new Solution();
        String[][] testcase = {
                {"1", "-", "3", "+", "5", "-", "8",},
                {"5", "-", "3", "+", "1", "+", "2", "-", "4",}
        };
        int[] result = {
                1, 3
        };
        for (int i = 0; i < result.length; i++) {
            int resultVal = solution.solution(testcase[i]);
            System.out.println(resultVal);
            System.out.println(resultVal == result[i]);
        }
    }

}
