package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class problem16053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);
        int result = bfs(start, end);
        System.out.println(result);
    }

    static class Node {
        int num, t;

        public Node(int num, int t) {
            this.num = num;
            this.t = t;
        }
    }

    static int bfs(int start, int end) {
        if (start > end) {
            return -1;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.add(new Node(end,1));
        while (!queue.isEmpty()) {
            Node cur=queue.poll();
            if(cur.num==start){
                return cur.t;
            }else if(cur.num<start){
                continue;
            }
            if(cur.num%10==1){
                queue.add(new Node(cur.num/10,cur.t+1));

            }
            if(cur.num%2==0){
                queue.add(new Node(cur.num/2,cur.t+1));
            }
        }
        return -1;
    }
}
