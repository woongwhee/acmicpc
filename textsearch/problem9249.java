package net.acmicpc.textsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * LCS 최다 공통 부분 문자열  이방법으로 풀면안된다 suffixArray로 풀어야된다.
 */
public class problem9249 {
    static int base=256;
    static int mod=127;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String keyword = br.readLine();
        int result=karpRabin(text,keyword);
        System.out.println(result);


    }

    static int hash(String text) {
        int hashValue = 0;
        for (int i=0;i<text.length();i++) {
            hashValue=(hashValue+text.charAt(i))*2;
        }
        return hashValue;
    }

    static int karpRabin(String text,String key) {
        int n=text.length();
        int m=key.length();
        if(n<m)return 0;
        int hashKey=hash(key);
        int hashText=hash(text.substring(0,m));
        int first=1;
        for (int i = 0; i < m-1; i++) {
            first = (first * base) % mod;
        }
        for (int i = 0; i <= n-m; i++) {
            if(hashKey==hashText) {
                if (text.substring(i, i+m).equals(key)) {
                    return 1;
                }
            }
            if(i+m<n){
                hashText=hashText-(text.charAt(i)*first)%mod;
                hashText=(hashText+mod)%mod;
                hashText=((hashText*base)%mod+text.charAt(i+m))%mod;
            }

        }
        return 0;

    }

}
