package net.acmicpc.dynamic;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem2096 {

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[][] map=new int[n+1][3];
        for (int i =0; i <n; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int j=0;
            while (st.hasMoreTokens()){
                map[i][j++]=Integer.parseInt(st.nextToken());
            }
        }
        int[][] maxDp=new int[n+1][3];
        int[][] minDp=new int[n+1][3];
        maxDp[0]= Arrays.copyOf(map[0],3);
        minDp[0]= Arrays.copyOf(map[0],3);
        for (int i = 1; i <=n ; i++) {
            maxDp[i][0]=Math.max(maxDp[i-1][0],maxDp[i-1][1])+map[i][0];
            maxDp[i][1]=Math.max(Math.max(maxDp[i-1][1],maxDp[i-1][2]),maxDp[i-1][0])+map[i][1];
            maxDp[i][2]=Math.max(maxDp[i-1][1],maxDp[i-1][2])+map[i][2];
            minDp[i][0]=Math.min(minDp[i-1][0],minDp[i-1][1])+map[i][0];
            minDp[i][1]=Math.min(Math.min(minDp[i-1][1],minDp[i-1][2]),minDp[i-1][0])+map[i][1];
            minDp[i][2]=Math.min(minDp[i-1][1],minDp[i-1][2])+map[i][2];
        }
        System.out.println(maxDp[n][1]+" "+minDp[n][1]);



    }

}
