package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class problem1208 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int s = Integer.parseInt(split[1]);
        int[] sequence = new int[n];
        split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(split[i]);
        }
        HashMap<Integer, Long> frontMap = new HashMap<>();//sum,count
        HashMap<Integer, Long> rearMap = new HashMap<>();//sum,count
        calculateSums(sequence,0,n/2,frontMap,0);
        calculateSums(sequence,n/2,n,rearMap,0);
        long result=0;
        for(Integer value:frontMap.keySet()){
            if(rearMap.containsKey(s-value)){
                result+=frontMap.get(value)*rearMap.get(s-value);
            }
        }
        result+=frontMap.getOrDefault(s,0L);
        result+=rearMap.getOrDefault(s,0L);
        System.out.println(result);
    }

    static void calculateSums(int[] sequence, int cur, int end, Map<Integer, Long> map, int value) {
        if (cur == end) {
            return;
        }
        calculateSums(sequence,cur+1,end,map,value);//선택안하는경우
        value += sequence[cur];
        map.merge(value,1L,Long::sum);
        calculateSums(sequence,cur+1,end,map,value);
    }
}
