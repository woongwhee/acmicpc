package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class problem13172 {
    final static long MOD= 1_000_000_007L;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int dice= Integer.parseInt(br.readLine());
        long result=0;
        for (int i = 0; i < dice; i++) {
            String[] split = br.readLine().split(" ");
            int Ni= Integer.parseInt(split[0]);
            int Si= Integer.parseInt(split[1]);
            int g =gcd(Si, Ni);
            Si /= g;
            Ni /= g;
            result +=Si*modInverse(Ni,MOD-2)%MOD;
            result%=MOD;
        }
        System.out.println(result);
    }
    public static int gcd(int a, int b) {
        if(b==0)return a;
        return gcd(b,a%b);
    }
    public static long modInverse(long num, long exponent) {
       if(exponent==1)return num;
        if(exponent%2==1)return num*modInverse(num,exponent-1);
       long half=modInverse(num,exponent/2);
       return half*half%MOD;
    }
}
