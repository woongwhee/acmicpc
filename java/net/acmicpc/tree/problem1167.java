package net.acmicpc.tree;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 트리의 지름 https://www.acmicpc.net/problem/1167
 * 트리의 지름을 구하기 위해서는
 * 1. 임의의 정점에서 가장 먼 정점을 구한다.
 * 2. 그정점에서부터 가장 먼 정점 까지의 거리가 트리의 지름이다.
 * 이문제에서 많이 해맸는데 입력에 대해 너무 안일하게 생각했던거같다.
 *
 */


public class problem1167 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Edge>[] tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            int from= Integer.parseInt(input[0]);
            tree[from] = new ArrayList<>();
            for (int j = 1; j < input.length-1; j += 2) {
                int to = Integer.parseInt(input[j]);
                int weight = Integer.parseInt(input[j+1]);
                tree[from].add(new Edge(to, weight));
            }
        }

        int start=1;
        int[] firstTraversal = traversal(N, tree, start);
        int maxVal=firstTraversal[1];
        int end=1;
        for (int i = 2; i < firstTraversal.length; i++) {
            if(firstTraversal[i]>maxVal){
                end=i;
                maxVal=firstTraversal[i];
            }
        }
        int[] secondTraversal=traversal(N, tree, end);
        int result=secondTraversal[1];
        for (int i = 2; i < secondTraversal.length; i++) {
            result=Math.max(result,secondTraversal[i]);
        }
        System.out.println(result);
    }

    static int[] traversal(int N,List<Edge>[] tree,int start){
        boolean[] visited=new boolean[N+1];
        int[] range=new int[N+1];
        Queue<Edge> queue=new LinkedList<>();
        queue.add(new Edge(start,0));
        while(!queue.isEmpty()){
            Edge cur=queue.poll();
            if(visited[cur.to]){
                continue;
            }
            visited[cur.to]=true;
            for(Edge e:tree[cur.to]){
                if(!visited[e.to]){
                    range[e.to]=cur.weight+e.weight;
                    queue.add(new Edge(e.to,e.weight+cur.weight));
                }
            }
        }
        return range;
    }

    static class Edge{
        int weight;
        int to;

        public Edge( int to,int weight) {
            this.to = to;
            this.weight = weight;
        }

    }
}
