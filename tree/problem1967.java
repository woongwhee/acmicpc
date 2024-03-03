package net.acmicpc.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class problem1967 {
    static int maxNode;
    static int maxValue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Edge>[] graph = new List[n+1];
        maxNode=0;
        maxValue=0;
        for (int i = 0; i <= n; i++) {
            graph[i]=new ArrayList<>();
        }
        TreeMap t=new TreeMap<>();
        for (int i = 0; i < n - 1; i++) {
            String[] input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int value = Integer.parseInt(input[2]);
            graph[from].add(new Edge(to, value));
            graph[to].add(new Edge(from, value));
        }
        boolean[] visit=new boolean[n+1];
        visit[1]=true;
        dfs(visit,graph,1,0);
        int start=maxNode;
        maxNode=0;
        maxValue=0;
        visit=new boolean[n+1];
        visit[start]=true;
        dfs(visit,graph,start,0);
        System.out.println(maxValue);
    }
    static void dfs(boolean[] visit,List<Edge>[] graph,int current,int value){
        int next=0;
        for (Edge e:graph[current]) {
            if(!visit[e.to]){
                visit[e.to]=true;
                dfs(visit,graph,e.to,value+e.value);
                visit[e.to]=false;
                next++;
            }
        }
        if(next==0&&value>maxValue){//뿌리노드인경우
            maxNode=current;
            maxValue=value;
        }
    }

    static class Edge {
        int to;
        int value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }
}
