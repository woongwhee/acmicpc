package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class problem12851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);
        System.out.println(find(start, end));
    }

    public static String find(int start, int end) {
        if (start >= end) {
            return start - end + "\n" + 1;
        }
        int limit = end * 2;
        int[] minTime = new int[limit + 1];
        int[] path = new int[limit + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        path[start] = 1;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : new int[]{current + 1, current - 1, current * 2}) {
                if (next <= 0 || next > limit) {
                    continue;
                }
                if (minTime[next] == 0 && next != start) {
                    minTime[next]=minTime[current]+1;
                    path[next]=path[current];
                    queue.add(next);
                } else if (minTime[next] == minTime[current] + 1) {
                    path[next]+=path[current];
                }
            }
        }
        return minTime[end]+"\n"+path[end];
    }


}
