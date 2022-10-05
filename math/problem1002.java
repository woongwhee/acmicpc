package net.acmicpc.math;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class problem1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in) );
        int testCase=Integer.parseInt(br.readLine());
        int[][] turret=new int[testCase][6];
        StringTokenizer st;
        for (int i = 0; i <testCase; i++) {
            st=new StringTokenizer(br.readLine()) ;
            for (int j = 0; j < turret[i].length; j++) {
                turret[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < testCase; i++) {
            System.out.println(checkTarget(turret[i]));
        }

    }
    public static int checkTarget(int[] turret){
        int result=0;
        int x1=turret[0];
        int y1=turret[1];
        int r1=turret[2];
        int x2=turret[3];
        int y2=turret[4];
        int r2=turret[5];
        double d=Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
        double rp=Math.abs(r1+r2);
        double rm=Math.abs(r1-r2);
        System.out.println("d"+d+"rp"+rp+"rm"+rm);
        if(rp==d||rm==d){
            result=1;
        }else if(rm==0&&d==0){
            result=-1;
        }else if(rm<d&&rp>d){
            result=2;
        }else if(rm>d||rp<d||d==0){
            result=0;
        }

        return result;
    }


}
