package net.acmicpc.dfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
    배열을통해 접근하면 시간 초과가 날수밖에 없는 구조다
    다이나믹 프로그래밍 개념을 이용해서 풀어야된다.
 */
public class problem1987 {
    static int R,C,BoardMax,Max;
    static char[][]Board;
    static boolean[]alpa;
    final static int[]DX={1,-1,0,0};
    final static int[]DY={0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] splited=br.readLine().split(" ");
        R=Integer.parseInt(splited[0]);
        C=Integer.parseInt(splited[1]);
        Max=Integer.MIN_VALUE;
        Board=new char[R][C];

        for (int i = 0; i < R; i++) {

            String st=br.readLine();

            for (int j = 0; j < C; j++) {
                Board[i][j]=st.charAt(j);
            }
        }

        alpa=new boolean[26];

        alpa[Board[0][0]-65]=true;

        advance(0,0,1);

        System.out.println(Max);
    }
    static void advance(int x,int y,int deep){
        int visitCount=0;

        for (int i = 0; i < 4; i++) {
            int nextX=x+DX[i];
            int nextY=y+DY[i];
            if(nextX<0||nextY<0||nextX>=R||nextY>=C)continue;
            int index=Board[nextX][nextY]-65;
            if(alpa[index])continue;
            alpa[index]=true;
            visitCount++;
            advance(nextX,nextY,deep+1);
            alpa[index]=false;
        }

        if (visitCount==0){
            Max=Math.max(Max,deep);
        }

    }
}
