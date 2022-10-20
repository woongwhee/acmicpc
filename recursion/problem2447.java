package net.acmicpc.recursion;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class problem2447 {


    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        long n=Long.parseLong(br.readLine());
        String str=String.valueOf(n);
        char[] charArr=str.toCharArray();
        Character[] CharArr=new Character[charArr.length];
        for(int i=0;i<charArr.length;i++){
            CharArr[i]=charArr[i];
        }
        Arrays.sort(CharArr,Comparator.reverseOrder());
        long answer = Long.parseLong(String.valueOf(charArr));


    }

}
