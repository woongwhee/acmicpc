        package net.acmicpc.PrefixSum;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.StringTokenizer;

        public class problem11660 {

            public static void main(String[] args) throws IOException {
                BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer st=new StringTokenizer(br.readLine());
                int N=Integer.parseInt(st.nextToken());
                int M=Integer.parseInt(st.nextToken());
                int[][] arr=new int[N][N];
                for (int i = 0; i < N; i++) {
                    st=new StringTokenizer(br.readLine());
                    for (int j = 0; j < N; j++) {
                        arr[i][j]=Integer.parseInt(st.nextToken());
                    }
                }
                int startX[]=new int[M];
                int startY[]=new int[M];
                int endX[]=new int[M];
                int endY[]=new int[M];
                StringBuffer sb=new StringBuffer();
                for (int i = 0; i < M; i++) {
                    st=new StringTokenizer(br.readLine());
                    startX[i]=Integer.parseInt(st.nextToken())-1;
                    startY[i]=Integer.parseInt(st.nextToken())-1;
                    endX[i]=Integer.parseInt(st.nextToken())-1;
                    endY[i]=Integer.parseInt(st.nextToken())-1;

                }

                int result=0;
                for (int i = 0; i < M; i++) {
                    for (int j = startX[i]; j <= endX[i]; j++) {
                        for (int k=startY[i]; k <= endY[i]; k++) {
                            result+=arr[j][k];
                        }
                    }
                    sb.append(result);
                    sb.append("\n");
                }

                System.out.println(sb.toString());


            }
        }
