package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
    https://www.acmicpc.net/problem/1043 거짓말
 */
public class problem1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");
        //사람수
        int N = Integer.parseInt(strArr[0]);
        //파티수
        int M = Integer.parseInt(strArr[1]);
        //알고 있는 사람수
        String[] knownStr=br.readLine().split(" ");
        int[] knownList=new int[Integer.parseInt(knownStr[0])];
        for (int i = 0; i < knownList.length; i++) {
            knownList[i]=Integer.parseInt(knownStr[i+1]);
        }
        // 파티 정보
        Party[] partyList=new Party[M];
        for (int i = 0; i < M; i++) {
            partyList[i]=new Party(N);
        }

        Human[] humanList=new Human[N+1];
        for (int i = 0; i <= N; i++) {
            humanList[i]=new Human(M);
        }
        for (int i = 0; i < M; i++) {
            String[] pArr=br.readLine().split(" ");
            for (int j = 1; j < pArr.length; j++) {
                int current=Integer.parseInt(pArr[j]);
                partyList[i].visitHuman.add(humanList[current]);
                humanList[current].partyList.add(partyList[i]);
            }
        }
        Queue<Party> queue=new LinkedList();
        for (int i = 0; i < knownList.length; i++) {
            Human cur=humanList[knownList[i]];
            queue.addAll(cur.partyList);
        }
        while(!queue.isEmpty()){
            Party cur=queue.poll();
            if(cur.visit){
                continue;
            }
            cur.visit=true;
            cur.canLie=false;
            for (Human visit:cur.visitHuman) {
                for (Party p:visit.partyList) {
                    if(p.canLie){
                       queue.add(p);
                    }
                }
            }
        }
        int result=0;
        for(Party p:partyList){
            if(p.canLie){
                result++;
            }
        }
        System.out.println(result);
    }
    static class Party{
        ArrayList<Human> visitHuman;
        boolean canLie;
        boolean visit;
        public Party(int N) {
            this.visitHuman = new ArrayList<>(N);
            this.canLie = true;
        }
    }
    static class Human{
        ArrayList<Party> partyList;

        public Human(int m) {
            this.partyList = new ArrayList<Party>(m);
        }
    }
}
