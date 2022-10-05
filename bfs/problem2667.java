package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class problem2667 {
    static int[][] map;
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
        ArrayList<Integger> groupCount=new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j]==true){
                    visit[i][j]=false;
                    Integger groupLength=new Integger();
                    dfs(i,j,groupLength);
                    groupCount.add(groupLength);
                }
            }
        }
        System.out.println(groupCount.size());
        groupCount.stream().sorted(Comparator.comparingInt(Integger::getValue))
                .mapToInt(e->e.value)
                .forEach(System.out::println);

    }
    public static void dfs(int x,int y,Integger groupLength){
        if(x!=0) {
            if (visit[x-1][y]) {
                visit[x-1][y] = false;
                groupLength.add1();
                dfs(x-1, y, groupLength);
            }
        }
        if(x!=N-1){
            if(visit[x+1][y]){
                visit[x+1][y]=false;
                groupLength.add1();
                dfs(x+1,y,groupLength);
            }
        }
        if(y!=0) {
            if (visit[x][y-1]) {
                visit[x][y-1] = false;
                groupLength.add1();
                dfs(x, y-1, groupLength);
            }
        }
        if(y!=N-1){
            if(visit[x][y+1]){
                visit[x][y+1]=false;
                groupLength.add1();
                dfs(x,y+1,groupLength);
            }
        }
    }


}
class Integger{
    int value;
    Integger(){
        this.value=1;
    }
    void add1(){
        this.value++;
    }
    int getValue(){
        return value;
    }
}

