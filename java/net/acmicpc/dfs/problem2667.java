package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;

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
        LinkedList<Integer> groupCount=new LinkedList<Integer>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j]==true){
                    visit[i][j]=false;
                    int groupLength=1;
                    groupLength=dfs(i,j,groupLength);
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
    public static int dfs(int x,int y,int groupLength){

        if(x!=0) {
            if (visit[x-1][y]) {
                visit[x-1][y] = false;
                groupLength=dfs(x-1, y, groupLength+1);
            }
        }

        if(x!=N-1){
            if(visit[x+1][y]){
                visit[x+1][y]=false;
                groupLength=dfs(x+1,y,groupLength+1);
            }
        }

        if(y!=0) {
            if (visit[x][y-1]) {
                visit[x][y-1] = false;
                groupLength=dfs(x, y-1, groupLength+1);
            }
        }

        if(y!=N-1){
            if(visit[x][y+1]){
                visit[x][y+1]=false;
                groupLength=dfs(x,y+1,groupLength+1);
            }
        }

        return groupLength;
    }


}

