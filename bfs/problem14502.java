package net.acmicpc.bfs;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem14502 {
    enum status{Blank,Wall,Virus}
    static int Max;
    static status[][] map;
    static LinkedList<Point> virusList;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    public static void main(String[] args)throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] size=br.readLine().split(" ");
        int N=Integer.parseInt(size[0]);
        int M=Integer.parseInt(size[1]);
        Max=-1;
        map=new status[N][M];
        virusList=new LinkedList<Point>();
        for (int i = 0; i < map.length ; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            for (int j = 0; j < map[0].length; j++) {
                String token=st.nextToken();
                if(token.equals("0")) {
                    map[i][j] = status.Blank;
                }else if (token.equals("1")) {
                    map[i][j]=status.Wall;
                }else if(token.equals("2")){
                    map[i][j]=status.Virus;
                    virusList.add(new Point(i,j));
                }
            }
        }
        makeWall(0);
        System.out.println(Max);
    }

    static void makeWall(int deep){
        if(deep==3){
            status[][] newMap=new status[map.length][map[0].length];
            for (int i = 0; i < map.length; i++) {
                System.arraycopy(map[i],0,newMap[i],0,map[0].length);
            }
            Max=Math.max(Max,virusWork(newMap));
            return;
        }
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]==status.Blank){
                    map[i][j]=status.Wall;
                    makeWall(deep+1);
                    map[i][j]=status.Blank;
                }
            }
        }
    }
    static int virusWork(status[][] map){

        Queue<Point> queue=new LinkedList<Point>();
        for (Point p: virusList) {
            queue.add(p);
        }
        while (!queue.isEmpty()){
            Point p=queue.poll();
            int x=p.x;
            int y=p.y;
            for (int i = 0; i < 4; i++) {
                int nextX=x+dx[i];
                int nextY=y+dy[i];
                if(0<=nextX&&nextX<map.length&&0<=nextY&&nextY<map[0].length){
                    if(map[nextX][nextY]==status.Blank){
                        map[nextX][nextY]=status.Virus;
                        queue.add(new Point(nextX,nextY));
                    }

                }
            }
        }
        return checkBlank(map);
    }

    static int checkBlank(status[][] newMap){
        int count=0;
        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[0].length; j++) {
                if(newMap[i][j]==status.Blank){
                    count++;
                };
            }
        }
        return count;
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
