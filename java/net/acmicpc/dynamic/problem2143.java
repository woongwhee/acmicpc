package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class problem2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T= Integer.parseInt(br.readLine());
        int N= Integer.parseInt(br.readLine());
        String[] split=br.readLine().split(" ");
        int[] arrayA=parseArrayInput(split,N);
        int M= Integer.parseInt(br.readLine());
        split=br.readLine().split(" ");
        int[] arrayB=parseArrayInput(split,M);
        br.close();
        SubArraySumCounter sumCounter= new SubArraySumCounter(arrayA,arrayB,T);
        long result=sumCounter.countPairs();
        System.out.println(result);
    }
    static class SubArraySumCounter{
        private final int[] arrayA;
        private final int[] arrayB;
        private final int target;

        public SubArraySumCounter(int[] arrayA, int[] arrayB,int target) {
            this.arrayA = arrayA;
            this.arrayB = arrayB;
            this.target=target;
        }
        public long countPairs() {
            Map<Long, Integer> sumOccurrencesA = calculateArray(arrayA);
            Map<Long, Integer> sumOccurrencesB = calculateArray(arrayB);
            long count = 0;
            for (Map.Entry<Long, Integer> entry : sumOccurrencesA.entrySet()) {
                long complement = target - entry.getKey();
                count += (long)entry.getValue() * sumOccurrencesB.getOrDefault(complement, 0);
            }
            return count;
        }
        private Map<Long, Integer> calculateArray(int[] array) {
            Map<Long,Integer> sumOccurrences= new HashMap<>();
            for (int start = 0; start < array.length; start++) {
                long sum=0;
                for (int end = start; end < array.length; end++) {
                    sum=array[end];
                    sumOccurrences.merge(sum,1,Integer::sum);
                }
            }
            return sumOccurrences;
        }


    }

    private static int[] parseArrayInput(String[] input, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(input[i]);
        }
        return array;
    }
}
