package net.acmicpc.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem1756 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int d= Integer.parseInt(st.nextToken());
        int n= Integer.parseInt(st.nextToken());
        int oven[]=new int[d+2];
        int pizzas[]=new int[n];
        st=new StringTokenizer(br.readLine());
        for (int i = 1; i <= d; i++) {
            oven[i]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pizzas[i]=Integer.parseInt(st.nextToken());
        }
        int result=solution( oven, pizzas);
        System.out.println(result);
    }

    private static int solution(int[] oven, int[] pizzas) {
        int holeSize=Integer.MAX_VALUE;
        ArrayList<Pair> ovenPair=new ArrayList<>(oven.length);
        for (int i = 1; i < oven.length; i++) {
            if(oven[i]<holeSize){
                ovenPair.add(new Pair(oven[i],i));
                holeSize=oven[i];
            }
        }
        int deep=oven.length-1;
        int pairPoint=ovenPair.size()-1;
        int thin=1;
        for (int pizza : pizzas) {
            for(int i=pairPoint;i>=0;i--){
                if(ovenPair.get(i).deep<deep&&ovenPair.get(i).diameter>=pizza){
                    if(pairPoint==i){
                        thin++;
                    }else{
                        thin=1;
                    }
                    pairPoint=i;
                    deep=ovenPair.get(i+1).deep-thin;
                    break;
                }
                if(i==0){
                    return 0;
                }
            }
        }
        return deep;
    }
    static class Pair{
        int diameter;
        int deep;
        public Pair(int diameter,int deep) {
            this.diameter = diameter;
            this.deep = deep;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Pair{");
            sb.append("diameter=").append(diameter);
            sb.append(", deep=").append(deep);
            sb.append('}');
            return sb.toString();
        }
    }
}
