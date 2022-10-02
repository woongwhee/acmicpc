    package net.acmicpc.exhaustivesearch;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st=new StringTokenizer(br.readLine());
            int bread=Integer.parseInt(st.nextToken());
            int meat=Integer.parseInt(st.nextToken());
            int hambuger=0;
            hambuger=bread/2;
            if(meat<hambuger){
                hambuger=meat;
            }
            System.out.println(hambuger);
        }
    }
