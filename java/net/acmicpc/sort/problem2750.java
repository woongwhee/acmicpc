package net.acmicpc.sort;

import java.io.*;

public class problem2750 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        mergeSort(arr);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void mergeSort(int[] arr) {
        int[] sorted = new int[arr.length];
        division(arr, sorted, 0, arr.length - 1);
    }


    static void division(int arr[], int[] sorted, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            division(arr, sorted, left, mid);
            division(arr, sorted, mid + 1, right);
            merge(arr, sorted, left, mid, right);
        }
    }

    private static void merge(int[] arr, int[] sorted, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                sorted[k++] = arr[i++];
            } else {
                sorted[k++] = arr[j++];
            }
        }
        while(i<=mid){
            sorted[k++] = arr[i++];
        }
        while(j<=right){
            sorted[k++] = arr[j++];
        }
        for (int l = left; l <= right; l++) {
            arr[l] = sorted[l];
        }
    }
}
