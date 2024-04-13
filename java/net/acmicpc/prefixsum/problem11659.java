package net.acmicpc.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int length=Integer.parseInt(st.nextToken());
        int cases=Integer.parseInt(st.nextToken());
        int[] arr=new int[length+1];
        st=new StringTokenizer(br.readLine());
        for (int i = 1; i <= length; i++) {
            arr[i]=arr[i-1]+Integer.parseInt(st.nextToken());
        }
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < cases; i++) {
            st= new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int result=arr[end]-arr[start-1];
            sb.append(result);
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }
}
