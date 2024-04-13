package net.acmicpc.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

public class problem3015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Pair[] pairs = new Pair[n + 1];
        for (int i = 1; i <= n; i++) {
            pairs[i] = new Pair(Integer.parseInt(br.readLine()));
        }
        Stack<Pair> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            while (!stack.isEmpty() && stack.peek().height < pairs[i].height) {
                Pair popped = stack.pop();
                pairs[i].count += popped.same;
                if (!stack.isEmpty()) {
                    stack.peek().count += popped.same;
                }
            }
            if (!stack.isEmpty() && stack.peek().height == pairs[i].height) {
                stack.peek().count += pairs[i].count+stack.peek().same;
                stack.peek().same++;
                pairs[i].count = 0;
            } else {
                stack.push(pairs[i]);
            }
        }
        while (!stack.isEmpty()) {
            Pair popped = stack.pop();
            if (!stack.isEmpty()) {
                stack.peek().count += popped.same;
            }
        }
        long result = 0;
        for (int i = 1; i <= n; i++) {
            result += pairs[i].count;
        }
        System.out.println(result);
    }

    static class Pair {
        long count;
        int same;
        int height;
        public Pair(int height) {
            this.height = height;
            this.same = 1;
        }
    }
}
