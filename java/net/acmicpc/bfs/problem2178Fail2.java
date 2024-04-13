package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2178Fail2 {
    static boolean[][] map;
    static int Min;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] size= br.readLine().split(" ");
        int N=Integer.parseInt(size[0]);
        int M=Integer.parseInt(size[1]);
        Min=Integer.MAX_VALUE;
        map=new boolean[N][M];
        for (int i = 0; i < N; i++){
            String str=br.readLine();
            for (int j = 0; j < M; j++) {
                if(str.charAt(j)=='1'){
                    map[i][j]=true;
                }
            }
        }
        bfs(0,0,1);
        System.out.println(Min);

    }

     public static boolean bfs(int x,int y,int length){
        boolean result=false;
         if(x==map.length-1&&y==map[0].length-1){
             Min=Math.min(Min,length);
             return true;
         }

            if(x!=map.length-1&&map[x+1][y]){
                map[x+1][y]=false;
                result=bfs(x+1,y,length+1);
                map[x+1][y]=true;
            }
            if(y!=map[0].length-1&&map[x][y+1]&&!result){
                 map[x][y+1]=false;
                 result=bfs(x,y+1,length+1);
                 map[x][y+1]=true;
            }
             if(y!=0&&map[x][y-1]&&!result){
                map[x][y-1]=false;
                 result=bfs(x,y-1,length+1);
                map[x][y-1]=true;
            }

            if(x!=0&&map[x-1][y]&&!result){
                map[x-1][y]=false;
                result=bfs(x-1,y,length+1);
                map[x-1][y]=true;
            }
         return result;
     }

}
