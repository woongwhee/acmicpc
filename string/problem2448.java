package net.acmicpc.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2448 {
    static String[][] starArr;
    static String[] blankArr;
    static void makeBlank(int line){
        blankArr=new String[line/2+1];
        blankArr[0]=" ";
        for (int i = 1; i <=line/2 ; i++) {
            blankArr[i]=blankArr[i-1]+" ";
        }
    }
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
        int blankIndex=starArr[k-1].length-1;
        int j=0;
        //아래 별 두개 그리기;
        for (int i = starArr[k-1].length; i < starArr[k].length; i++) {
            starArr[k][i]=starArr[k-1][j]+blankArr[blankIndex-j]+starArr[k-1][j];
            j++;
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
        starArr[0]=new String[]{"  *"," * *","*****"};
        makeBlank(line);
        for (int i = 1; i < k; i++) {
            makStar(i);
        }
        printStar(k);
    }
}
