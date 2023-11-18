package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class problem1865 {
    static class Graph {
        List<Edge>[] edgeList;
        int size;
        private static int INF = 200000000;

        public Graph(int size) {
            edgeList = new ArrayList[size+1];
            this.size = size;
            for (int i = 1; i <= size; i++) {
                edgeList[i] = new ArrayList<>();
            }
        }

        public void addRoad(String str) {
            String[] split = str.split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int weight = Integer.parseInt(split[2]);
            edgeList[from].add(new Edge(to, weight));
            edgeList[to].add(new Edge(from, weight));
        }

        public void addWormhole(String str) {
            String[] split = str.split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int weight = -Integer.parseInt(split[2]);
            edgeList[from].add(new Edge(to, weight));
        }

        public String bellmanFord() {
            String result = "NO";
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            int[] dist = new int[size+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            for (Edge e : edgeList[1]) {
                dist[e.to] = e.weight;
            }
            queue.addAll(edgeList[1]);

            while (!queue.isEmpty()) {
                Edge cur = queue.poll();
                if (cur.to == 1 && cur.weight < 0) {
                    result = "YES";
                    break;
                }
                if (dist[cur.to] < cur.weight) {
                    dist[cur.to] = cur.weight;
                }
                for (Edge e : edgeList[cur.to]) {
                    if (dist[e.to] > cur.weight + e.weight) {
                        queue.add(new Edge(e.to, cur.weight + e.weight));
                    }
                }
            }

            return result;

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

        public void setTo(int to) {
            this.to = to;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int teatCase = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < teatCase; i++) {
            String[] split = br.readLine().split(" ");
            int N = Integer.parseInt(split[0]);
            int M = Integer.parseInt(split[1]);
            int W = Integer.parseInt(split[2]);
            Graph g = new Graph(N);
            for (int j = 0; j < M; j++) {
                g.addRoad(br.readLine());
            }
            for (int j = 0; j < W; j++) {
                g.addWormhole(br.readLine());
            }
            sb.append(g.bellmanFord());
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }
}
