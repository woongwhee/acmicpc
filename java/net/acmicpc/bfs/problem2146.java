package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem2146 {
    final static int[] DX={1,0,-1,0};
    final static int[] DY={0,1,0,-1};
    static int[][]map;
    static int[][]check;
    static int[][]road;
    static int N;
    static int islandCount;
    static List<Point> islandList;
    static int MinBridge;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        check=new int[N][N];
        road=new int[N][N];
        islandCount=0;
        islandList=new ArrayList<>();
        MinBridge=Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==1){
                    map[i][j]=-1;
                    check[i][j]=-1;
                } else if (map[i][j]==0) {
                    road[i][j]=-1;
                }

            }
        }

        for (int i = 0; i <N ; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]==-1){
                    islandCount++;
                    islandMarking(new Point(i,j));
                    islandList.add(new Point(i,j));
                }
            }
        }
        for (int i = 0; i < islandList.size()-1; i++) {
            findRoot(islandList.get(i));
        }
        System.out.println(MinBridge);
    }

    static void islandMarking(Point p){
        Queue<Point> queue=new LinkedList<>();
        map[p.x][p.y]=islandCount;
        queue.add(p);
        while(!queue.isEmpty()){
            Point cur=queue.poll();
            int x=cur.x;
            int y=cur.y;
            for (int i = 0; i <4 ; i++) {
                int nextX=x+DX[i];
                int nextY=y+DY[i];
                if(nextX<0||nextY<0||nextX>=map.length||nextY>= map.length){
                    continue;}
                if(map[nextX][nextY]==-1){
                    map[nextX][nextY]=islandCount;
                    queue.add(new Point(nextX,nextY));
                }
            }
        }
    }
    static void findRoot(Point p){
        initRoad();
        Queue<Point>queue= new LinkedList<>();
        Queue<Point>sideQ= new LinkedList<>();
        int islandId=map[p.x][p.y];
        check[p.x][p.y]=1;
        queue.add(p);
        while (!queue.isEmpty()){
            Point cur=queue.poll();
            int x=cur.x;
            int y=cur.y;
            boolean side=false;
            for (int i = 0; i < 4; i++) {
                int nextX=x+DX[i];
                int nextY=y+DY[i];
                if(nextX<0||nextY<0||nextX>=map.length||nextY>= map.length){
                    continue;}
                if(map[nextX][nextY]==0&&check[nextX][nextY]==0){
                    side=true;
                    check[nextX][nextY]=2;
                }
                if(check[nextX][nextY]==-1){//방문한적없는육지
                    check[nextX][nextY]=-2;
                    queue.add(new Point(nextX,nextY));
                };

            }
            if(side=true){
                sideQ.add(cur);
            }
            while(!sideQ.isEmpty()){
                Point s=sideQ.poll();
                int sX=s.x;
                int sY=s.y;
                for (int i = 0; i < 4; i++) {
                    int nextX=sX+DX[i];
                    int nextY=sY+DY[i];
                    if(nextX<0||nextY<0||nextX>=map.length||nextY>= map.length){continue;}
                    if(map[nextX][nextY]==islandId){continue;}//같은섬인경우
                    if(map[nextX][nextY]!=0&&map[nextX][nextY]!=islandId){//다른섬에 도착한경우
                        MinBridge=Math.min(MinBridge,road[sX][sY]);
                    }else if(road[nextX][nextY]!=-1){//방문한적있는경우
                        if(road[nextX][nextY]>road[sX][sY]+1){//방문한적있지만 이경로가 더짦은경우
                            road[nextX][nextY]=road[sX][sY]+1;
                            sideQ.add(new Point(nextX,nextY));
                        }
                    }else{//방문한적없는경우
                        road[nextX][nextY]=road[sX][sY]+1;
                        sideQ.add(new Point(nextX,nextY));
                    }
                }
            }
        }



    }
    static void initRoad(){
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <N ; j++) {
                if(road[i][j]!=0){
                    road[i][j]=-1;
                };
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
