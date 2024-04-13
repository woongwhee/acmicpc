package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);//수빈이 위치
        int K = Integer.parseInt(split[1]);//동생 위치
        System.out.println(bfs(N, K));

    }
    public static class Node implements Comparable<Node>{
        int time;
        int num;
        public Node(int num,int time) {
            this.time = time;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time,o.time);
        }
    }
    public static int bfs(int n, int k) {
        if (n >= k) {
            return n-k;
        }

        int limit=100001;
        boolean[] visited=new boolean[limit+1];
        PriorityQueue<Node> queue=new PriorityQueue<>();
        queue.add(new Node(n,0));
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            visited[current.num]=true;
            if (current.num== k) {
                return current.time;
            }
            if(current.num*2<limit&&!visited[current.num*2]){
                queue.add(new Node(current.num*2, current.time));
            }
            if(current.num+1<limit&&!visited[current.num+1]){
                queue.add(new Node(current.num+1, current.time+1));
            }
            if(current.num-1>=0&&!visited[current.num-1]){
                queue.add(new Node(current.num-1, current.time+1));
            }
        }

        return k - n;
    }
}
