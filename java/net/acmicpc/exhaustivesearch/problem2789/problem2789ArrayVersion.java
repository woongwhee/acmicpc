//블랙잭

package net.acmicpc.exhaustivesearch.problem2789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2789ArrayVersion {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());


        int[] arr=new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }


        int answer=0;
        for (int i = 0; i < arr.length-2; i++) {
            for (int j = 1+i; j < arr.length-1; j++) {
                for (int k = 1+j; k < arr.length; k++) {
                    int sum=arr[i]+arr[j]+arr[k];
                    if(M>=sum){
                        if(M==sum){
                            System.out.println(answer);return;
                        }else if(sum>answer){
                            answer=sum;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
