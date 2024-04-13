package net.acmicpc.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class problem15657 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] split=br.readLine().split(" ");
        int n= Integer.parseInt(split[0]);
        int m= Integer.parseInt(split[1]);
        int[] sequence=new int[n];
        split=br.readLine().split(" ");
        for (int i = 0; i <n ; i++) {
            sequence[i]= Integer.parseInt(split[i]);
        }
        Arrays.sort(sequence);
        List<String> result=new LinkedList<>();
        backtracking(result,m,0,sequence,"");
        StringBuffer sb=new StringBuffer();
        result.stream().forEach(e->sb.append(e).append("\n"));
        System.out.println(sb.toString());
    }
    static void backtracking(List<String> result,int toSelcet,int start,int[] sequence,String current){
        if(toSelcet==0){
            result.add(current);
            return ;
        }
        for(int i=start;i<sequence.length;i++){
            backtracking(result,toSelcet-1,i,sequence,current+sequence[i]+" ");
        }

    }
}
