package net.acmicpc.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class problem1939 {
    static int n,start,end;
    static List<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] split=br.readLine().split(" ");
        n=Integer.parseInt(split[0]);
        int m=Integer.parseInt(split[1]);
        graph=new List[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i]=new ArrayList<>();
        }
        int maxWeight=0;
        for (int i = 0; i < m; i++) {
            split=br.readLine().split(" ");
            int to= Integer.parseInt(split[0]);
            int from= Integer.parseInt(split[1]);
            int weight= Integer.parseInt(split[2]);
            graph[from].add(new Edge(to,weight));
            graph[to].add(new Edge(from,weight));
            maxWeight=Math.max(maxWeight,weight);
        }
        split= br.readLine().split(" ");
        start=Integer.parseInt(split[0]);
        end=Integer.parseInt(split[1]);
        int result=binarySearch(1,maxWeight);
        System.out.println(result);
     }
    static int binarySearch(int low, int high) {
        int result = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canMove(mid)) { // mid 중량으로 start에서 end까지 이동 가능한지 확인
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }
    static boolean canMove(int weight){
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == end) {
                return true;
            }
            for (Edge edge : graph[current]) {
                if (!visited[edge.to] && edge.weight >= weight) {
                    visited[edge.to] = true;
                    queue.offer(edge.to);
                }
            }
        }

        return false;
    }
    static class Edge{
        int to;
        int weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }


}
