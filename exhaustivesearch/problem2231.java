package net.acmicpc.exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int input= Integer.parseInt(br.readLine());
        int leng= (int) Math.log10(input)+1;
        int min=0;
        for (int i = 0; i < input; i++) {
            if(input==consturct(i)){
                min=i;
                break;
            };
        }
        System.out.println(min);

    }
    static int consturct(int num){
        int leng= (int) Math.log10(num)+1;//자리수
        int result=num;
        for (int i = 1; i <= leng; i++) {
             result+=num%Math.pow(10,i)/Math.pow(10,i-1);
        }
        return result;
    }
}
