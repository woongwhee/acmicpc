package net.acmicpc.floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class problem11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cityCount = Integer.parseInt(br.readLine());
        int busCount = Integer.parseInt(br.readLine());
        int[][] cost = new int[cityCount][cityCount];
        int INF=100000*100+1;
        for(int i=0;i<cityCount;i++){
            Arrays.fill(cost[i],INF);
            cost[i][i]=0;
        }
        for(int i=0;i<busCount;i++){
            String[] input=br.readLine().split(" ");
            int from=Integer.parseInt(input[0])-1;
            int to=Integer.parseInt(input[1])-1;
            int value=Integer.parseInt(input[2]);
            cost[from][to]=Math.min(cost[from][to],value);
        }
        //i->k를 갈때 i->j->k 와 i->k중 어떤게 더빠른지 비교한다.
        //플루이드 알고리즘
        for(int k=0; k<cityCount; k++){
            for(int i=0; i<cityCount; i++){
                for(int j=0; j<cityCount; j++){
                    if(cost[i][k] + cost[k][j] < cost[i][j]){
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<cityCount;i++){
            for(int j=0;j<cityCount;j++){
                if(cost[i][j]>=INF){
                    sb.append(0).append(" ");
                }else{
                    sb.append(cost[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}