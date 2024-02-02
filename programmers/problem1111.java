package net.acmicpc.programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

public class problem1111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        Solution s=new Solution();
        int[][] result=s.solution(n);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0]+","+result[i][1]);
        }
    }
    public static class Solution {
        public int[][] solution(int n) {
            Hanoi hanoi = new Hanoi(n);
            LinkedList<int[]> result = hanoi.hanoiTop(n, 4, 4);
            int[][] answer = new int[result.size()][];
            for (int i = 0; i < result.size(); i++) {
                answer[i] = result.get(i);
            }
            return answer;
        }

        class Hanoi {
            Stack<Integer>[] hanoi;

            public Hanoi(int n) {
                hanoi = new Stack[3];
                for (int i = 0; i < 3; i++) {
                    hanoi[i] = new Stack<Integer>();
                }
                for (int i = n; i > 0; i--) {
                    hanoi[0].push(i);
                }
            }

            public LinkedList<int[]> hanoiTop(int popPoint, int beforeI, int beforeJ) {
                LinkedList<int[]> answer = null;
                if (popPoint == 0) {
                    return new LinkedList();
                }
                for (int i = 0; i < 3; i++) {
                    if (!hanoi[i].isEmpty()) {
                        for (int j = 0; j < 3; j++) {
                            if (i == j || (i == beforeJ && j == beforeI)) {
                                continue;
                            }

                            if (hanoi[i].peek() == popPoint && hanoi[2].isEmpty()) {
                                int cur = hanoi[i].pop();
                                LinkedList result = hanoiTop(popPoint - 1, 3, 3);
                                result.add(0, new int[]{i, j});
                                hanoi[i].push(cur);
                                return result;
                            }
                            if(!hanoi[j].isEmpty()&&hanoi[j].peek()<hanoi[i].peek()) {
                                hanoi[j].push(hanoi[i].pop());
                                LinkedList result = hanoiTop(popPoint, i, j);
                                result.add(0, new int[]{i, j});
                                if (answer == null || (result != null && result.size() < answer.size())) {
                                    answer = result;
                                }
                                hanoi[i].push(hanoi[j].pop());
                            }

                        }
                    }
                }
                return answer;
            }
        }
    }
}
