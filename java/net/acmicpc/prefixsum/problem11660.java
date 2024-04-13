package net.acmicpc.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    배열을통해 접근하면 시간 초과가 날수밖에 없는 구조다
    누적합 개념을 이용해서 풀어야된다.
     반복문을 사용하여 i~k사이의 값을 더하는 알고리즘의 시간복잡도는 O(n)이다.
    이 같은 알고리즘을 사용할 경우 n의 값이 클 경우 이를 정해진 시간 내에 해결할 수 없다.
    하지만 구간 합 알고리즘을 사용하여 구간합을 구하는 경우 O(1)의 성능을 갖는다.
    구간 합 알고리즘은 현재 진행단계까지의 인덱스까지 값의 합을 저장하는 sum[]배열을 사용한다.
    j번째 바로 앞까지의 총합에 arr[j] 값을 더하면 j번째까지의 총합을 의미하므로 sum[j] = sum[j-1] + arr[j] 로 표현할 수 있다.
 */
public class problem11660 {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken())+1;
        int M=Integer.parseInt(st.nextToken());
        int[][] arr=new int[N][N];
        for (int i = 1; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 1; j < N; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 2; i < N; i++) {
            for (int j = 1; j < N; j++) {
                arr[i][j]=arr[i-1][j]+arr[i][j];
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 2; j < N; j++) {
                arr[i][j]=arr[i][j-1]+arr[i][j];
            }
        }
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < M; i++) {

            st=new StringTokenizer(br.readLine());
            int startX=Integer.parseInt(st.nextToken());
            int startY=Integer.parseInt(st.nextToken());
            int endX=Integer.parseInt(st.nextToken());
            int endY=Integer.parseInt(st.nextToken());
            int result=arr[startX-1][startY-1]+arr[endX][endY]-arr[endX][startY-1]-arr[startX-1][endY];
            sb.append(result);
            sb.append("\n");
        }
        System.out.println(sb.toString());


    }
}
