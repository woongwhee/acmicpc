package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//dfs로 문제를 풀시 시간초과가 날수있다.
//최적화의 문제일수도있지만 dp도 가능하기 때문에 dp
public class problem17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = split[j].charAt(0) == '0';
            }
        }
        int[] result={0};
        dfs(map,0,1,Course.HORIZONTAL,result);
        System.out.println(result[0]);
    }
    enum Course {
        HORIZONTAL(new int[]{0, 1}),
        DIAGONAL(new int[]{1, 1}),
        VERTICAL(new int[]{1, 0});
        private final int[] direction;
        private static final Map<Course, List<Course>> nextDirection = new HashMap<>();
        private static final Map<Course, List<int[]>> checkBlock = new HashMap<>();//움직이기전 확인해야될 블럭
        Course(int[] direction) {
            this.direction = direction;
        }
        static {
            nextDirection.put(HORIZONTAL, Collections.unmodifiableList(Arrays.asList(HORIZONTAL, DIAGONAL)));
            nextDirection.put(DIAGONAL, Collections.unmodifiableList(Arrays.asList(HORIZONTAL, DIAGONAL, VERTICAL)));
            nextDirection.put(VERTICAL, Collections.unmodifiableList(Arrays.asList(DIAGONAL, VERTICAL)));

            checkBlock.put(HORIZONTAL, Collections.singletonList(new int[]{0, 1}));
            checkBlock.put(DIAGONAL, Collections.unmodifiableList(Arrays.asList(new int[]{1, 0}, new int[]{1, 1}, new int[]{0, 1})));
            checkBlock.put(VERTICAL, Collections.singletonList(new int[]{1, 0}));
        }
        public int plusX() {
            return direction[0];
        }

        public int plusY() {
            return direction[1];
        }

        public List<Course> getNext() {
            return nextDirection.get(this);
        }

        public List<int[]> getCheckBlock() {//배열은 불변성이 보장이안된다...
            return checkBlock.get(this);
        }
    }

    static void dfs(boolean[][] map, int x, int y, Course direction,int[]result) {

        firstLoop:for (Course next : direction.getNext()) {
            int nextX = x + next.plusX();
            int nextY = y + next.plusY();
            for (int[] check:next.getCheckBlock()) {
                int checkX = x + check[0];
                int checkY = y + check[1];
                if(checkX>=map.length||checkY>=map.length||!map[checkX][checkY]){
                    continue firstLoop;
                }
            }
            if (nextX >= map.length || nextY >= map.length) {
                continue;
            }
            if(nextX== map.length-1&&nextY==map.length-1){
                result[0]++;
                continue;
            }
            dfs(map,nextX,nextY,next,result);
        }

    }


}
