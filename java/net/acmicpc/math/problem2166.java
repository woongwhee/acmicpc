package net.acmicpc.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2166 {
    static class Loc {
        double x;
        double y;
        Loc(double x, double y) {
            this.x = x;
            this.y = y;
        }
        double dot(Loc a){
            return this.x*a.x+this.y*a.y;
        }
        Loc minus(Loc a){
            return new Loc(this.x-a.x,this.y-a.y);
        }
        double cross(Loc a){
            return this.x*a.y-this.y*a.x;
        }
        public static double exProduct(Loc stand, Loc pa, Loc pb){
            return pa.minus(stand).cross(pb.minus(stand));
        }

    }
    private static double getArea(Loc[] locs){
        double result=0;
        Loc stand=locs[0];
        for(int i=1;i<locs.length-1;i++){
            result+=Loc.exProduct(stand,locs[i],locs[i+1])/2.0;
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        Loc[] locs=new Loc[n];
        for(int i=0;i<n;i++){
            String[] input=br.readLine().split(" ");
            locs[i]=new Loc(Double.parseDouble(input[0]),Double.parseDouble(input[1]));
        }
        double result=Math.abs(getArea(locs));
        System.out.printf("%.1f",result);
    }

}
