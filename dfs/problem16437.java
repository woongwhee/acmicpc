package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problem16437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        Island[] islands=new Island[N];
        islands[0]=new Island(0,-1);//1번섬추가
        for (int i = 1; i < N; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            char type=st.nextToken().charAt(0);
            long value=Long.parseLong(st.nextToken());
            int nextIndex=Integer.parseInt(st.nextToken());
            value=type=='S'?value:value*-1;
            islands[i]=new Island(value,nextIndex);
        }
        for (int i = 1; i < N; i++) {
            islands[islands[i].nextIndex-1].addIsland(islands[i]);
        }
        long survive=0L;
        for (Island is :islands[0].adjactionList){
            survive+=DFS(is);
        }
        System.out.println(survive);
    }
    static long DFS(Island is){
        long survice=is.value;
        for (Island i:is.adjactionList) {
            survice+=DFS(i);
        }
        survice=survice<0L?0L:survice;
        return survice;
    }
    static class Island{
        long value;
        int nextIndex;
        ArrayList<Island> adjactionList;
        Island(long value, int nextIndex) {
            this.value = value;
            this.nextIndex = nextIndex;
            adjactionList=new ArrayList<>();
        }
        void addIsland(Island i){
            adjactionList.add(i);
        }
    }
}
