package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class problem1850 {
    public static int gbc(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        } else if (a == b) {
            return (int) a;
        }
        while(b>0){
            long temp = b;
            b = a % b;
            a = temp;
        }
        return (int) a;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int gbc = gbc(a,b);
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < gbc; i++) {
            sb.append("1");
        }
        System.out.println(sb);
    }
}
