package net.acmicpc.programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class lessons432432 {
    class Solution {
        //value: 양이 더많은 개수
        //
        private HashSet<Integer> memo;
        private int[][] tree;
        private int[] info;

        private int dfs(int beatMask, List<Integer> leafList, int sheepCount, int value) {
            int maxSheep = sheepCount;
            for (int leaf : leafList) {
                for (int i = 0; i < 2; i++) {
                    int child = tree[leaf][i];
                    int brother = tree[leaf][i ^ 1];
                    int childMask = beatMask | (0b1 << child);
                    if (child == -1 || memo.contains(childMask)) {
                        continue;
                    }
                    memo.add(childMask);
                    List<Integer> nextLeafs = new ArrayList<>(leafList);
                    boolean brotherVisited = beatMask == (beatMask | 0b1 << brother);
                    if (brother==-1||brotherVisited) {//자식 두개를 전부방문해야 제거
                        nextLeafs.remove(Integer.valueOf(leaf));
                    }
                    if (tree[child][0] != -1 || tree[child][1] != -1) {
                        nextLeafs.add(child);
                    }
                    if (info[child] == 0) {
                        int sp = dfs(childMask, nextLeafs, sheepCount + 1, value + 1);
                        maxSheep = Math.max(sp, maxSheep);
                    } else if (value > 1) {
                        int sp = dfs(childMask, nextLeafs, sheepCount, value - 1);
                        maxSheep = Math.max(sp, maxSheep);
                    }

                }
            }
            return maxSheep;
        }

        public int solution(int[] info, int[][] edges) {
            this.tree = new int[info.length][2];//자식,자식
            this.info = info;
            this.memo = new HashSet<>();
            for (int i = 0; i < info.length; i++) {
                tree[i][0] = -1;
                tree[i][1] = -1;
            }
            for (int[] edge : edges) {
                if (tree[edge[0]][0] == -1) {
                    tree[edge[0]][0] = edge[1];
                } else {
                    tree[edge[0]][1] = edge[1];
                }
            }
            memo.add(1);
            List<Integer> leafList = new ArrayList<>();
            leafList.add(0);
            int answer = dfs(1, leafList, 1, 1);
            return answer;
        }
    }

    //info	edges	result
//[0,0,1,1,1,0,1,0,1,0,1,1]	[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]	5
//[0,1,0,1,1,0,1,0,0,1,0]	[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6],[3,7],[4,8],[6,9],[9,10]]	5
    public static void main(String[] args) {
        int[][] infos = {{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}};
        int[][][] edgeses = {{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3},
                {6, 5}, {4, 6}, {8, 9}}, {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}};
        int[] resultSet = {5, 5};
        for (int i = 0; i < resultSet.length; i++) {
            lessons432432 l = new lessons432432();
            Solution s = l.new Solution();
            int solution = s.solution(infos[i], edgeses[i]);
            System.out.println("result : " + solution + " expected : " + resultSet[i] + " " + (solution == resultSet[i]));
        }

    }
}
