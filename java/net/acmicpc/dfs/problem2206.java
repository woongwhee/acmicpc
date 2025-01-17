package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem2206 {
    static int N, M;
    static char[][] MAP;
    static int[] dX = {0, 0, -1, 1};
    static int[] dY = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new char[N][];
        int[][] range = new int[N][M];
        for (int i = 0; i < N; i++) {
            MAP[i] = br.readLine().toCharArray();
            Arrays.fill(range[i],Integer.MAX_VALUE);
        }
        boolean[][] visit = new boolean[N][M];
        Queue<Node> queue = new LinkedList<Node>();
        visit[0][0] = true;
        range[0][0] = 1;
        LinkedList<Node> endList = new LinkedList<>();
        if(M>1) {
            if (MAP[0][1] != '1') {
                range[0][1] = 2;
                queue.add(new Node(0, 1, 2));
            } else {
                range[0][1] = 2;
                endList.add(new Node(0, 1, 2));
            }
        }
        if(N>1) {
            if (MAP[1][0] != '1') {
                range[1][0] = 2;
                queue.add(new Node(1, 0, 2));
            } else {
                range[1][0] = 2;
                endList.add(new Node(1, 0, 2));
            }
        }


        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.getX() + dX[i];
                int nextY = cur.getY() + dY[i];
                int nextRange = cur.getRange() + 1;
                if (nextX<0 || nextX >= N ||nextY<0 || nextY >= M) {
                    continue;
                }
                if (nextRange < range[nextX][nextY]) {
                    range[nextX][nextY] = nextRange;
                    if (MAP[nextX][nextY] == '0') {
                        queue.add(new Node(nextX, nextY, nextRange));
                    } else {
                        endList.add(new Node(nextX, nextY, nextRange));

                    }
                }
            }
        }

        queue = endList;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.getX() + dX[i];
                int nextY = cur.getY() + dY[i];
                int nextRange = cur.getRange() + 1;
                if (nextX<0 || nextX >= N||nextY<0 ||nextY >= M) {
                    continue;
                }
                if (nextRange < range[nextX][nextY]) {
                    if (MAP[nextX][nextY] == '0') {
                        range[nextX][nextY] = nextRange;
                        queue.add(new Node(nextX, nextY, nextRange));
                    }
                }
            }
        }
        range[N-1][M-1]=range[N-1][M-1]==Integer.MAX_VALUE?-1:range[N-1][M-1];
        System.out.println(range[N - 1][M - 1]);

    }

    static class Node {
        private int x;
        private int y;
        private int range;

        public Node(int x, int y, int range) {
            this.x = x;
            this.y = y;
            this.range = range;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getRange() {
            return range;
        }
    }

}
