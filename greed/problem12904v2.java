package net.acmicpc.greed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem12904v2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());
        br.close();
        int flag = 0;
        while (S.length() < T.length()) {
            if (T.charAt(T.length() - 1) == 'A') {
                T.delete(T.length() - 1, T.length());
            } else {
                T.delete(T.length() - 1, T.length());
                T.reverse();
            }
            if (S.length() == T.length()) {
                if (S.toString().equals(T.toString())) {
                    flag = 1;
                }
                break;
            }

        }
        System.out.println(flag);

    }
}
