package net.acmicpc.realization;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem17144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int r = Integer.parseInt(split[0]);
        int c = Integer.parseInt(split[1]);
        int t = Integer.parseInt(split[2]);
        int[][] map = new int[r][c];
        int robot = 0;//행정보만 저장
        for (int i = 0; i < r; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }
        for (int i = 0; i < r; i++) {
            if (map[i][0] == -1) {
                robot = i;
                break;
            }
        }
        printMap(0,map);
        for (int i = 0; i < t; i++) {
            map=spreadOut(map,robot);
            windBlow(map,robot);
            printMap(i+1,map);
        }
        int result=2;//청소기값 보정 2
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                result+=map[i][j];
            }
        }
        System.out.println(result);
        System.out.println("robot "+map[robot][0]+","+map[robot+1][0]);
    }

    static void printMap(int step,int[][] map){
        StringBuffer sb=new StringBuffer("step "+step+"\n");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int[][] spreadOut(int[][] map,int robot) {
        int[][] newMap = new int[map.length][map[0].length];
        newMap[robot][0]=-1;
        newMap[robot+1][0]=-1;
        int[] dX = {1, -1, 0, 0};
        int[] dY = {0, 0, 1, -1};
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == -1) {
                    continue;
                }
                int spread = map[i][j] / 5;
                int spreadCount = 0;
                for (int k = 0; k < 4; k++) {
                    int nextX = i + dX[k];
                    int nextY = j + dY[k];
                    if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length) {
                        continue;
                    }
                    if (map[nextX][nextY]==-1) {
                        continue;
                    }
                    spreadCount++;
                    newMap[nextX][nextY]+=spread;
                }
                newMap[i][j]+=map[i][j]-(spread*spreadCount);
            }
        }
        return newMap;
    }

    static void windBlow(int[][] map, int robot) {
        for (int i = robot - 1; i > 0; i--) map[i][0] = map[i - 1][0];
        for (int i = 0; i < map[0].length - 1; i++) map[0][i] = map[0][i + 1];
        for (int i = 0; i < robot; i++) map[i][map[0].length - 1] = map[i + 1][map[0].length - 1];
        for (int i = map[0].length - 1; i > 1; i--) map[robot][i] = map[robot][i - 1];
        // 공기청정기 입구에 도달한 미세먼지 정화
        map[robot][1] = 0;

        // 공기청정기 아래쪽 순환
        // 순환 경로 따라 한 칸씩 미세먼지 이동
        for (int i = robot + 2; i < map.length - 1; i++) map[i][0] = map[i + 1][0];
        for (int i = 0; i < map[0].length - 1; i++) map[map.length - 1][i] = map[map.length - 1][i + 1];
        for (int i = map.length - 1; i > robot + 1; i--) map[i][map[0].length - 1] = map[i - 1][map[0].length - 1];
        for (int i = map[0].length - 1; i > 1; i--) map[robot + 1][i] = map[robot + 1][i - 1];
        map[robot + 1][1] = 0;

    }

}
