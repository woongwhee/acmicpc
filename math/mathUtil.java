package net.acmicpc.math;

import java.util.Scanner;

public class mathUtil {

    /**
     * 유클리드 호제법 구현
     * @return
     */
    public  int gbc(int a,int b){
        if(a<b){
            int temp=a;
            b=a;
            a=b;
        }else if(a==b){
            return a;
        }
        System.out.println("a : "+a+",b : " + b);
        do{
            System.out.println(a+"="+b+"*"+(a/b)+"+"+(a%b));
            int temp=b;
            b=a%b;
            a=temp;
        }while(b!=0);
        return a;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("첫번째 숫자 입력 : ");
        int a=sc.nextInt();
        sc.nextLine();
        System.out.print("두번째 숫자 입력 : ");
        int b=sc.nextInt();
        sc.nextLine();
        System.out.println(new mathUtil().gbc(a,b));

    }

}
