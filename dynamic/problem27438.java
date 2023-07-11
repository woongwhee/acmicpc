package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class problem27438 {

    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num= Integer.parseInt(br.readLine());
        int dp[][]=new int[9][];
//        dp[0]= new int[]{666};
//        dp[1]= new int[]{1666,2666,3666,4666,5666,6660,6661,6662,6663,6664,6665,6666,6667,6668,6669,7666,8666,9666};
        int end[][]=new int[8][3];
        int size[]=new int[9];
        size[1]=1;
        size[2]=18;
        end[0]= new int[]{1, 1, 1};
        end[1]= new int[]{4,14 , 17};
        int point =0;
        for (int i = 2; i < 8; i++) {
            int start=end[i-1][2]+1;
            int beforeSize = size[i] + size[i-1];
            end[i][0]=start+5*beforeSize;
            end[i][1]= beforeSize +end[i][0]+ ((int)(Math.pow(10,i)*0.9));
            end[i][2]=end[i][1]+3* beforeSize;
            if(num <end[i][2]){
                point=i;
                break;
            }
        }
        makeNum(end,size,point,num);
    }
    public static String makeNum(int[][]endArr,int[]size,int index,int num){
        if(index==0){
            return "666";
        }
        String result="";
        int start=endArr[index-1][2]+1;
        int beforeSize = size[index] + size[index-1];
        if(endArr[index][0]>num){
            result=(num/beforeSize)
                    +makeNum(endArr,size,index-1,num%beforeSize);
        }else if(endArr[index][2]>num){
            result=(6+(num-endArr[index][1])/beforeSize)
                    +makeNum(endArr,size,index-1,(num-endArr[index][1])%beforeSize);
        }else{
            if(endArr[index][1]+size[index-1]*5>num){
                result=(num/beforeSize)
                        +makeNum(endArr,size,index-1,num%beforeSize);
            }else if(endArr[index][2]-size[index-1]*3<num){

            }
        }
        return result;
    }
}