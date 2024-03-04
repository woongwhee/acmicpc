package net.acmicpc.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *  https://www.acmicpc.net/problem/2533
 * 백준 사회망 서비스 (SNS)
 * 트리와 dp를 함께 이용하는 유형의 알고리즘.
 * dp[n][0]에 자신이 얼리어답터가 아닌경우
 * dp[n][1]에 자신이 얼리어답터인 경우를 저장하고
 * 얼리어답터가 아닌경우 자식 노드는 무조건 얼리어답터 이기때문에 자식노드의 얼리어답터의 경우의수만 저장하고
 * 얼리어답터인경우 자식노드가 얼리어답터인경우와 아닌경우 둘중 하나기 때문에 최소값만 저장한다.
 */
public class problem2533 {
    static List<Integer>[] graph;
    static int n;
    static int[][] dp;
    static boolean[]visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            n= Integer.parseInt(br.readLine());
            graph=new List[n+1];
            for (int i = 1; i <= n; i++) {
                graph[i]=new ArrayList<>();
            }
            for (int i = 0; i < n-1; i++) {
                String[] split = br.readLine().split(" ");
                int from= Integer.parseInt(split[0]);
                int to= Integer.parseInt(split[1]);
                graph[from].add(to);
                graph[to].add(from);
            }
            dp=new int[n+1][2];
            visited=new boolean[n+1];
            dfs(1);
            int result=Math.min(dp[1][0],dp[1][1]);
            System.out.println(result);
    }
    static void dfs(int node){
        visited[node]=true;
        dp[node][0]=0;
        dp[node][1]=1;
        for (Integer child:graph[node]) {
            if(!visited[child]){
                dfs(child);
                dp[node][0]+=dp[child][1];
                dp[node][1]+=Math.min(dp[child][1],dp[child][0]);
            }
        }
    }
}
