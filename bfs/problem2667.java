package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem2667 {
    static boolean[][] visit;
    static int N;
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        visit=new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String str= br.readLine();
            for (int j = 0; j < N; j++) {
                if(str.charAt(j)=='1'){
                    visit[i][j]=true;//true가 방문할수있다.
                }
            }
        }
        LinkedList<Integer> groupCount=new LinkedList<Integer>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j]==true){
                    int groupLength=1;
                    visit[i][j]=false;
                    groupLength=bfs(new Position(i,j));
                    groupCount.add(groupLength);
                }
            }
        }
        System.out.println(groupCount.size());
        groupCount.sort(Comparator.naturalOrder());
        for (Integer groupLength:groupCount) {
            System.out.println(groupLength.intValue());
        }
    }
    public static int bfs(Position start){
        Queue<Position> queue=new LinkedList<Position>();
        queue.add(start);
        int count=0;
        while (!queue.isEmpty()){
            Position current=queue.poll();
            count++;
            int x=current.x;
            int y=current.y;
            if(x!=0) {
                if (visit[x-1][y]) {
                    visit[x-1][y] = false;//큐에 넣었다면 false로 표시
                    queue.add(new Position(x-1,y));
                }
            }
            if(x!=N-1){
                if(visit[x+1][y]){
                    visit[x+1][y]=false;
                    queue.add(new Position(x+1,y));
                }
            }
            if(y!=0) {
                if (visit[x][y-1]) {
                    visit[x][y-1] = false;
                    queue.add(new Position(x,y-1));
                }
            }
            if(y!=N-1){
                if(visit[x][y+1]){
                    visit[x][y+1]=false;
                    queue.add(new Position(x,y+1));
                }
            }
        }
        return count;
    }


}
class Position{
    int x;
    int y;
    Position(int x,int y){
        this.x=x;
        this.y=y;
    }
}
