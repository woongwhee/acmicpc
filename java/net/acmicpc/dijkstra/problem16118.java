package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class problem16118 {
    static class Edge implements Comparable<Edge> {
        int to;
        int value;
        boolean isRun;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }

        public Edge(int to, int value, boolean isRun) {
            this.to = to;
            this.isRun = isRun;
            this.value = value;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.value, e.value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, value));
            graph[to].add(new Edge(from, value));
        }
        int result = dijkstra(graph, N);
        System.out.println(result);
    }

    static int dijkstra(ArrayList<Edge>[] graph, int N) {
        int INF = Integer.MAX_VALUE;
        int[] foxDist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(foxDist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Object o : graph[1]) {
            Edge e = (Edge) o;
            foxDist[e.to] = e.value;
            pq.add(new Edge(e.to, e.value * 2));
        }
        visited[1] = true;
        foxDist[1] = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (!visited[cur.to]) {
                visited[cur.to] = true;
                for (Object o : graph[cur.to]) {
                    Edge next = (Edge) o;
                    int nextValue = next.value * 2 + cur.value;
                    if (foxDist[next.to] > nextValue) {
                        foxDist[next.to] = nextValue;
                        pq.add(new Edge(next.to, nextValue));
                    }
                }
            }
        }
        int[] runDist = new int[N + 1];//뛰었을때 도착할수있는 최단거리
        int[] walkDist = new int[N + 1];//걸었을때 도착할수있는 최단거리
        boolean[] runVisited = new boolean[N + 1];
        boolean[] walkVisited = new boolean[N + 1];
        Arrays.fill(runDist, INF);
        Arrays.fill(walkDist, INF);
        for (Object o : graph[1]) {
            Edge e = (Edge) o;
            runDist[e.to] = e.value;
            pq.add(new Edge(e.to, e.value, true));
        }
        walkDist[1] = 0;
        walkVisited[1] = true;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.isRun && !runVisited[cur.to]) {
                runVisited[cur.to] = true;
                for (Object o : graph[cur.to]) {
                    Edge next = (Edge) o;
                    int nextValue = next.value * 4 + cur.value;
                    if (walkDist[next.to] > nextValue) {
                        walkDist[next.to] = nextValue;
                        pq.add(new Edge(next.to, nextValue, false));
                    }
                }
            } else if (!cur.isRun && !walkVisited[cur.to]) {
                walkVisited[cur.to] = true;
                for (Object o : graph[cur.to]) {
                    Edge next = (Edge) o;
                    int nextValue = next.value + cur.value;
                    if (runDist[next.to] > nextValue) {
                        runDist[next.to] = nextValue;
                        pq.add(new Edge(next.to, nextValue, true));
                    }
                }
            }
        }
        int result = 0;
        for (int i = 2; i <= N; i++) {
            if (foxDist[i] < INF && foxDist[i] < Math.min(walkDist[i], runDist[i])) {
                result++;
            }
        }
        return result;
    }
}
