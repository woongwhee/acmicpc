package net.acmicpc.dfs;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1260
 */
public class problem1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        int start = Integer.parseInt(split[2]);
        Set<Integer>[] graph = new Set[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new TreeSet<>();
        }
        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            int to = Integer.parseInt(split[0]);
            int from = Integer.parseInt(split[1]);
            graph[to].add(from);
            graph[from].add(to);
        }
        StringBuilder sb = new StringBuilder();
        boolean[] visit = new boolean[N + 1];
        dfs(sb, visit, graph, start);
        String dfsResult = sb.toString();
        String bfsResult = bfs(graph, start);
        System.out.println(dfsResult);
        System.out.println(bfsResult);


    }

    private static void dfs(StringBuilder sb, boolean[] visit, Set<Integer>[] graph, int i) {
        if (visit[i]) return;
        visit[i] = true;
        sb.append(i);
        sb.append(" ");
        for (int to : graph[i]) {
            dfs(sb, visit, graph, to);
        }
    }

    ;

    private static String bfs(Set<Integer>[] graph, int start) {
        boolean[] visit = new boolean[graph.length];
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        sb.append(start);
        sb.append(" ");
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (visit[cur]) {
                continue;
            }
            visit[cur] = true;
            sb.append(cur);
            sb.append(" ");
            for (int to : graph[cur]) {
                if (!visit[to]) {
                    queue.add(to);
                }
            }
        }
        return sb.toString();
    }
}
