package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class problem7620 {
    public static int min(int one, int two, int three) {
        return one < two ? one < two ? 1 : 2 : two < three ? 2 : 3;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        br.close();

        int[][] dp = new int[a.length() + 1][b.length() + 1];
        char[][] p = new char[a.length() + 1][b.length() + 1];
        for (int i = 1; i < a.length() + 1; i++) {
            dp[i][0] = i;
            p[i][0] = 'd';
        }
        for (int i = 1; i < b.length() + 1; i++) {
            dp[0][i] = i;
            p[0][i] = 'a';
        }
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    p[i][j] = 'a';
                } else {
                    int min = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]);
                    switch (min) {
                        case 1:
                            dp[i][j] = dp[i - 1][j - 1] + 1;
                            p[i][j] = 'm';
                            break;
                        case 2:
                            dp[i][j] = dp[i - 1][j] + 1;
                            p[i][j] = 'c';
                            break;
                        case 3:
                            dp[i][j] = dp[i][j - 1] + 1;
                            p[i][j] = 'd';
                            break;
                    }
                }
            }
        }
        LinkedList<String> list = new LinkedList();
        int i = a.length();
        int j = b.length();
        while (i > 0 || j > 0) {
            switch (p[i][j]) {
                case 'c':
                    list.addFirst("c " + a.charAt(i-1)+"\n");
                    i--;
                    j--;
                    break;
                case 'm':
                    list.addFirst("m " + a.charAt(i-1)+"\n");
                    i--;
                    j--;
                    break;
                case 'a':
                    list.addFirst("a " + b.charAt(j-1)+"\n");
                    j--;
                    break;
                case 'd':
                    list.addFirst("d " + a.charAt(i-1)+"\n");
                    i--;
                    break;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (String str : list) {
            sb.append(str);
        }
        System.out.println(sb.toString());
    }
}
