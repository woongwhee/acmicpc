package net.acmicpc.dynamic;

import java.util.*;
import java.io.*;

public class problem2169 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dpDfs(N,M,map));
    }

    private static int dpDfs(int N,int M,int[][] map) {
        int[][] dp=new int[N][M];
        dp[0][0]=map[0][0];

        for (int i = 1; i < N; i++) {
            dp[0][i]=dp[0][i-1]+map[0][i];
        }
        int[] L,R;
        for (int i = 1; i < N; i++) {
            L=new int[M];
            R=new int[M];
            L[0]=dp[i-1][0]+map[i][0];
            for (int j = 1; j <M ; j++) {
                L[j]=Math.max(L[j-1],dp[i-1][j])+map[i][j];
            }
            R[M-1]=dp[i-1][M-1]+map[i][M-1];
            for (int j = M-2; j >=0; j--) {
                R[j]=Math.max(R[j+1],dp[i-1][j])+map[i][j];
            }
            for (int j = 0; j < M; j++) {
                dp[i][j]=Math.max(R[j],L[j]);
            }
        }
        return dp[N-1][M-1];
    }

//    static int bfs(int[][] map,int n,int m){
//        int result;
//        int[][] dp=new int[n][m];
//        boolean[][] visited=new boolean[n][m];
//        Queue<point> queue=new LinkedList<>();
//        queue.add(new point(0,0));
//        dp[0][0]=map[0][0];
//        while(!queue.isEmpty()){
//            point p=queue.poll();
//            int x=p.x;
//            int y=p.y;
//            if(nx>=0&&ny>=0&&nx<N&&ny<M) {
//                if(dp[nx][ny]==0){
//                dp[nx][ny]=dfs(nx,ny);
//                }else{
//                dp[nx][ny]=Math.max(dp[nx][ny],dp[x][y]+map[nx][ny]);
//                }
//            };
//         }
//
//        return dp[][]
//    }
//    static class point{
//        int x;
//        int y;
//
//        public point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
}
