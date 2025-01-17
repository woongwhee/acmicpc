package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem24444 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int Start=Integer.parseInt(st.nextToken());
        LinkedList<Integer>[] vertexList=new LinkedList[N+1];
        for (int i = 1; i <= N; i++) {
            vertexList[i]=new LinkedList<>();
        }
        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());
            int to=Integer.parseInt(st.nextToken());
            int from=Integer.parseInt(st.nextToken());
            vertexList[to].add(from);
            vertexList[from].add(to);
        }
        System.out.println(bfs(Start,vertexList));



    }
    static String bfs(int start,LinkedList<Integer>[] vertexList){
        int[] orderArr=new int[vertexList.length];
        int order=1;
        boolean[] visited=new boolean[vertexList.length];
        Queue<Integer> queue=new LinkedList<>();
        visited[start]=true;
        queue.add(start);
        while (!queue.isEmpty()){
            int cur=queue.poll();
            LinkedList<Integer> vertex=vertexList[cur];
            orderArr[cur]=order++;
            Collections.sort(vertex);
            for (Integer i:vertex) {
                if(!visited[i]){
                    queue.add(i);
                    visited[i]=true;
                }
            }
        }
        StringBuffer sb=new StringBuffer();
        for (int i = 1; i < orderArr.length; i++) {
            sb.append(orderArr[i]);
            if(i!=orderArr.length-1)sb.append("\n");
        }
        return sb.toString();
    }
}
