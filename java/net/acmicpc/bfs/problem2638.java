package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem2638 {
   static int N,M;
   enum status{
       Chess,MaltChess,UnMaltChess,Space,Air
   }
   final static int[] DX={1,-1,0,0};
   final static int[] DY={0,0,1,-1};
   static status[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] sp=br.readLine().split(" ");
        N=Integer.parseInt(sp[0]);
        M=Integer.parseInt(sp[1]);
        map=new status[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num=Integer.parseInt(st.nextToken());
                if(num==0){
                    map[i][j]=status.Space;
                } else if (num==1) {
                    map[i][j]=status.Chess;
                }
            }
        }
        map[0][0]=status.Air;
        int time=-1;
        while(checkEnd()){
            checkAntrum();
            maltChess();
            time++;
        }
        System.out.println(time);

    }
    static void maltChess(){
        status[][]chess=new status[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i],0,chess[i],0,M);
        }
        int check[][]=new int[N][M];
        loop:for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(chess[i][j]==status.Chess){
                    checkChess(new Point(i,j),check,chess);
                }
            }
        }


    }
    private static void checkChess(Point p,int[][] check,status[][]chess){
        Queue<Point>queue=new LinkedList<>();
        queue.add(p);
        check[p.x][p.y]=-1;
        while (!queue.isEmpty()){
            Point cur=queue.poll();
            int x=cur.x;
            int y=cur.y;
            int airCount=0;
            for (int i = 0; i <4 ; i++) {
                int nextX=x+DX[i];
                int nextY=y+DY[i];
                if(nextX<0||nextY<0||nextX>=N||nextY>=M){
                    continue;
                }
                if(check[nextX][nextY]!=0){
                    continue;
                }
                if(chess[nextX][nextY]==status.Air){
                    airCount++;
                    continue;
                } else if (chess[nextX][nextY]==status.Space) {
                    continue;
                }
                check[nextX][nextY]=-1;
                queue.add(new Point(nextX,nextY));


            }
            if(airCount>=2){
                chess[x][y]=status.MaltChess;
                map[x][y]=status.MaltChess;
            }else{
                chess[x][y]=status.UnMaltChess;
            }
        }
    }


    static void checkAntrum(){
        Queue<Point> queue=new LinkedList<>();
        int check[][]=new int[N][M];
        check[0][0]=1;
        queue.add(new Point(0,0));
        while (!queue.isEmpty()){
            Point cur=queue.poll();
            int x=cur.x;
            int y=cur.y;
            for (int i = 0; i <4 ; i++) {
                int nextX=x+DX[i];
                int nextY=y+DY[i];
                if(nextX<0||nextY<0||nextX>=N||nextY>=M){continue;}
                if(check[nextX][nextY]!=0) {continue;}
                check[nextX][nextY]=1;
                if(map[nextX][nextY]==status.Chess){
                    continue;}
                else if(map[nextX][nextY]==status.MaltChess||
                        map[nextX][nextY]==status.Space)
                {
                    map[nextX][nextY]=status.Air;}
                queue.add(new Point(nextX,nextY));
            }
        }
    }
    static boolean checkEnd(){
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <M ; j++) {
                if(map[i][j]!=status.Air){
                    return true;
                }
            }
        }
        return false;
    }
    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x=x;
            this.y=y;
        }

    }
}
