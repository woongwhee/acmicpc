package net.acmicpc.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2448 {
    static String[][] starArr;
    static String[] blankArr;
    public static void makStar(int k){
        starArr[k]=new String[starArr[k-1].length*2];
        String blank="   ";
        for (int i = 1; i < k; i++) {
            blank+=blank;
        }
        //위에별그리기
        for (int i = 0; i < starArr[k-1].length; i++) {
            starArr[k][i]=blank+starArr[k-1][i]+blank;
        }
        int start=starArr[k-1].length;
        //아래 별 두개 그리기;
        for (int i = 0; i < starArr[k-1].length; i++) {
            starArr[k][start+i]=starArr[k-1][i]+" "+starArr[k-1][i];
        }
    }
    public static void printStar(int k){
        for (int i = 0; i < starArr[k-1].length ; i++) {
            System.out.println(starArr[k-1][i]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader( System.in));
        int line= Integer.parseInt(br.readLine());
        int m=line/3;
        int k=1;//별을 몇단계까지 그려야되나
        while(m>1){
            k++;
            m/=2;
        }
        starArr=new String[k][];
        starArr[0]=new String[]{"  *  "," * * ","*****"};
        for (int i = 1; i < k; i++) {
            makStar(i);
        }
        printStar(k);
    }
}
