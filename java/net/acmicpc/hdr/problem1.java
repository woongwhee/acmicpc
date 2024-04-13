package net.acmicpc.hdr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int nm = Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
        if (nm % 2 == 0) {
            System.out.println(nm);
        } else {
            System.out.println(nm-1);
        }

    }
}
