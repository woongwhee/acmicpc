package net.acmicpc.gazicup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class problemA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> hashMap = new HashMap<>();
        int index = 0;
        int[][] data = new int[10][10];
        for (int i = 0; i < 10; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < 10; j++) {
                if (hashMap.containsKey(split[j])) {
                    data[i][j] = hashMap.get(split[j]);
                } else {
                    data[i][j]=index;
                    hashMap.put(split[j], index++);
                }
            }
        }
        System.out.println(findDurum(data));
    }

    static int findDurum(int[][] data) {
        //가로
        for (int i = 0; i < 10; i++) {
            boolean find=true;
            for (int j = 1; j < 10; j++) {
                if(data[i][0]!=data[i][j]){
                    find=false;
                    break;
                }
            }
            if(find){
                return 1;
            }
        }
        //새로
        for (int i = 0; i < 10; i++) {
            boolean find=true;
            for (int j = 1; j < 10; j++) {
                if(data[0][i]!=data[j][i]){
                    find=false;
                    break;
                }
            }
            if(find){
                return 1;
            }
        }
        return 0;
    }
}
