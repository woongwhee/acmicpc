package net.acmicpc.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class problem1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        Set<String> set=new TreeSet<>((Comparator) (o1, o2) -> {
            String s1= (String) o1;
            String s2= (String) o2;
            if(s1.length()!=s2.length()){
                return s1.length()-s2.length();
            }
            int i=0;
            for (int j = 0; j < s1.length(); j++) {
                if(s1.charAt(i)!=s2.charAt(i)){
                    char c1=Character.toLowerCase(s1.charAt(i));
                    char c2=Character.toLowerCase(s2.charAt(i));
                    if(c1==c2){
                        return s1.charAt(i)-s2.charAt(i);
                    }
                    return c1-c2;
                }
            }
            return 0;
        });
        for (int i=0;i<n;i++){
            set.add(br.readLine());
        }
        StringBuffer sb=new StringBuffer();
        for (String s : set) {
            sb.append(s);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
