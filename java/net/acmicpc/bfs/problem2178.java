package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class problem2178 {
    static boolean[][] map;
    static int dx[];
    static int dy[];
    static int N;
    static int M;

    static int check[][];
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] size= br.readLine().split(" ");
        N=Integer.parseInt(size[0]);
        M=Integer.parseInt(size[1]);
        dx= new int[]{1,0,-1,0};
        dy= new int[]{0,1,0,-1};
        map=new boolean[N][M];
        check=new int[N][M];
        for (int i = 0; i < N; i++){
            String str=br.readLine();
            for (int j = 0; j < M; j++) {
                if(str.charAt(j)=='1'){
                    map[i][j]=true;
                }
            }
        }
        bfs(map);
        System.out.println(check[N-1][M-1]);

    }
    /*
    *   깊이 우선탐색으로 문제를 풀게되면 모든 칸에 방문해야되서 시간초과가난다.
    *   넓이 우선탐색으로 하게되면 모든칸에 최단거리가 얼마인지 계산할수있게된다.
    */
     public static void bfs(boolean[][] map){
        Queue<Point> queue=new LinkedList<Point>();
        map[0][0]=false;
        queue.add(new Point(0,0));
        while (!queue.isEmpty()){
            Point p=queue.poll();
            int x=p.x;
            int y=p.y;
            for (int i = 0; i < 4; i++) {
                int nextX=x+dx[i];
                int nextY=y+dy[i];
                if(0<=nextX&&nextX<N&&0<=nextY&&nextY<M){
                    if(map[nextX][nextY]==true){
                        check[nextX][nextY]=check[x][y]+1;
                        map[nextX][nextY]=false;
                        queue.add(new Point(nextX,nextY));
                    }
                }
            }
        }

     }
    static class Point{
        int x;
        int y;
        Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
}
