package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1629 문제
 * 거듭제곱 분할정복을 이용해서 풀수있다
 * C^x=C^x/2* C^x/2 or C^(x-1)/2 * C^(x-1)/2 * C
 * C^1=C
 * result=C^x%i
 * 다음점화식을 통해 계산해 낼수 있다.
 * 하지만 값이 너무 커저 잘못된 결과를 내 줄 수 있어 다른 점화식을 사용해야된다
 * C^x=C^x/2*C^x/2%i
 * C^1=1;C^0=0;
 * 거듭제곱분할정
 */
public class problem1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int A=Integer.parseInt(strings[0]);
        int B=Integer.parseInt(strings[1]);
        int C=Integer.parseInt(strings[2]);
        long result=fastPower(A,B,C);
        System.out.println(result);
    }

    static long fastPower(int base,int exponent,int C){
        if(exponent==1){
            return base%C;
        }else if(exponent==0){
            return 1;
        }

        long result=fastPower(base,exponent/2,C);
        result=(result*result)%C;
        if(exponent%2==1){
            result=(result*base)%C;
        }
        return result;
    }


}
