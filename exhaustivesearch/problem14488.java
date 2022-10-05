package net.acmicpc.exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem14488 {
    static int[] num;
    static int[] oper;
    static int Min;
    static int Max;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Max = Integer.MIN_VALUE;
        Min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int[] oper_ = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        oper = oper_;
        operating(1,num[0]);
        System.out.println(Max);
        System.out.println(Min);
    }

    private static void operating(int deep,int result) {
        if (deep == N) {

            if (result < Min) {
                Min = result;
            }
            if (result > Max) {
                Max = result;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (oper[i] == 0) {
                continue;
            }
            oper[i]--;

            switch (i){
                case 0 : operating(deep + 1,result+num[deep]);break;
                case 1 : operating(deep + 1,result-num[deep]);break;
                case 2 : operating(deep + 1,result*num[deep]);break;
                case 3 : operating(deep + 1,result/num[deep]);break;
            }
            oper[i]++;
        }

    }
}
