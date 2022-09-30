package net.acmicpc.exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//시간초
public class problem14889Fail {
    static int[][] arr;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        arr= new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        min=2000;
        makeTeam(new Member(1,null),N/2-1);
        System.out.println(min);
    }
    public static void makeTeam(Member prevMember,int deep){
        if(deep==0){
            if(min>getScore(prevMember));
            return;
        }
        int result;
        loop:for (int i = 1; i < arr.length; i++) {
            Member current=prevMember;
            while(current!=null){
                if(i==current.backNumber){
                    continue loop;
                };
                current=current.prevMember;
            }
            makeTeam(new Member(i,prevMember),deep-1);
        }
    }
    public static int getScore(Member lastMember){
        int[] firstTeam=new int[arr.length/2];
        int[] secondTeam=new int[arr.length/2];
        Member current=lastMember;
        int[] check=new int[arr.length];
        for (int i = 0; i <firstTeam.length; i++) {
            firstTeam[i]=current.backNumber;
            check[current.backNumber]=1;
            current=current.prevMember;
        }
        int j=0;
        int k=0;
        while(j<secondTeam.length){
            if(check[k]==0){
                secondTeam[j++]=k;
            }
            k++;
        }
        int firstScore=0;
        int secondScore=0;
        for (int i = 0; i < secondTeam.length; i++) {
            for (int l = 0; l <secondTeam.length ; l++) {
                firstScore+=arr[firstTeam[i]][firstTeam[l]];
                secondScore+=arr[secondTeam[i]][secondTeam[l]];
            }
        }
        return Math.abs(firstScore-secondScore);
    }
}
class Member{
    int backNumber;
    Member prevMember;
    Member(int backNumber,Member prevMember){
        this.backNumber=backNumber;
        this.prevMember=prevMember;
    }
}