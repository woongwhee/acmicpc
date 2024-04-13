package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2292 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int num=Integer.parseInt(br.readLine());
        int result=1;
        if(num==1){
            result=1;
        }else if(num<8){
            result=2;
        }else {
            int m=(num-2)/6;
            /**
             * 1+1+6*(3-2)+6*(4-2)+6*(5-2)....6*(ai-2)<m
             * 일때 최대인 i의 값
             * 1+1+6*(3-2)+6*(4-2)+6*(5-2)....6*(ai-2)
             * = 2+6(1+2+3+....(ai-2))
             * 즉 ai(ai+1)/2>m
             * ai^2-ai
             *
             */
            int i=(int)((-1+Math.sqrt(1+(8*m)))/2);
            while (i * (i + 1) / 2 > m) {
                i--;
            }
            result=i;
        }
        System.out.println(result);
    }
}
