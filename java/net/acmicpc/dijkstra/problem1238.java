package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.acmicpc.net/problem/1238
 */
public class problem1238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        int X = Integer.parseInt(split[2]);//파티하는곳
        List<Edge>[] graph = new List[N+1];
        for (int i = 1; i <=N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int weight = Integer.parseInt(split[2]);
            graph[from].add(new Edge(to, weight));
        }
        br.close();
        int result=dijkstra(N,X,graph);
        System.out.println(result);
    }

    public static int dijkstra(int N, int partyAt, List<Edge>[] graph) {
        int[] dist = new int[N + 1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[partyAt] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.addAll(graph[partyAt]);
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if (dist[cur.to] <= cur.weight) {
                continue;
            }
            dist[cur.to] = cur.weight;
            for (Edge e : graph[cur.to]) {
                if (cur.weight + e.weight < dist[e.to]) {
                    queue.add(new Edge(e.to, e.weight+cur.weight));
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (i == partyAt) {
                continue;
            }
            int[] min = new int[N + 1];
            Arrays.fill(min,INF);
            min[i]=0;
            queue = new PriorityQueue<>();
            queue.addAll(graph[i]);
            while (!queue.isEmpty()) {
                Edge cur = queue.poll();
                if (min[cur.to] < cur.weight) {
                    continue;
                }
                if (cur.to == partyAt) {
                    dist[i]+=cur.weight;
                    break;
                }
                min[cur.to] = cur.weight;
                for (Edge e : graph[cur.to]) {
                    if (cur.weight + e.weight < min[e.to]) {
                        queue.add(new Edge(e.to, cur.weight+e.weight));
                    }
                }
            }
        }
        return Arrays.stream(dist).filter(e->e!=INF).max().getAsInt();
    }

    static class Edge implements Comparable<Edge> {
        private int to;
        private int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.getWeight());
        }
    }
}
