package net.acmicpc.exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class problem14888 {
    static int[] num;
    static int[] oper;
    static int Min;
    static int Max;
    static int[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        num=new int[N];
        StringTokenizer st=new StringTokenizer(br.readLine());
        Max=Integer.MIN_VALUE;
        Min=Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            num[i]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        int[] oper_={Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        oper=oper_;
        visited=new int[N-1];
        operating(0);

        System.out.println(Max);
        System.out.println(Min);
    }
    private static void operating(int deep){
        if(deep==N-1){
            int result=calculating();
            if(result<Min){
                Min=result;
            }
            if(result>Max){
                Max=result;
            }
        }
        for (int i = 0; i < 4; i++) {
            if(oper[i]==0){
                continue;
            }
            oper[i]--;
            visited[deep]=i;
            operating(deep+1);
            oper[i]++;
        }//이걸 고치는게 맞나???

    }
    private static int calculating(){
        //알고보니 연산자 우선순위를 무시하는 문제였다..
        List<Integer> list =new ArrayList<Integer>();
        list.add(num[0]);
        for (int i = 0; i < N-1; i++) {
            if(visited[i]<2){//+-경우
                list.add(num[i+1]);
            } else if (visited[i]==2) {//*경
                list.set(list.size()-1, list.get(list.size()-1)*num[i+1]);
            } else if (visited[i]==3) {
                list.set(list.size()-1, list.get(list.size()-1)/num[i+1]);
            }

        }
        Iterator<Integer> it=list.listIterator();
        int result=it.next();
        for (int i = 0; i <visited.length ; i++) {
            if(visited[i]==0){
                result+=it.next();
            } else if (visited[i]==1) {
                result-=it.next();
            }
        }
        System.out.println();
        return result;
    }
}
