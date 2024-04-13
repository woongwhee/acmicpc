package net.acmicpc.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem10026 {
    static int N;

    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};
    static char[][] map;
    static char[][] RGmap;
    static boolean[][] check;
    static boolean[][] check2;

    public static void checkAria(int x,int y){
        check[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int nextX=x+dx[i];
            int nextY=y+dy[i];
            if(nextX<0||nextY<0||nextX>=N||nextY>=N){
                continue;
            }
            if(check[nextX][nextY]){
                continue;
            }
            if(map[x][y]!=map[nextX][nextY]){
                continue;
            }
            checkAria(nextX,nextY);
        }
    }
    public static void checkRGAria(int x,int y){
            check2[x][y]=true;
            for (int i = 0; i < 4; i++) {
                int nextX=x+dx[i];
                int nextY=y+dy[i];
                if(nextX<0||nextY<0||nextX>=N||nextY>=N){
                    continue;
                }
                if(check2[nextX][nextY]){
                    continue;
                }
                if(RGmap[x][y]!=RGmap[nextX][nextY]){
                    continue;
                }
                checkRGAria(nextX,nextY);
            }

        }
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map=new char[N][N];
        RGmap=new char[N][N];
        for (int i = 0; i <N ; i++) {
            String str=br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j]=str.charAt(j);

                if(str.charAt(j)=='R'){
                    RGmap[i][j]='G';
                }else {
                    RGmap[i][j]=str.charAt(j);
                }
            }
        }
        check=new boolean[N][N];
        check2=new boolean[N][N];
        int rgb=0;
        int rb=0;
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j]){
                    checkAria(i,j);
                    rgb++;
                }
                if(!check2[i][j]){
                    checkRGAria(i,j);
                    rb++;
                }
            }
        }
        System.out.println(rgb+" "+rb);


    }

}
