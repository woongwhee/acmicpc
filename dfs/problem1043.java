package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");
        int N = Integer.parseInt(strArr[0]);
        int M = Integer.parseInt(strArr[1]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        if (length == 0) {
            System.out.println(M);
            return;
        }
        boolean[] knowLie = new boolean[N+1];
        for (int i = 0; i < length; i++) {
            int cur = Integer.parseInt(st.nextToken());
            knowLie[cur] = true;
        }
        int count = 0;
        List[] partyList=new List[M+1];
        List[] visitList=new List[N+1];
        for (int i = 0; i < N+1; i++) {
            visitList[i]=new ArrayList<>(N);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pCount = Integer.parseInt(st.nextToken());
            partyList[i]=new ArrayList<>();
            for (int j = 0; j < pCount; j++) {
                int cur = Integer.parseInt(st.nextToken());
                visitList[cur].add(i);
                partyList[i].add(cur);
            }
        }
        Queue<Integer> queue=new LinkedList<>();
        for (int i = 1; i < N+1; i++) {
            if(knowLie[i]){
                queue.add(i);
            }
        }
        boolean[] visited=new boolean[M+1];
        while(!queue.isEmpty()){
            int cur= queue.poll();

            for(Object m:partyList[cur]){

            };

        }
        System.out.println(count);
    }
}
