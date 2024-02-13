package net.acmicpc;

import java.util.*;
public class Solution {
    Map<Integer,Map<Integer,Integer>>resultMap;
    int[][] dice;
    int maxWinRate=0;
    int maxPair=0;
    int mask;
    public int[] solution(int[][] dice) {
        this.dice=dice;
        this.resultMap=new HashMap();
        this.mask = 1<<dice.length;
        Map<Integer,Integer> zeroMap=new HashMap<>();
        zeroMap.put(0,1);
        resultMap.put(1,zeroMap);
        generateCombinations(dice.length/2,1,0);
        int[] result=bitToArray(maxPair);
        return result;
    }
    public int[] bitToArray(int bit){
        Set<Integer> resultSet=new HashSet();
        for(int i=dice.length;i>0;i--){
            if((bit&1)==1){
                resultSet.add(i);
            }
            bit=bit>>1;
        }
        int[] result=resultSet.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }
    //완전탐색
    public void generateCombinations(int toSelect,int current,int flag){
        if(toSelect==0){
            Map<Integer,Integer> result =resultMap.get(current);
            while(current < mask){
                current=current<<1;
                resultMap.put(current,result);
            }
            checkWinRate(current);
            return;
        }
        int remaining= dice.length-flag;
        if(remaining==toSelect){
            Map<Integer,Integer> cur=resultMap.get(current);
            while (mask >current){
                cur=makePair(cur,dice[flag++]);
                current=current<<1|1;
                resultMap.put(current,cur);
            }
            checkWinRate(current);
            return;
        }


        Map<Integer,Integer> cur=resultMap.get(current);
        Map<Integer,Integer> next=makePair(cur,dice[flag]);
        resultMap.put(current<<1,cur);
        generateCombinations(toSelect,current<<1,flag+1);
        resultMap.put(current<<1|1,next);
        generateCombinations(toSelect-1,current<<1|1,flag+1);

    }
    private Map<Integer,Integer>  makePair(Map<Integer,Integer> pair1,int[] dice){
        Map<Integer,Integer>  result=new HashMap<>();
        for(Map.Entry<Integer,Integer> entry:pair1.entrySet()){
            for(int j=0;j<6;j++){
                result.merge(entry.getKey()+dice[j],entry.getValue(),Integer::sum);
            }
        }
        return result;
    }
    private int flipBit(int origin){
        int bitLength = dice.length;
        int mask = (1 << (bitLength)) - 1;
        // 식별 코드를 제외하고 비트 반전
        int flipped = (origin ^ mask)|(1<<bitLength);
        return flipped;
    }
    public void checkWinRate(int pair){

        if(pair< mask)return;
        int flipPair=flipBit(pair);
        if(!resultMap.containsKey(flipPair))return;
        Map<Integer, Integer> pair1 = resultMap.get(pair);
        Map<Integer, Integer> pair2 = resultMap.get(flipPair);
        int win1=0;
        int win2=0;
        for (Map.Entry<Integer,Integer> e1:pair1.entrySet()) {
            for (Map.Entry<Integer,Integer> e2:pair2.entrySet()) {
                if(e1.getKey()>e2.getKey()){
                    win1+=e1.getValue()*e2.getValue();
                } else if (e2.getKey()>e1.getKey()) {
                    win2+=e1.getValue()*e2.getValue();
                }
            }
        }
        if(maxWinRate<win1){
            maxPair=pair;
            maxWinRate=win1;
        }
        if(maxWinRate<win2){
            maxPair=flipPair;
            maxWinRate=win2;
        }
    }



}