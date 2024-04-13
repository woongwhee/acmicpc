package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class problem1918 {


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();
        String result=infixToPostfix(str);
        System.out.println(result);
    }

    private static String infixToPostfix(String infix) {
        char[] infixArr = infix.toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < infixArr.length; i++) {
            switch (infixArr[i]) {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    Character top = stack.pop();
                    while (top != '(') {
                        sb.append(top);
                        if (!stack.isEmpty()) {
                            top=stack.pop();
                        } else {
                            break;
                        }
                    }
                    break;
                case '+':
                case '-':
                    while (!stack.isEmpty()&& stack.peek()!='(') {
                        sb.append(stack.pop());
                    }
                        stack.push(infixArr[i]);
                    break;
                case '*':
                case '/':
                    while(!stack.isEmpty()&&(stack.peek()=='*'||stack.peek()=='/')){
                        sb.append(stack.pop());
                    }
                    stack.push(infixArr[i]);
                    break;
                default:
                    if (infixArr[i] >= 'A' && infixArr[i] <= 'Z') {
                        sb.append(infixArr[i]);
                    }else{

                    }

            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}

