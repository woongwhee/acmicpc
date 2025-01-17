package net.acmicpc.minimumspaningtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class problem1647 {
    static long findMinimumSpaningTree(List<Edge>[] graph){
        PriorityQueue<Edge> queue=new PriorityQueue<>();
        boolean[] visited=new boolean[graph.length];
        int start=1;
        long totalValue=0;
        int maxValue=0;
        queue.add(new Edge(start,0));
        while (!queue.isEmpty()){
            Edge cur=queue.poll();
            if(visited[cur.to]){continue;}
            visited[cur.to]=true;
            totalValue+=cur.value;
            maxValue= Math.max(maxValue,cur.value);
            for (Edge next:graph[cur.to]) {
                if(!visited[next.to]){
                    queue.add(next);
                }
            }
        }
        totalValue-=maxValue;//마지막에 추가된 간선을빼준다.
        return totalValue;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n= Integer.parseInt(split[0]);
        int m= Integer.parseInt(split[1]);
        List<Edge>[] graph=new List[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int from= Integer.parseInt(split[0]);
            int to= Integer.parseInt(split[1]);
            int value= Integer.parseInt(split[2]);
            graph[from].add(new Edge(to,value));
            graph[to].add(new Edge(from,value));
        }
        long result = findMinimumSpaningTree(graph);
        System.out.println(result);
    }
    static class Edge implements Comparable<Edge>{

        int to;
        int value;
        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.value,o.value);
        }
    }


}
