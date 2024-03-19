package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem1179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int node = Integer.parseInt(br.readLine());
        int bus = Integer.parseInt(br.readLine());
        List<Edge>[] graph = new List[node + 1];
        for (int i = 0; i <= node; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < bus; i++) {
            String[] split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int value = Integer.parseInt(split[2]);
            graph[from].add(new Edge(to, value));
        }
        String[] split = br.readLine().split(" ");
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);
        String result = dijkstra(graph, start, end);
        System.out.println(result);
    }

    static String dijkstra(List<Edge>[] graph, int start, int end) {
        int[] dist = new int[graph.length];
        int[] prev = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        dist[start]=0;
        queue.add(new Edge(start, 0));
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if (cur.value > dist[cur.to]) {
                continue;
            }
            for (Edge e : graph[cur.to]) {
                int value = e.value + cur.value;
                if (dist[e.to] > value) {
                    prev[e.to] = cur.to;
                    dist[e.to] = value;
                    queue.add(new Edge(e.to, value));
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        for (int at = end; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        StringBuffer result = new StringBuffer();
        result.append(dist[end]).append("\n").append(path.size()).append("\n");
        for (int n : path) {
            result.append(n).append(" ");
        }
        return result.toString().trim();
    }

    static class Edge implements Comparable<Edge> {
        int to, value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
