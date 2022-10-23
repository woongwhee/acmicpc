package net.acmicpc.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem11660 {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken())+1;
        int M=Integer.parseInt(st.nextToken());
        int[][] arr=new int[N][N];
        for (int i = 1; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 1; j < N; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 2; i < N; i++) {
            for (int j = 1; j < N; j++) {
                arr[i][j]=arr[i-1][j]+arr[i][j];
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 2; j < N; j++) {
                arr[i][j]=arr[i][j-1]+arr[i][j];
            }
        }
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < M; i++) {

            st=new StringTokenizer(br.readLine());
            int startX=Integer.parseInt(st.nextToken());
            int startY=Integer.parseInt(st.nextToken());
            int endX=Integer.parseInt(st.nextToken());
            int endY=Integer.parseInt(st.nextToken());
            int result=arr[startX-1][startY-1]+arr[endX][endY]-arr[endX][startY-1]-arr[startX-1][endY];
            sb.append(result);
            sb.append("\n");
        }
        System.out.println(sb.toString());


    }
}