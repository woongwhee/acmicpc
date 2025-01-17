package net.acmicpc.programmers;

import java.util.Arrays;

/**
 * 요격시스템
 * https://school.programmers.co.kr/learn/courses/30/lessons/181188
 */


class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (e, d) -> e[1]-d[1]);
        int pivot=0;
        for (int i = 0; i < targets.length; i++) {
            if(targets[i][0]>=pivot){
                answer++;
                pivot=targets[i][1];
            }
        }

        return answer;
    }
}
public class problem181188 {
    static int[][] arr = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};

    public static void main(String[] args) {
        Solution s = new Solution();
        int answer = s.solution(arr);
        System.out.println(answer);
    }


}
