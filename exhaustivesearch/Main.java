package net.acmicpc.exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int arr[]=new int[31];
        for (int i = 0; i < 28; i++) {
            arr[Integer.parseInt(br.readLine())]++;
        }
        System.out.println(arr[0]+" "+arr[1]);
    }

}