package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class problem24479 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int startIndex=Integer.parseInt(st.nextToken());
        List<Integer>[] vertexList=new List[N+1];
        for (int i = 0; i <= N; i++) {
            vertexList[i]=new LinkedList<>();
        }
        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            vertexList[from].add(to);
            vertexList[to].add(from);
        }
        Dfs d=new Dfs(vertexList,N,startIndex);
        System.out.println(d.dfs());
    }
    static class Dfs {
        List<Integer>[] vertexList;
        boolean[] visited;
        int[] orderArr;
        int order;
        int start;
        public Dfs(List<Integer>[] eList,int N, int start) {
            this.vertexList = eList;
            this.visited = new boolean[N+1];
            this.orderArr =new int[N+1];
            this.order = 1;
            this.start=start;
        }
        public String dfs(){
            this.orderArr[start]=order++;
            visited[start]=true;
            for (List<Integer> list:vertexList) {
                Collections.sort(list);
            }
            dfs(start);
            StringBuffer sb=new StringBuffer();
            for (int i = 1; i < orderArr.length; i++) {
                sb.append(orderArr[i]);
                if(i!=orderArr.length-1)sb.append("\n");
            }
            return sb.toString();
        }
        private void dfs(int index) {
            for (int i : vertexList[index]) {
                if (!visited[i]) {
                    visited[i] = true;
                    orderArr[i] = order++;
                    dfs(i);
                }
            }
        }
    }
}
