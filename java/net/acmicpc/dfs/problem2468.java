package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2468 {
    static int N;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};
    static void checkDeep(boolean[][] check,int x, int y){
        check[x][y]=false;
        for (int i = 0; i < 4; i++){
            int nextX=x+dx[i];
            int nextY=y+dy[i];
            if(nextX>=0&&nextY>=0&&nextX<N&&nextY<N&&check[nextX][nextY]){
                checkDeep(check,nextX,nextY);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        int[][] altitude=new int[N][N];
        int maxNum=0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                altitude[i][j]=Integer.parseInt(st.nextToken());
                maxNum=Math.max(maxNum,altitude[i][j]);
            }
        }
        int Max=0;
        for (int k = 0; k <= maxNum; k++) {
            boolean[][] check=new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(altitude[i][j]>k){
                        check[i][j]=true;
                    }
                }
            }
            int count=0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (check[i][j]){
                        checkDeep(check,i,j);
                        count++;
                    }
                }
            }
            Max=Math.max(Max,count);

        }
        System.out.println(Max);

    }
}
