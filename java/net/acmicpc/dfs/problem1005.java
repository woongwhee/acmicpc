package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class problem1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < testCase; j++) {
            String[] split = br.readLine().split(" ");
            int building = Integer.parseInt(split[0]);
            int buildRule = Integer.parseInt(split[1]);
            int[] time = new int[building + 1];
            split = br.readLine().split(" ");
            for (int i = 0; i < building; i++) {
                time[i + 1] = Integer.parseInt(split[i]);
            }
            List<Integer>[] techTree = new List[building + 1];
            for (int i = 0; i <= building; i++) {
                techTree[i] = new ArrayList();
            }
            for (int i = 0; i < buildRule; i++) {
                split = br.readLine().split(" ");
                int parent = Integer.parseInt(split[0]);
                int child = Integer.parseInt(split[1]);
                techTree[child].add(parent);
            }
            int target = Integer.parseInt(br.readLine());
            int[] range = new int[building + 1];
            Arrays.fill(range, Integer.MAX_VALUE);
            int result = dfs(techTree, time, target, range);
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int dfs(List<Integer>[] techTree, int[] times, int cur, int[] range) {
        //방문해야될 리스트 및 루트 노드 리스트 작성
        int max = Integer.MIN_VALUE;
        for (int parent : techTree[cur]) {
            if (range[parent] == Integer.MAX_VALUE) {
                range[parent] = dfs(techTree, times, parent, range);
            }
            max = Math.max(range[parent], max);
        }
        range[cur] = times[cur] + (max == Integer.MIN_VALUE ? 0 : max);
        return range[cur];
    }
}
