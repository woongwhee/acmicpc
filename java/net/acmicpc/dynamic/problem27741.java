//package net.acmicpc.dynamic;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class problem27741 {
//    static int[][] map;
//    static int N;
//    static List<Integer> divisorList;
//    static int dp[][][];
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//        int[][] map = new int[N][N];
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < N; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//
//            }
//        }
//        boolean turn = true;
//        divisorList = getDivisor(K);
//        dp=new int[divisorList.size()][N][N];
//        find(0, 0, K, turn);
//    }
//
//
//    public static List<Integer> getDivisor(int k) {
//        List<Integer> divisorList = new ArrayList<>();
//        for (int i = 1; i * i <= k; i++) {
//            if (k%i==0) {
//                divisorList.add(i);
//                if(i!=k/i){
//                    divisorList.add(k/i);
//                }
//            }
//        }
//        divisorList.sort(Comparator.reverseOrder());
//        return divisorList;
//    }
//
//
//    public static int find(int x, int y, int k, boolean turn) {
//        int result = 0;
//        int kIndex=divisorList.lastIndexOf(k);
//        for (int i = x + 1; i <= x + k && i < N; i++) {
//
//            if(dp[kIndex][i][y][0]==-1&&turn){
//                dp[kIndex][x][y][0]==-1;
//            }else if(dp[kIndex][i][y][1]==-1&&!turn){
//                dp[kIndex][x][y][0]==-1
//            } else if (dp[kIndex][i][y][]) {
//
//            }
//        }
//
//        for (int i = x + 1; i <= x + k && i < N; i++) {
//
//        }
//
//        return turn ? 0 : 1;
//
//
//
//    }
//
//
//}
