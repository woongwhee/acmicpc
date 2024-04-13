package net.acmicpc.dynamic;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
/**
 * 이동하기
 * https://www.acmicpc.net/problem/11048
 * 사탕을 가장 많이 줍는 루트를 저장하는 배열을 따로 하나 만들거
 * 저장용 배열 레버 1,2,5 위치에있는값중 최대값과 해당위치에잇는 사탕의 값을 더한다.
 */
public class problem11048 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int[][] map=new int[N+1][M+1];
        int[][] countMap=new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int maxRoot=Math.max(Math.max(countMap[i][j-1],countMap[i-1][j]),countMap[i-1][j-1]);
                countMap[i][j]=map[i][j]+maxRoot;
            }
        }
        System.out.println(countMap[N][M]);
    }


}
