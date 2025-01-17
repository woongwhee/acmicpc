package net.acmicpc.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class lessons72413 {
    static class Solution{
        class Edge implements Comparable<Edge>{
            int to;
            int value;

            public Edge(int to, int value) {
                this.to = to;
                this.value = value;
            }

            @Override
            public int compareTo(Edge o) {
                return Integer.compare(this.value,o.value);
            }
        }
        private static final int INF=1000000000;
        private int[] dijkstra(int start,List<Edge>[] graph){
            int dist[]=new int[graph.length];
            Arrays.fill(dist,Integer.MAX_VALUE);
            for (int i = 0; i < graph.length; i++) {
                dist[i]=Integer.MAX_VALUE;
            }
            dist[start]=0;
            PriorityQueue<Edge> queue=new PriorityQueue<>();
            queue.addAll(graph[start]);
            Integer[] prev=new Integer[graph.length];
            return null;
        }
        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = 0;
            List<Edge>[] graph=new ArrayList[n+1];
            for (int i = 0; i <= n; i++) {
                graph[i]=new ArrayList<>();
            }

            return answer;
        }
    }


}
