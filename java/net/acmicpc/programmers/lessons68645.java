package net.acmicpc.programmers;

public class lessons68645 {
    static class Solution {
        public int[] solution(int n) {
            if (n == 1) {
                return new int[]{1};
            }
            int[][] triangle = new int[n][];
            int answerSize = 0;

            for (int i = 0; i < n; i++) {
                triangle[i] = new int[i + 1];
                answerSize += i + 1;
            }

            //동작이 3개
            //i방향으로 내려간다 행의 마지막(0이 아닌 혹은 n)까지. 동작 1
            //j방향으로 이동한다. 열의 마지막(0이 아닌 혹은 최대길이 까지). 동작 2
            //대각방향으로 i,j를 한칸씩 올라간다(0이 아닌) 동작 3
            int i = 0;
            int j = 0;
            int num = 1;
            while (true) {

                for (; i < n && triangle[i][j] == 0; i++, num++) {
                    triangle[i][j] = num;
                }
                i--;
                j++;
                if (triangle[i][j] > 0) {
                    break;
                }
                for (; j < triangle[i].length && triangle[i][j] == 0; j++, num++) {
                    triangle[i][j] = num;
                }
                i--;
                j -= 2;
                if (triangle[i][j] > 0) {
                    break;
                }
                for (; triangle[i][j] == 0; i--, j--, num++) {
                    triangle[i][j] = num;
                }
                i += 2;
                j++;
                if (triangle[i][j] > 0) {
                    break;
                }


            }

            int[] answer = new int[answerSize];
            int idx = 0;
            for (
                    int k = 0;
                    k < n; k++) {
                for (int l = 0; l < triangle[k].length; l++) {
                    answer[idx++] = triangle[k][l];
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        int[] testSet = {1};
        int[][] expectedResults = {
                {1, 2, 9, 3, 10, 8, 4, 5, 6, 7},
                {1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9},
                {1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11}
        };
        for (int i = 0; i < testSet.length; i++) {
            Solution s = new Solution();
            int[] result = s.solution(testSet[i]);
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[j] + ",");
            }
            System.out.println();
        }

    }
}
