package net.acmicpc.exhaustivesearch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem10891clear {
        public static void main(String[] args) throws IOException {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            int length=Integer.parseInt(br.readLine());
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            Data[] DataArr=new Data[length];
            for (int i = 0; i < length; i++) {
                DataArr[i]=new Data(Integer.parseInt(st.nextToken()));
            }
            int max=searchBest(DataArr);

            System.out.println(max);
        }

        static int searchBest(Data[] DataArr){
            int max=0;
            int deep=DataArr.length;
            for(int i=0;i<DataArr.length;i++){
                int result=searchBest(DataArr,new node(DataArr[i],null),deep-1);
                if(max<result){
                    max=result;
                };
            }
            return max;
        }
        static int searchBest(Data[] DataArr,node parent,int deep){
            if(deep==0){
                int result=0;
                node current=parent;
                do{
                    result+=Math.abs(current.data.value-current.parent.data.value);

                    current=current.parent;
                }while (current.parent!=null);

                return result;
            }
            int max=0;
            loop:for (int i = 0; i < DataArr.length; i++) {
                Data data = DataArr[i];
                node current = parent;
                do {
                    if (current.data == data) {
                        continue loop;
                    }
                    current = current.parent;
                }while (current!=null);
                int result = searchBest(DataArr, new node(data, parent), deep - 1);
                if (result > max) {
                    max = result;

                }
            }
            return max;
        }
    }
    class node{
        Data data;
        node parent;
        node(Data data,node parent){
            this.parent=parent;
            this.data=data;
        }
    }
    class Data{
        int value;
        Data(int value){
            this.value=value;
        }
    }



