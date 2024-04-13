package net.acmicpc.minimumspaningtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class problem1197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int V = Integer.parseInt(split[0]);
        int E = Integer.parseInt(split[1]);
        List<Edge>[] graps = new List[V + 1];
        for (int i = 0; i < V+1; i++) {
            graps[i] = new ArrayList();
        }
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            graps[from].add(new Edge(to, weight));
            graps[to].add(new Edge(from, weight));
        }
        boolean visited[] = new boolean[V+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,0));
        int count=0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.getTo()]) {
                continue;
            }
            visited[cur.getTo()]=true;
            count+=cur.getWeight();
            pq.addAll(graps[cur.getTo()]);
        }
        System.out.println(count);
    }

    static class Edge implements Comparable<Edge> {
        private int to;
        private long weight;
        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }
        public long getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(weight, o.weight);
        }
    }
}
