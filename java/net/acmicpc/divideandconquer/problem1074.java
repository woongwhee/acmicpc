package net.acmicpc.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int n = Integer.parseInt(arr[0]);
        int x = Integer.parseInt(arr[1]);
        int y = Integer.parseInt(arr[2]);
        int startX = 0;
        int startY = 0;
        int result=0;
        int curLength = (int) Math.pow(2,n);
        for (int i = 0; i < n; i++) {
            int space = 0;//사분면 -1
            if (x < startX + curLength / 2) {
                if (y < startY + curLength / 2) {
                    space = 0;
                } else {
                    startY += curLength /2;
                    space = 1;
                }
            } else {
                startX+=curLength/2;
                if (y < startY + curLength / 2) {
                    space = 2;
                } else {
                    startY += curLength/2;
                    space = 3;
                }
            }
            result+= curLength*curLength*space/4;
            curLength/=2;
        }
        System.out.println(result);
    }

}



