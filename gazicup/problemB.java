package net.acmicpc.gazicup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class problemB {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int start= Integer.parseInt(br.readLine());
        int[] testCase=new int[10];
//        for (int i = 0; i < t; i++) {
//            testCase[i]= Integer.parseInt(br.readLine());
//        }
        for (int i = 0; i < 10; i++) {
            testCase[i]=start+i;
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 10; i++) {
//            find(testCase[i],true);
            List<Integer> list = find(testCase[i]);
            for (int n:list) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
     static List<Integer> find(int n){
        List<Integer> result=new ArrayList<>();
        int value=1;
         for (int i = 0; i < 2*n-1; i++) {
             int row=i<n?i:n-1;
             int col=i<n?0:i-n+1;
             while(row>=0&&col<n){
                 if(value==n*row+col+1){
                     result.add(value);
                 }
                 value++;
                 row--;
                 col++;
             }

         }
        return result;
    }
    static void find(int n,boolean b){
        int value=1;
        int[][] map =new int[n][n];
        for (int i = 0; i < 2*n-1; i++) {
            int row=i<n?i:n-1;
            int col=i<n?0:i-n+1;
            while(row>=0&&col<n){
                if(value==n*row+col+1){
                    map[row][col]=value;
                }
                value++;
                row--;
                col++;
            }

        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }


}
