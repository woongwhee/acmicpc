package net.acmicpc.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2580 {
    static int[][] map;
    static boolean[][] row;
    static boolean[][] column;
    static boolean[][] box;
    static int[] numcheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        //가로 세로 상자 별로 체크용배열
        row = new boolean[9][10];
        column = new boolean[9][10];
        box = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num != 0) {
                    row[i][num] = true;
                    column[j][num] = true;
                    box[boxPostion(i, j)][num] = true;
                }
            }
        }
//        checkEigth();
        checkSudoku(0,0);
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }

    /**백트래킹으로 빈칸을 채우는 함수*/
    static boolean checkSudoku(int x, int y) {
        //마지막줄에 도달할시 true를 반환
        if(y==9){
            return true;
        }
        int nextX=x<8?x+1:0;
        int nextY=x==8?y+1:y;
        if(map[x][y]!=0){
            return checkSudoku(nextX,nextY);
        }
        for (int k = 1; k <= 9; k++) {
            int bp=boxPostion(x,y);
            if(row[x][k]||column[y][k]||box[bp][k]){
                continue;
            }
            row[x][k]=true;
            column[y][k]=true;
            box[bp][k]=true;
            map[x][y]=k;
            if(checkSudoku(nextX,nextY)){
                return true;
            };
            row[x][k]=false;
            column[y][k]=false;
            box[bp][k]=false;
            map[x][y]=0;

        }
        return false;
    }
    /** 몇번째 네모칸인지 반환*/
    static int boxPostion(int x, int y) {
        int boxNum = 0;
        if (x / 3 == 0) {
            if (y / 3 == 1) {
                boxNum = 1;
            } else if (y / 3 == 2) {
                boxNum = 2;
            }
        } else if (x / 3 == 1) {
            if (y / 3 == 0) {
                boxNum = 3;
            } else if (y / 3 == 1) {
                boxNum = 4;
            } else {
                boxNum = 5;
            }
        } else {
            if (y / 3 == 0) {
                boxNum = 6;
            } else if (y / 3 == 1) {
                boxNum = 7;
            } else {
                boxNum = 8;
            }
        }
        return boxNum;
    }
//    /** 확정적인 빈칸을 채우고 시작하는 함수*/
//    static void checkEigth() {
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if(map[i][j]>0){
//                    continue;
//                }
//                int bp=boxPostion(i,j);
//                int ZeroCount = 0;
//
//                for (int k = 1; k <= 9; k++) {
//                    if (!row[i][k]&&++ZeroCount>1){
//                        ZeroCount=0;
//                        //빈칸이 2칸이상인경우 반복문종료
//                        break;
//                    }
//                }
//                if(ZeroCount==1){
//                    for (int k = 0; k < 9; k++) {
//                        if(!row[i][k]){
//                            row[i][k]=true;
//                            column[j][k]=true;
//                            box[bp][k]=true;
//                            map[i][j]=k;
//                            checkEigth();
//                            return;
//                        }
//                    }
//                }
//
//                for (int k = 1; k <= 9; k++) {
//                    if (!column[j][k]&&ZeroCount++>1){
//                        ZeroCount=0;
//                        break;
//                    }
//                }
//                if(ZeroCount==1){
//                    for (int k = 0; k < 9; k++) {
//                        if(!column[j][k]){
//                            row[i][k]=true;
//                            column[j][k]=true;
//                            box[bp][k]=true;
//                            map[i][j]=k;
//                            checkEigth();
//                            return;
//                        }
//                    }
//                }
//
//                for (int k = 1; k <= 9; k++) {
//                    if (!box[bp][k]&&ZeroCount++>1){
//                        ZeroCount=0;
//                        break;
//                    }
//                }
//                if(ZeroCount==1){
//                    for (int k = 0; k < 9; k++) {
//                        if(!column[j][k]){
//                            row[i][k]=true;
//                            column[j][k]=true;
//                            box[bp][k]=true;
//                            map[i][j]=k;
//                            checkEigth();
//                            return;
//                        }
//                    }
//                }
//            }
//        }
//
//    }

}
