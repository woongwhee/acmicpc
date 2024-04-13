package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 조합론중 파스칼 삼각형에관한 내용
 * nCk=nPk/!n=k!/(k-n)!*n!
 */
public class problem11050 {

    public static int solution(int k,int n){
        int p=1;
        for (int i = k-n+1; i <= k; i++) {
            p*=i;
        }
        int q=1;
        for (int i = 1; i <= n; i++) {
            q*=i;
        }
        return p/q;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int k=Integer.parseInt(st.nextToken());
        int n=Integer.parseInt(st.nextToken());
        System.out.println(solution(k,n));
    }

}
