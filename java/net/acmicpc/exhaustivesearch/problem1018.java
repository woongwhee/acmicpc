package net.acmicpc.exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] chess = new String[N];
        for (int i = 0; i < N; i++) {
            chess[i] = br.readLine();
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                int count = 0;
                for (int k = i; k < i + 8; k++) {
                    for (int q = j; q < j + 8; q++) {
                        if ((k - i + q - j) % 2 == 0 && chess[k].charAt(q) == 'B') {
                            count++;
                        }else if((k - i + q - j) % 2 != 0 && chess[k].charAt(q) == 'W'){
                            count++;
                        }
                    }
                }
                count = count > 32 ? 64 - count : count;
                result = Math.min(result, count);
            }
        }
        System.out.println(result);
    }
}
