package net.acmicpc.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int aSize= Integer.parseInt(br.readLine());
        int[] hands= new int[aSize];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for (int i = 0; i <aSize ; i++) {
            hands[i]=Integer.parseInt(st.nextToken());
        }
        int bSize= Integer.parseInt(br.readLine());
        int[] hasCard=new int[bSize];
        st=new StringTokenizer(br.readLine());
        Map<Integer,Integer> deck=new TreeMap<>();
        for (int i = 0; i <bSize ; i++) {
            deck.put(Integer.parseInt(st.nextToken()),i);
        }
        for (int i = 0; i < aSize; i++) {
            Integer value=deck.get(hands[i]);
            if(value!=null){
                hasCard[value]=1;
            }
        }
        StringBuffer sb=new StringBuffer();
        for (int i : hasCard) {
            sb.append(i);
            sb.append(" ");
        }
        System.out.println(sb);

    }
}
