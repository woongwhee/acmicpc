package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class problem14938 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] split=br.readLine().split(" ");
        int N=Integer.parseInt(split[0]);//정점의 수
        int M=Integer.parseInt(split[1]);//수색범위
        int R=Integer.parseInt(split[2]);//경로 수
        int INF=1000000;
        int[] value=new int[N+1];
        split=br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            value[i]= Integer.parseInt(split[i-1]);
        }
        List<Edge>[] graph=new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<R;i++){
            split=br.readLine().split(" ");
            int to= Integer.parseInt(split[0]);
            int from= Integer.parseInt(split[1]);
            int wight= Integer.parseInt(split[2]);
            graph[to].add(new Edge(from,wight));
            graph[from].add(new Edge(to,wight));
        }
        br.close();

        int max=Integer.MIN_VALUE;
        for (int i = 1; i < N; i++) {
            PriorityQueue<Edge> queue=new PriorityQueue<>();
            int[] dist=new int[N+1];
            int item=value[i];
            Arrays.fill(dist,INF);
            dist[i]=0;
            queue.add(new Edge(i,0));
            while (!queue.isEmpty()){
                Edge cur=queue.poll();
                for (Edge e:graph[cur.to]) {
                    int edgeWeight = cur.weight + e.weight;
                    if(M>=edgeWeight && edgeWeight <dist[e.to]){
                        if(dist[e.to]==INF){
                            item+=value[e.to];
                        }
                        dist[e.to]= edgeWeight;
                        queue.add(new Edge(e.to,edgeWeight));
                    }
                }
            }
            max=Math.max(max,item);
        }
        System.out.println(max);

    }

    static class Edge implements Comparable<Edge>{
        int to;
        int weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.weight,e.weight);
        }
    }



}
