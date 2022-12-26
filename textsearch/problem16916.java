package net.acmicpc.textsearch;

    import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class problem16916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String key = br.readLine();
        String value = br.readLine();
        if(key.length()>value.length()){
            String temp=value;
            value=key;
            key=temp;
        }
        for (int l = key.length(); l > 0; l--) {
            int coefficient = 1;
            for (int i = 1; i < l; i++) {
                coefficient *= 2;
            }
            int valueHash[]=new int[value.length()-l+1];
            valueHash[0]=hash(value,0,l);
            for (int i = 1; i < value.length()-l; i++) {
                valueHash[i]=rehash(value,i,l,valueHash[i-1],coefficient);
            }
            int keyHash[]=new int[key.length()-l+1];
            keyHash[0]=hash(key,0,l);
            for (int i = 1; i <= key.length() - l; i++) {
                keyHash[i]=rehash(key,i,l,keyHash[i-1],coefficient);
            }
            for (int i = 0; i < keyHash.length; i++) {
                for (int j = 0; j < valueHash.length; j++) {
                    if(keyHash[i]==valueHash[j]){
                        System.out.println(l);
                        System.out.println(key.substring(i,i+l));
                        return;
                    }
                }
            }
        }
    }

    static int hash(String text, int start, int length) {
        int hashValue = 0;
        for (int i = start; i < length; i++) {
            hashValue = text.charAt(i) + hashValue * 2;
        }
        return hashValue;
    }

    static int rehash(String text, int start, int length, int hashPrev, int coefficient) {

        return text.charAt(start+length-1) + (hashPrev - text.charAt(start - 1) * coefficient) * 2;

    }

}

