package net.acmicpc.greed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//다시푼 방법 코드는 간결해 젔지만 시간,메모리는 획기적으로 줄이진 못했다.


public class problem1932v2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        int[][]triangle=new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = N-2; i >=0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
        System.out.println(triangle[0][0]);
    }
}
