package net.acmicpc.greed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class problem1202 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);


        PriorityQueue<Jewelry> pq = new PriorityQueue<>();
        PriorityQueue<Jewelry> pq2 = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return Integer.compare(o1.m,o2.m);
            }
        });
        for (int i = 0; i < N; i++) {
            String[] mv = br.readLine().split(" ");
            pq2.add(new Jewelry(Integer.parseInt(mv[0]), Integer.parseInt(mv[1])));
        }
        int[] back = new int[K];
        int[] value = new int[K];
        for (int i = 0; i < K; i++) {
            back[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        Arrays.sort(back);

        for (int i = 0; i < K; i++) {
            while(!pq2.isEmpty()){
                if(pq2.peek().m<=back[i]){
                    pq.add(pq2.poll());
                }else{
                    break;
                }
            }
            if(!pq.isEmpty()){
              value[i]=pq.poll().v;
            }
        }
        long result=0;
        for (int i = 0; i < K; i++) {
            result+=value[i];
        }
        System.out.println(result);
    }

    public static class Jewelry implements Comparable<Jewelry> {
        int m;
        int v;
        public Jewelry(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewelry o) {
            return Integer.compare(o.v, v);
        }

    }
}
