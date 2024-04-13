package net.acmicpc.exhaustivesearch.problem2789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class node{
    int data;
    node parent;
    node(int data,node parent){
        this.data=data;
        this.parent=parent;
    };

}

public class problem2789Backtracking {
    static int M;
    static int Max;
    static int[] arr;
    static final int card = 3;
    public static int searchBest(int deep,node parent) {
        if (deep == 0) {
            int result = 0;
            node current=parent;
            while (current != null) {
                result += current.data;
                current=current.parent;
            }
            return result;
        }
        int currentMax=0;
        loop:for (int i = 0; i < arr.length; i++) {
            if (deep != card) {//첫번째는 검사하지않음
                node current = parent;
                while (current != null) {
                    if(arr[i]==current.data){
                        continue loop;
                    }else{
                        current=current.parent;
                    }
                }
                int result=searchBest(deep-1,new node(arr[i],parent));
                if(result>M){
                    return currentMax;
                }else if(result>currentMax){                                
                    currentMax=result;
                    if(currentMax>Max){
                        Max=currentMax;
                    };
                }else if(result==M){
                    return M;
                }
            }else{
                int result=searchBest(deep-1,new node(arr[i],parent));
            }
        }
        return currentMax;

    }

    public static void main (String[]args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            arr = new int[N];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int max=0;
            Arrays.sort(arr);
            searchBest(card, null);
            System.out.println(Max);

    }

}