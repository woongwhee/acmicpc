package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2609 {
    public static int gbc(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        } else if (a == b) {
            return a;
        }
        do {
            int temp = b;
            b = a % b;
            a = temp;
        } while (b != 0);
        return a;
    }
    public static int lcm(int a, int b){
        int gbc=gbc(a,b);
        return a*b/gbc;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a=Integer.parseInt(st.nextToken());
        int b=Integer.parseInt(st.nextToken());
        int gbc=gbc(a,b);
        int lcm=a*b/gbc;
        System.out.println(gbc);
        System.out.println(lcm);
    }
}
