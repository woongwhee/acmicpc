package net.acmicpc.exhaustivesearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem14889{
    static int[][] arr;
    static boolean[] visited;
    static int N;
    static int Min=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        arr= new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        visited=new boolean[N];
        visited[0]=true;
        makeTeam(1,1);
        System.out.println(Min);
    }
    static void makeTeam(int deep,int index){
        if(deep==N/2){
            int score=getScore(visited);
            if(score==0){

            }
            if(score<Min){
                Min=score;
            };
        }
        for (int i = index; i < arr.length; i++) {
            if(!visited[i]){
                visited[i]=true;
                makeTeam(deep+1,i+1);
                visited[i]=false;
            }
        }
    }
    static int getScore(boolean[] visited){
        int firstTeam=0;
        int secondTeam=0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if(visited[i] && visited[j]){
                    firstTeam+=arr[i][j];
                    firstTeam+=arr[j][i];

                }else if(!visited[i]&&!visited[j]){
                    secondTeam+=arr[i][j];
                    secondTeam+=arr[j][i];
                }
            }
        }
        return Math.abs(firstTeam-secondTeam);
    }

}
