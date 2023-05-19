package net.acmicpc;
class Solution {
    public int[] solution(int[][] rules,int m) {
        int[][] arr=new int[m][rules.length];
        for (int i = 0; i < rules.length; i++) {
            arr[0][i]=1;
        }
        for (int i = 1; i <m ; i++) {
            for (int j = 0; i <rules.length ; i++) {
                for (int k = 0; j < rules.length; j++) {
                    arr[i][rules[j][k]]+=arr[i][j];
                }
            }
        }
        return arr[m];
    }

}