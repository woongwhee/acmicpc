package net.acmicpc.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class problem9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input= br.readLine();
        char[] boom = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<Character>();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
            if(stack.size()>=boom.length){
                boolean isBoom=true;
                for (int j = 1; j <=boom.length ; j++) {
                    if(boom[boom.length-j]!=stack.get(stack.size()-j)){
                        isBoom=false;
                        break;
                    }
                }
                if(isBoom){
                    for (int j = 0; j < boom.length; j++) {
                        stack.pop();
                    }
                }
            }
        }
        for (char c:stack) {
            result.append(c);
        }
        System.out.println(result.length() > 0 ? result.toString() : "FRULA");

    }
}
