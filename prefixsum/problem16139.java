package net.acmicpc.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem16139 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();
        int q=Integer.parseInt(br.readLine());
        int[][]alpa=new int[26][str.length()+1];
        for (int i = 1; i <=str.length(); i++) {
            for (int j = 0; j <26 ; j++) {
                alpa[j][i]=alpa[j][i-1];
            }
            alpa[(int)(str.charAt(i-1)-'a')][i]++;
        }
        StringBuffer sb=new StringBuffer();
        StringTokenizer st;
        for (int i = 0; i <q; i++) {
            st=new StringTokenizer(br.readLine());
            int ap=st.nextToken().charAt(0)-'a';
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken())+1;
            sb.append(alpa[ap][end]-alpa[ap][start]);
            sb.append("\n");
        }
        System.out.println(sb);

    }

}