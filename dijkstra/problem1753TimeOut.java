package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem1753TimeOut {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        LinkedList<Edge>[] graph = new LinkedList[v + 1];
        int[][] weight = new int[v + 1][v + 1];
        int[] root = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            if (!contains(graph[from],value)) {
                graph[from].add(new Edge(to,value));
                weight[from][to] = value;
            } else {
                weight[from][to] = Math.min(weight[from][to], value);
            }
        }

        boolean[] visite = new boolean[v + 1];
        visite[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int from = queue.poll();
            for (Edge edge : graph[from]) {
                int to=edge.to;
                if (root[to] == 0) {
                    root[to] = root[from] + weight[from][to];
                } else {
                    root[to] = Math.min(root[to], root[from] + weight[from][to]);
                }
                if (!visite[to]) {
                    queue.add(to);
                    visite[to] = true;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= v; i++) {
            if (i == start) {
                sb.append("0");
                sb.append("\n");
            } else if (root[i] > 0) {
                sb.append(root[i]);
                sb.append("\n");
            } else {
                sb.append("INF");
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static boolean contains(List<Edge> edgeList,int to){
        boolean result=false;
        for (Edge edge : edgeList) {
            if(edge.to==to){
                return false;
            }
        }
        return result;
    }
    public static class Edge{
        int to;
        int value;
        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }

    }
}
