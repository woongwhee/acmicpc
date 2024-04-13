package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class problem13460 {
    static int N,M;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] split=br.readLine().split(" ");
        N=Integer.parseInt(split[0]);
        M=Integer.parseInt(split[1]);
        map=new char[N][M];
        Point blue=null,red=null,goal=null;
        for (int i = 0; i < N; i++) {
            String line= br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j]=line.charAt(j);
                if(map[i][j]=='R'){
                    red=new Point(i,j);
                }else if(map[i][j]=='B'){
                    blue=new Point(i,j);
                }else if(map[i][j]=='0'){
                    goal=new Point(i,j);
                }
            }
        }

        PriorityQueue<Node> queue =new PriorityQueue<>();
        while(!queue.isEmpty()) {
            Node cur=queue.poll();

        }

    }
    static boolean move(Node a){
    return false;


    }


    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    static class Node implements Comparable<Node>{
        char[][] map;
        Direction before;
        
        int step;
        public Node(char[][] map, Direction before,int step) {
            this.map=map;
            this.before = before;
            this.step=step;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(step,o.step);
        }
    }

    static class Point{
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
