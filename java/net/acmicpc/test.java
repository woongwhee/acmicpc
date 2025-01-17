package net.acmicpc;

import java.util.*;

public class test {
    class Solution {
        private int[] num;
        private int[][] links;
        private int k;
        private int cutCount;

        public int slashGroup(int k, int[] num, int[][] links) {
            this.k = k;
            this.links = links;
            this.num = num;
            int rootIdx = findRoot();
            int left = Arrays.stream(num).max().getAsInt();
            int right = Arrays.stream(num).sum();
            int answer = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (canDivide(rootIdx, mid)) {
                    answer = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return answer;
        }

        private int findRoot() {
            boolean[] isNotRoot = new boolean[this.num.length];
            for (int i = 0; i < this.num.length; i++) {
                if (links[i][0] != -1) {
                    isNotRoot[this.links[i][0]] = true;
                }
                if (links[i][1] != -1) {
                    isNotRoot[this.links[i][1]] = true;
                }
            }
            for (int i = 0; i < num.length; i++) {
                if (!isNotRoot[i]) {
                    return i;
                }
            }
            throw new IllegalStateException("루트 노드를 찾을 수 없습니다.");
        }

        public boolean canDivide(int rootIdx, int limit) {
            this.cutCount = 0;
            dfs(rootIdx, limit);
            return cutCount <= k;
        }

        public int dfs(int cur, int limit) {
            if (cur == -1) {//리프노드
                return 0;
            }
            int left = dfs(this.links[cur][0], limit);
            int right = dfs(this.links[cur][1], limit);
            int size = this.num[cur] + left + right;
            if (size > limit) {
                this.cutCount++;
                size-=Math.max(left, right);
                if(size>limit){//둘다짤라야되는경우
                    this.cutCount++;
                    return this.num[cur];
                }
            }
            return size;
        }
        public int solution(int k, int[] num, int[][] links) {
            int result = slashGroup(k, num, links);
            return result;
        }
    }

    public static void main(String[] args) {
        int[] ks={3,1,2,4};
//        int[] ks = {1};
        int[][] nums = {
                {12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1},
                {6, 9, 7, 5}
                ,{6,9,7,5},{6,9,7,5}
        };
        int[][][] linkss = {
                {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {8, 5}, {2, 10}, {3, 0}, {6, 1}, {11, -1}, {7, 4}, {-1, -1}, {-1, -1}}
                ,{ {-1, -1}, {-1, -1}, {-1, 0}, {2, 1}}
                ,{ {-1, -1}, {-1, -1}, {-1, 0}, {2, 1}},
                {{-1, -1}, {-1, -1}, {-1, 0}, {2, 1}}};
        int[] result={40,27,14,9};
//        int[] result = {14};
        for (int i = 0; i < ks.length; i++) {
            Solution s = new test().new Solution();
            int r = s.solution(ks[i], nums[i], linkss[i]);
            System.out.println(r);
            System.out.println(result[i]);
        }
    }
}
