package net.acmicpc.recursion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class problem10870 {
    static int Fibo(int n) {
        if(n<=1) {
            return n;}
        else {
            return Fibo(n-1)+Fibo(n-2);}
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num= Integer.parseInt(br.readLine());
        br.close();
        System.out.println(Fibo(num));
    }

}
