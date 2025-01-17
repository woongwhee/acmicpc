package net.acmicpc.programmers;

import java.util.*;

public class lessons340211 {

    class Solution {
        private int hashPoint(int x, int y) {
            return x * 101 + y;
        }

        public int solution(int[][] points, int[][] routes) {
            int x = routes.length;//로봇의 수
            int[] target = new int[x];
            Arrays.fill(target, 1);
            List<int[]> positions = new LinkedList<int[]>();
            int[] tgPoints = new int[points.length];
            for (int i = 0; i < points.length; i++) {
                tgPoints[i] = hashPoint(points[i][0], points[i][1]);
            }
            HashSet<Integer> collisonSet = new HashSet<Integer>();
            HashSet<Integer> posSet = new HashSet<Integer>();
            for (int i = 0; i < x; i++) {
                int point = tgPoints[routes[i][0] - 1];
                if (routes[i].length > 1) {//첫포인트가 마지막인경우
                    positions.add(new int[]{i, point});
                }
                if (!posSet.contains(point)) {
                    posSet.add(point);
                } else {
                    collisonSet.add(point);
                }
            }
            int collisionCount = collisonSet.size();//충돌횟수
            while (positions.size() > 0) {
                collisonSet.clear();
                posSet.clear();
                Iterator<int[]> iter = positions.iterator();
                while (iter.hasNext()) {
                    int[] position = iter.next();
                    int idx = position[0];
                    int pos = position[1];
                    //clear target
                    int tgPos = tgPoints[routes[idx][target[idx]] - 1];
                    if (tgPos == pos) {
                        target[idx]++;
                        if (target[idx] == routes[idx].length) {
                            iter.remove();
                            continue;
                        }
                        tgPos = tgPoints[routes[idx][target[idx]] - 1];
                    }

                    if (tgPos / 101 != pos / 101) { // move y
                        pos += pos / 101 < tgPos / 101 ? 101 : -101;
                    } else {// move x
                        pos += pos % 101 < tgPos % 101 ? 1 : -1;
                    }
                    position[1] = pos;
                    if (!posSet.contains(pos)) {
                        posSet.add(pos);
                    } else {
                        collisonSet.add(pos);
                    }
                }
                collisionCount += collisonSet.size();
            }
            return collisionCount;
        }


    }

    public static void main(String[] args) {
        int[][][][] testSet = {
                { // Test Case A
                        {{2, 2}, {2, 3}},
                        {{1, 2}}
                },
                { // Test Case B
                        {{2, 2}, {2, 3}},
                        {{1, 2}, {1, 2}}
                },
                { // Test Case C
                        {{2, 2}, {2, 3}, {3, 2}, {3, 3}},
                        {{1, 4}, {3, 2}}
                },
                { // Test Case D
                        {{2, 2}, {2, 3}},
                        {{1, 2, 1, 2}, {2, 1, 2, 1}}
                },
                { // Test Case E
                        {{1, 1}, {1, 2}, {2, 2}},
                        {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}}
                },
                { // Test Case F
                        {{2, 2}, {2, 3}},
                        {{1, 1, 2}}
                }
        };

        int[] expectedResults = {0, 1, 0, 0, 3, 0};

        for (int i = 0; i < expectedResults.length; i++) {
//            int i=4;
            Solution solution = new lessons340211().new Solution();
            int result = solution.solution(testSet[i][0], testSet[i][1]);
            if (result == expectedResults[i]) {
                System.out.println("Test Case " + (char) ('A' + i) + ": PASS");
            } else {
                System.out.println("Test Case " + (char) ('A' + i) + ": FAIL (Expected "
                        + expectedResults[i] + ", Got " + result + ")");
            }
        }
    }
}
