package net.acmicpc.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class problem2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        long[] seq = new long[n];
        for (int i = 0; i < n; i++) {
            seq[i] = Long.parseLong(input[i]);
        }
        Arrays.sort(seq);
        long ret=Long.MAX_VALUE;
        long[] result=new long[3];
        for(int i=0;i<n-2;i++){
            int left = i+1;
            int right = n - 1;
            while (left < right) {
                long sum = seq[left] + seq[right] + seq[i];
                if (Math.abs(sum)< ret) {
                    ret = Math.abs(sum);
                    result[0] = seq[i];
                    result[1] = seq[left];
                    result[2] = seq[right];
                }
                if (sum<0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println(result[0] + " " +result[1] + " "+result[2]);
    }
}
