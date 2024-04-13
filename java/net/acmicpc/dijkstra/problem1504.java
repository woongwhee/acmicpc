package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class problem1504 {
    static int INF = 200000000;
    static int V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        ArrayList<Edge>[] graph = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, value));
            graph[to].add(new Edge(from, value));
        }
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        br.close();
        int[] dist = new int[V+1];
        int rootA=0;
        int rootB=0;
        dijkstra(graph, dist,1);
        rootA+=dist[a];
        rootB+=dist[b];
        dijkstra(graph,dist,a);
        rootA+=dist[b];
        rootB+=dist[V];
        dijkstra(graph,dist,b);
        rootB+=dist[a];
        rootA+=dist[V];
        int result=-1;
        if(rootA<INF&&rootB<INF){
            result=Math.min(rootA,rootB);
        }else if(rootA <INF||rootB<INF){
            result=rootA<INF?rootA:rootB;
        }
        System.out.println(result);

    }

    private static void dijkstra(ArrayList[] graph, int[] dist,int start) {
        boolean[] vitited= new boolean[V+1];
        Arrays.fill(dist,INF);
        dist[start]=0;
        vitited[start]=true;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.addAll(graph[start]);
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(!vitited[cur.to]) {
                vitited[cur.to]=true;
                dist[cur.to] = cur.value;
                for (Object o : graph[cur.to]) {
                    Edge e = (Edge) o;
                    if (dist[e.to] > e.value + cur.value) {
                        dist[e.to]=e.value+cur.value;
                        pq.add(new Edge(e.to, e.value + cur.value));
                    }
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Edge e) {
            return (this.value < e.value) ? -1 : ((this.value == e.value) ? 0 : 1);
        }
    }


}
