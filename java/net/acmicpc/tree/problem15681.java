package net.acmicpc.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class problem15681 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int root = Integer.parseInt(split[1]);
        int query = Integer.parseInt(split[2]);
        split[0].toCharArray();
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            split = br.readLine().split(" ");
            int vertex1 = Integer.parseInt(split[0]);
            int vertex2 = Integer.parseInt(split[1]);
            graph[vertex1].add(vertex2);
            graph[vertex2].add(vertex1);
        }
        int[] querys = new int[query];
        for (int i = 0; i < query; i++) {
            querys[i] = Integer.parseInt(br.readLine());
        }
        Tree2 t = new Tree2(graph, n, root);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < querys.length; i++) {
            sb.append(t.getNodeCount(querys[i])).append("\n");
        }
        System.out.println(sb.toString());
    }

    static class Tree2 {
        private boolean[] visited;
        private int[] childCount;
        private int root;
        private List<Integer>[] graph;
        private int size;

        public Tree2(List<Integer>[] graph, int size, int root) {
            this.graph = graph;
            this.size = size;
            this.root = root;
            preProccess();
        }

        private void preProccess() {
            childCount = new int[size + 1];
            Arrays.fill(childCount, 1);//자기 자신도 포함하는 서브트리는 최소 1개 존재한다.
            visited = new boolean[size + 1];
            dfs(root);
        }

        private int dfs(int current) {
            if (visited[current]) {
                return 0;
            }
            visited[current] = true;
            for (Integer child : graph[current]) {
                childCount[current] += dfs(child);
            }
            return childCount[current];
        }
        public int getNodeCount(int node) {
            return childCount[node];
        }
    }


}
