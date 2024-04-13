package net.acmicpc.recursion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class problem10872 {
        static int factorial(int num) {
            int result;
            if(num>1) {
                result= num* factorial(num-1);
            }else {
                result=1;
            }
            return result;
        }
        public static void main(String[] args) throws IOException {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            int num=Integer.parseInt(br.readLine());
            System.out.println(factorial(num));
        }
}
