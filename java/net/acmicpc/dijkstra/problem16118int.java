package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class problem16118int {
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
            return Integer.compare(this.value, e.value);
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
        int INF = 20000000;
        int[] foxDist = new int[N + 1];
        int[] wolfDist = new int[N + 1];
        Arrays.fill(foxDist, INF);
        Arrays.fill(wolfDist, INF);
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        visited[1] = true;
        pq.addAll(graph[1]);
        foxDist[1] = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (!visited[cur.to]) {
                visited[cur.to] = true;
                for (Object o : graph[cur.to]) {
                    Edge next = (Edge) o;
                    if (foxDist[next.to] > next.value + cur.value) {
                        foxDist[next.to] = next.value + cur.value;
                        pq.add(new Edge(next.to,foxDist[next.to]));
                    }
                }
            }
        }
        visited=new boolean[N+1];
        for (Object o : graph[1]) {
            Edge e = (Edge) o;
            pq.add(new Edge(e.to, e.value/2,false));
        }
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (!visited[cur.to]) {
                visited[cur.to] = true;
                for (Object o : graph[cur.to]) {
                    Edge next = (Edge) o;
                    int nextValue=next.isRun ? next.value/2 + cur.value : next.value*2 + cur.value;
                    if (wolfDist[next.to] > nextValue) {
                        wolfDist[next.to] = nextValue;
                        pq.add(new Edge(next.to,nextValue,!cur.isRun));
                    }
                }
            }
        }
        int result = 0;
        for (int i = 2; i <= N; i++) {
            if (foxDist[i] > wolfDist[i]) {
                result++;
            }
        }
        return result;
    }
}
