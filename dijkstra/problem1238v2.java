package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class problem1238v2 {
   static class PartyDijkstra{
        private final List<Edge>[] graph;
        private final int N, partyAt;
        private final int INF=Integer.MAX_VALUE;

        public PartyDijkstra(List<Edge>[] graph, int N, int partyAt) {
            this.graph = graph;
            this.N = N;
            this.partyAt = partyAt;
        }

        public int findMaxTime() {
            int[] timeToParty = runDijkstra(partyAt);
            int[] returnTimes = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                if (i == partyAt) continue;
                returnTimes[i] = runDijkstraWithExit(i, partyAt);
            }

            int maxTime = 0;
            for (int i = 1; i <= N; i++) {
                maxTime = Math.max(maxTime, timeToParty[i] + returnTimes[i]);
            }

            return maxTime;
        }
        private int[]runDijkstra(int start){
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[start] = 0;
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            queue.addAll(graph[start]);
            while (!queue.isEmpty()) {
                Edge cur = queue.poll();
                if (dist[cur.to] <= cur.weight) continue;
                dist[cur.to] = cur.weight;
                for (Edge edge : graph[cur.to]) {
                    if (cur.weight + edge.weight < dist[edge.to]) {
                        queue.add(new Edge(edge.to, cur.weight + edge.weight));
                    }
                }
            }
            return dist;

        }

        private int runDijkstraWithExit(int start, int end) {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[start] = 0;

            PriorityQueue<Edge> queue = new PriorityQueue<>();
            queue.addAll(graph[start]);

            while (!queue.isEmpty()) {
                Edge cur = queue.poll();
                if (dist[cur.to] <= cur.weight) continue;

                dist[cur.to] = cur.weight;

                if (cur.to == end) break;  // If we've reached the party location, exit.

                for (Edge edge : graph[cur.to]) {
                    if (cur.weight + edge.weight < dist[edge.to]) {
                        queue.add(new Edge(edge.to, cur.weight + edge.weight));
                    }
                }
            }
            return dist[end];
        }


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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        int X = Integer.parseInt(split[2]);

        List<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int weight = Integer.parseInt(split[2]);
            graph[from].add(new Edge(to, weight));
        }

        PartyDijkstra partyDijkstra = new PartyDijkstra(graph,N, X );
        System.out.println(partyDijkstra.findMaxTime());
    }


}
