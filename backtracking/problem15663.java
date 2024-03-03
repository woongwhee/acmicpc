package net.acmicpc.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem15663 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] inputString=br.readLine().split(" ");
        int n= Integer.parseInt(inputString[0]);
        int m= Integer.parseInt(inputString[1]);
        inputString=br.readLine().split(" ");
        int[] inputToInt=new int[n];
        for (int i = 0; i < n; i++) {
            inputToInt[i]= Integer.parseInt(inputString[i]);
        }
        Arrays.sort(inputToInt);
        LinkedHashSet<String> resultList=new LinkedHashSet<>();
        StringBuffer sb=new StringBuffer();
        tracking(resultList,"",new boolean[n],inputToInt,m);
        for (String castStr:resultList) {
            sb.append(castStr).append("\n");
        }
        System.out.println(sb.toString());

    }
    public static void tracking(LinkedHashSet<String> resultList,String str,boolean[] visit,int[] nums,int toSelect){
        if(toSelect==0){
            resultList.add(str);
            return ;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!visit[i]){
                visit[i]=true;
                tracking(resultList,str+nums[i]+" ",visit,nums,toSelect-1);
                visit[i]=false;
            }

        }
    }
}
