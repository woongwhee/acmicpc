package net.acmicpc.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class problem1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        LinkedList[] graph=new LinkedList[v+1];
        for (int i = 0; i <= v; i++) {
            graph[i] = new LinkedList<Edge>();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to,value));
//            graph[to].add(new Edge(from,value));


        }
        int[] min=new int[v+1];
        Arrays.fill(min,Integer.MAX_VALUE);
        PriorityQueue<Edge> pq=new PriorityQueue<>();
        min[start]=0;
        pq.addAll(graph[start]);
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(min[cur.to]>cur.value){
                min[cur.to]=cur.value;
            for (Object o: graph[cur.to]) {
                Edge ver=(Edge) o;
                if(min[ver.to] > ver.to + cur.value){
                    pq.add(new Edge(ver.to,ver.value+cur.value));
                }
            }
            }
        }

        StringBuffer sb=new StringBuffer();
        for (int i = 1; i <= v; i++) {
            if(min[i]==Integer.MAX_VALUE){
                sb.append("INF\n");
            }else{
                sb.append(min[i]);
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
    static class Edge implements Comparable<Edge>{
        int to;
        int value;
        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.value,e.value);
        }
    }

}
