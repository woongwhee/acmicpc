package net.acmicpc.hdr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2 {
    final static int[] DX = {1, -1, 0, 0};
    final static int[] DY = {0, 0, 1, -1};
    static int answer;
    static StringBuffer sb;
    static boolean[][] visitMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        if (x * y % 2 == 0) {
            answer = x * y;
        } else {
            answer = x * y - 1;
        }
        visitMap = new boolean[x + 1][y + 1];
        visitMap[1][1] = true;
        sb = new StringBuffer(answer + "\n");
        dfs(1, 1, 1);
        sb.append("1 1\n");
        System.out.println(sb.toString());
    }

    static boolean dfs(int x, int y, int deep) {
        for (int i = 0; i < 4; i++) {
            int curX = x + DX[i];
            int curY = y + DY[i];
            if (curX < 1 || curY < 1 || curX >= visitMap.length || curY >= visitMap[0].length) {
                continue;
            }
            if (visitMap[curX][curY]) {
                continue;
            }
            visitMap[curX][curY] = true;
            boolean result = dfs(curX, curY, deep + 1);
            visitMap[curX][curY] = false;
            if (result) {
                sb.append(curX);
                sb.append(" ");
                sb.append(curY);
                sb.append("\n");
                return true;
            }
        }
        if (deep == answer) {
            if ((x - 1 == 1 && y == 1) || (y - 1 == 1 && x == 1)) {
                return true;
            }
        }
        return false;
    }


}
