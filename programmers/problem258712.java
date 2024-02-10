package net.acmicpc.programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem258712 {
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] friends = br.readLine().split(" ");
        int N= Integer.parseInt(br.readLine());
        String[] gifts=new String[N];
        for (int i = 0; i < N; i++) {
            gifts[i]= br.readLine();
        }
        int solution = s.solution(friends, gifts);
        System.out.println(solution);
    }

    static class Solution {
        public int solution(String[] friends, String[] gifts) {
            int[][] graph = new int[friends.length][friends.length];
            int[] giftScore = new int[friends.length];
            for (String gift : gifts) {
                int fromIdx = -1;
                int toIdx = -1;
                String[] split = gift.split(" ");
                String from = split[0];
                String to = split[1];
                for (int i = 0; i < friends.length; i++) {
                    if (friends[i].equals(from)) {
                        fromIdx = i;
                        break;
                    }
                }
                for (int i = 0; i < friends.length; i++) {
                    if (friends[i].equals(to)) {
                        toIdx = i;
                        break;
                    }
                }
                graph[fromIdx][toIdx]++;
                giftScore[fromIdx]++;
                giftScore[toIdx]--;
            }
            int[] giftCount = new int[friends.length];
            for (int i = 0; i < friends.length; i++) {
                for (int j = 0; j < friends.length; j++) {
                    if (i == j) continue;
                    int compare = graph[i][j] - graph[j][i];
                    if (compare > 0) {
                        giftCount[i]++;
                    } else if (compare == 0 && giftScore[i] > giftScore[j]) {
                        giftCount[i]++;
                    }
                }
            }
            int max = 0;
            for (int i = 1; i < friends.length; i++) {
                if (giftCount[max] < giftCount[i]) {
                    max = i;
                }
            }
            int answer = giftCount[max];
            return answer;
        }
    }
}
