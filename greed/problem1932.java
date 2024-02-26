package net.acmicpc.greed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int triangleSize = 0;
        for (int i = 1; i <= size; i++) {
            triangleSize += i;
        }
        int[] levelStart = new int[size + 1];
        int[] triangle = new int[triangleSize];
        int startIndex = 0;
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while (st.hasMoreTokens()) {
                triangle[startIndex + j] = Integer.parseInt(st.nextToken());
                j++;
            }
            startIndex += j;
            levelStart[i + 1] = startIndex;
        }
        int[] dp = new int[triangle.length];
        System.arraycopy(triangle, 0, dp, 0, triangle.length); // 삼각형의 마지막 레벨을 dp에 복사
        for (int level = size-1; level >0; level--) {
            for (int cur = levelStart[level-1]; cur < levelStart[level]; cur++) {
                int left = cur + level;
                int right = cur + level + 1;
                dp[cur] = triangle[cur] + Math.max(dp[left], dp[right]);
            }
        }
        System.out.println(dp[0]);
    }


}

