package net.acmicpc.exhaustivesearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2661 {
    static int[] arr;
    static int length;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        length=Integer.parseInt(br.readLine());
        arr=new int[length];
        arr[0]=1;
        //실행할코드
        makeSequence(1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
    static boolean makeSequence(int deep){
        if(deep==length){
            return true;
        }
            if(arr[deep-1]!=1){
                arr[deep]=1;}
            else{
                arr[deep]=2;
            }
            if(!checkGoodSequence(deep)){
                if(arr[deep]==1&&arr[deep-1]!=2){
                    arr[deep]=2;
                }else{
                    arr[deep]=3;
                }
                if(!checkGoodSequence(deep)){
                    return false;
                };
            }
             if(!makeSequence(deep+1)){
                 if(arr[deep]==1&&arr[deep-1]!=2){
                     arr[deep]=2;
                 }else{
                     arr[deep]=3;
                 }
                 if(checkGoodSequence(deep)){
                     return makeSequence(deep+1);
                 }else{
                     return false;
                 }
             }else{
                 return true;
             }

    }


    static boolean checkGoodSequence(int deep){
        boolean GoodBad=true;
        loop1:for (int i = 2; i <= (deep+1)/2; i++) {
            for (int j =0 ; j <i ; j++) {
                if(arr[deep-j]!=arr[deep-j-i]){
                    continue loop1;
                }
                if(j==i-1){
                    GoodBad=false;
                    break loop1;
                }
            }
        }
        return GoodBad;
    }
}
