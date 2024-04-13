package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class problem1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<Edge>[] graph = new List[n + 1];
        String[] split;
        for (int i = 0; i <= n; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int value = Integer.parseInt(split[2]);
            graph[from].add(new Edge(to, value));
        }
        split = br.readLine().split(" ");
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);
        int dijkstra = dijkstra(graph, start, end);
        System.out.println(dijkstra);

    }
    static int dijkstra(List<Edge>[] graph,int start,int end){
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[graph.length];
        Arrays.fill(dist, INF);
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start, 0));
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if (cur.value>dist[cur.to]) {
                continue;
            }
            for (Edge e : graph[cur.to]) {
                int value = cur.value + e.value;
                if (dist[e.to] > value) {
                    dist[e.to]= value;
                    queue.add(new Edge(e.to, value));
                }
            }
        }

        return dist[end];
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
            return Integer.compare(this.to, e.to);
        }
    }
}
