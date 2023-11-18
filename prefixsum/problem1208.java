package net.acmicpc.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1208 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int S = Integer.parseInt(split[1]);
        int[] sequence = new int[N];
        split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(split[i]);
        }

        int s = 0, e = 0, sum = 0, count = 0;
        while (true) {
            if (sum == S) {
                count++;
                if (sequence[s] > 0) {
                    sum -= sequence[s++];
                } else {
                    sum += sequence[s++];
                }
            } else {

            }
        }
    }
}
