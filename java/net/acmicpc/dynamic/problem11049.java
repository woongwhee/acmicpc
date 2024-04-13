    package net.acmicpc.dynamic;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Arrays;
    import java.util.StringTokenizer;

    public class problem11049 {
        public static void main(String[] args) throws IOException {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            int n= Integer.parseInt(br.readLine());

            int arr[]=new int[n+1];
            StringTokenizer st=null;
            for (int i = 0; i < n; i++) {
                st=new StringTokenizer(br.readLine());
                arr[i]= Integer.parseInt(st.nextToken());
            }
                arr[n]= Integer.parseInt(st.nextToken());

            int dp[][]=new int[n][n];
            int p[][]=new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i],Integer.MAX_VALUE);
                dp[i][i]=0;
            }

            for (int j = 1; j <n ; j++) {
                for (int i = 0; i < n-j ; i++) {
                    int a=i;
                    int b=i+j;
                    p[a][b]=a+1;
                    for (int k = a; k <b ; k++) {
                        dp[a][b]=Math.min(dp[a][b],dp[a][k]+dp[k+1][b]+arr[a]*arr[k+1]*arr[b+1]);
                    }
                }
            }
            System.out.println(dp[0][n-1]);
        }
    }
