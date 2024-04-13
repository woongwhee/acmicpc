package net.acmicpc.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] split=br.readLine().split(" ");
        int N=Integer.parseInt(split[0]);
        int H=Integer.parseInt(split[1]);
        int[] line=new int[H];
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < N/2; i++) {
            line[0]++;
            line[Integer.parseInt(br.readLine())]--;
            line[H-Integer.parseInt(br.readLine())]++;
        }
        for (int i = 1; i < H; i++) {
            line[i]+=line[i-1];
        }
        for (int i = 0; i <H ; i++) {
            min=Math.min(min,line[i]);
        }
        int count=0;
        for (int i = 0; i <H; i++) {
            if(line[i]==min){
                count++;
            }
        }
        System.out.println(min+" "+count);
    }

}
