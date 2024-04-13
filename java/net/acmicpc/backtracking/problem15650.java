package net.acmicpc.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class problem15650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] input=br.readLine().split(" ");
        int n= Integer.parseInt(input[0]);
        int m= Integer.parseInt(input[1]);
        List<String> caseList=new ArrayList<>();
        track(caseList,"",1,m,n);
        StringBuffer sb=new StringBuffer();
        for (String castStr:caseList) {
            sb.append(castStr).append("\n");
        }
        System.out.println(sb.toString());
    }
    static void track (List<String> result,String str, int current, int toSelect, int remain){
        if(toSelect==0){
            result.add(str);
            return;
        }
        if(toSelect==remain){
            for (int i = 0; i <remain ; i++) {
                str+=(current+i)+" ";
            }
            result.add(str);
            return;
        }
        track(result,str+current+" ",current+1,toSelect-1,remain-1);
        track(result,str,current+1,toSelect,remain-1);
    }


}