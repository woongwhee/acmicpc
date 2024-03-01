package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class problem11444 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n= Long.parseLong(br.readLine());
        long fibonachi = fibonachi(n);
        System.out.println(fibonachi);
    }
    private final static long MOD=1000000007;
    private static long[][] multiply(long[][] A,long[][] B){
        long[][] C = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    C[i][j]=(C[i][j]+A[i][k]*B[k][j])%MOD;
                }
            }
        }
        return C;
    }
    private static long[][] power(long[][]A , long n){
        if(n==1){
            return A;
        }
        long[][] temp=power(A,n/2);
        temp=multiply(temp,temp);
        if(n%2==1){
            temp=multiply(temp,A);
        }
        return temp;
    }
    public static long fibonachi(long n) {
        long[][] F={{1,1},{1,0}};
        if(n==0){
            return 0;
        }
        long[][] result=power(F,n-1);
        return result[0][0];

    }



}
