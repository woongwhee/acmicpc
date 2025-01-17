package net.acmicpc.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class problem12789 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] splits = br.readLine().split(" ");
        Queue<Integer> queue = new LinkedList<>();
        int min = 1;
        for (int i = 0; i < splits.length; i++) {
            queue.add(Integer.parseInt(splits[i]));
        }
        Stack<Integer> anotherLine = new Stack<>();
        Integer last = 0;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            if (cur == min) {
                min++;
            } else if ((!anotherLine.isEmpty() && cur < anotherLine.peek()) || anotherLine.isEmpty()) {
                if (!anotherLine.isEmpty() && anotherLine.peek() == min) {
                    min++;
                    anotherLine.pop();
                }
                anotherLine.push(cur);
            } else if (!anotherLine.isEmpty() && anotherLine.peek() == min) {
                min++;
                anotherLine.pop();
                anotherLine.push(cur);
            } else {

                break;


            }
            if (queue.isEmpty() && !anotherLine.isEmpty()) {
                while (!anotherLine.isEmpty()) {
                    cur = anotherLine.pop();
                    if (cur != min++) {
                        anotherLine.push(cur);
                        break;
                    }
                }
            }
        }

        String result = "Sad";
        if (anotherLine.isEmpty()) {
            result = "Nice";
        }
        System.out.println(result);

    }

}
