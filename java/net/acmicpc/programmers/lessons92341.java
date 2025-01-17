package net.acmicpc.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class lessons92341 {
    class Solution {
        private int parseStringToSec(String strTime) {
            String[] splits = strTime.split(":");
            return Integer.parseInt(splits[0]) * 60 + Integer.parseInt(splits[1]);
        }

        //int[] fees [기본시간(분),기본요금,단위시간(분),단위요금]
        private int calcPrice(int time, int[] fees) {
            int overTime = time-fees[0];
            if(overTime<=0){
                return fees[1];
            }
            int unit = (int) Math.ceil((double) overTime / fees[2]); // 초과 시간 계산
            return unit * fees[3] + fees[1];
        }

        public int[] solution(int[] fees, String[] records) {
            Map<Integer, Integer> inTimeMap = new HashMap();
            Map<Integer, Integer> outTimeMap = new TreeMap();
            for (int i = 0; i < records.length; i++) {
                String[] record = records[i].split(" ");
                int time = this.parseStringToSec(record[0]);
                int carNum = Integer.parseInt(record[1]);
                boolean isIn = record[2].equals("IN");
                if (isIn) {//입차 처리
                    inTimeMap.put(carNum, time);
                } else {//출차 처리
                    //실제 예외가 있다면 에러
                    int inTime = inTimeMap.get(carNum);
                    inTimeMap.remove(carNum);
                    if (!outTimeMap.containsKey(carNum)) {
                        outTimeMap.put(carNum, time - inTime);
                    } else {
                        outTimeMap.put(carNum, outTimeMap.get(carNum) + (time - inTime));
                    }
                }
            }
            int dayOver = this.parseStringToSec("23:59");
            for (Map.Entry<Integer, Integer> entry : inTimeMap.entrySet()) {
                int carNum = entry.getKey();
                int inTime = entry.getValue();
                int time =dayOver - inTime;
                outTimeMap.put(carNum, time);
            }
            int i = 0;
            int[] result = new int[outTimeMap.size()];
            for (Map.Entry<Integer,Integer>entry : outTimeMap.entrySet()) {
                int price = calcPrice(entry.getValue(), fees);
                result[i++] = price;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution s = new lessons92341().new Solution();
        int[][] feesSet = {
//                {180, 5000, 10, 600},
//                {1, 461, 1, 10},
                {120, 0, 60, 591}};

        String[][] recordsSet = {
//                case 1
//                {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"}
//                case 2
//                ,{"00:00 1234 IN"},
//                case 3
                {"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"}
        };
        int[][] expectedResults = {
//                {14600, 34400, 5000},
//                {14841},
                {0, 591}
        };

        for (int i = 0; i < feesSet.length; i++) {
            int[] result = s.solution(feesSet[i], recordsSet[i]);
            System.out.println(Arrays.equals(result, expectedResults[i])+": 결과"+Arrays.toString(result)+"예상"+Arrays.toString(expectedResults[i]));
        }
    }
}
