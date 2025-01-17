package net.acmicpc.bellmanford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class problem1865 {
    static class Graph {
        List<Edge> edgeList;
        int size;
        final static int INF = 10000000;

        public Graph(int size) {
            edgeList = new ArrayList<>();
            this.size = size;
        }

        public void addRoad(String str) {
            String[] split = str.split(" ");
            int from = Integer.parseInt(split[0]) - 1;
            int to = Integer.parseInt(split[1]) - 1;
            int weight = Integer.parseInt(split[2]);
            edgeList.add(new Edge(from, to, weight));
            edgeList.add(new Edge(to, from, weight));
        }

        public void addWormhole(String str) {
            String[] split = str.split(" ");
            int from = Integer.parseInt(split[0]) - 1;
            int to = Integer.parseInt(split[1]) - 1;
            int weight = -Integer.parseInt(split[2]);
            edgeList.add(new Edge(from, to, weight));
        }

        public boolean bellmanFord() {
            int start = 1;
            int[] dist = new int[size];
            Arrays.fill(dist, INF);
            dist[start] = 0;
            // N-1번의 relaxation
            for (int i = 0; i < size - 1; i++) {
                for (Edge edge : edgeList) {
                    int from = edge.from;
                    int to = edge.to;
                    int weight = edge.weight;
                    if (dist[to] > dist[from] + weight) {
                        dist[to] = dist[from] + weight;
                    }
                }
            }
            for (Edge edge : edgeList) {
                int from = edge.from;
                int to = edge.to;
                int weight = edge.weight;
                if (dist[to] > dist[from] + weight) {
                    return true;
                }
            }
            return false;
        }
    }

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
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
            sb.append(g.bellmanFord() ? "YES" : "NO");
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }


}
