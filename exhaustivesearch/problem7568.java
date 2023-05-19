package net.acmicpc.exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        int[][] bmi=new int[2][n];
        for (int i = 0; i <n ; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            bmi[0][i]= Integer.parseInt(st.nextToken());
            bmi[1][i]= Integer.parseInt(st.nextToken());
        }
        int[] rank = solution(bmi, n);
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i <n ; i++) {
            sb.append(rank[i]+1);
            sb.append(" ");
        }
        System.out.println(sb);
    }

    static int[] solution(int[][] bmi,int n){
        int[] rank=new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if(bmi[0][i]>bmi[0][j]&&bmi[1][i]>bmi[1][j]){
                    rank[j]++;
                }else if(bmi[0][j]>bmi[0][i]&&bmi[1][j]>bmi[1][i]){
                    rank[i]++;
                }
            }
        }
        return rank;

    }

}
