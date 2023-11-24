package net.acmicpc.greed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class problem12904 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String S=br.readLine();
        String T=br.readLine();
        String reverseT=flip(T);
        Comparator<String > com=new MyComparator();
        PriorityQueue<String> queue=new PriorityQueue<>(com);
        queue.add(S);
        int result=0;
        while (!queue.isEmpty()){
            String cur=queue.poll();
            if(cur.equals(T)){
                result=1;
                break;
            }
            if(cur.length()==T.length())continue;


        }
        System.out.println(result);
    }
    static String flip(String s){
        StringBuilder sb=new StringBuilder();
        for (int i = s.length()-1; i >=0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    static String addA(String s){
        return s+'A';
    }
    static String addB(String s){
        return s+'B';
    }
    static class MyComparator implements Comparator<String> {
        public int compare(String x, String y) {
            if (x.length() < y.length()) {
                return -1;
            }
            if (x.length() > y.length()) {
                return 1;
            }
            return 0;
        }
    }


}
