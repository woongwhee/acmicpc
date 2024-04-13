package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem1167 {
    static boolean visit[];
    static int maxNode;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List[] graph = new LinkedList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new LinkedList<Edge>();
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            st.nextToken();
            int next = Integer.parseInt(st.nextToken());
            while (next != -1) {
                int value = Integer.parseInt(st.nextToken());
                graph[i].add(new Edge(next, value));
                next = Integer.parseInt(st.nextToken());
            }
        }
        visit = new boolean[N + 1];
        max = Integer.MIN_VALUE;
        dfs(graph, 1, 0);
        max = Integer.MIN_VALUE;
        Arrays.fill(visit,false);
        dfs(graph, maxNode, 0);
        System.out.println(max);
    }

    public static void dfs(List[] grap, int index, int value) {
        if (visit[index]) {
            return;
        }
        if(value>max){
            max=value;
            maxNode=index;
        }
        visit[index] = true;
        for (Object o : grap[index]) {
            Edge e = (Edge) o;
            dfs(grap, e.to, value + e.value);
        }
}

static class Edge implements Comparable<Edge> {
    int to;
    int value;

    public Edge(int to, int value) {
        this.to = to;
        this.value = value;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(o.value, this.value);
    }
}
}

