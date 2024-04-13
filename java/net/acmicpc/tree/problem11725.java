package net.acmicpc.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class problem11725 {
        public static void main(String[] args) throws IOException {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            int n= Integer.parseInt(br.readLine());
            Node[] nodeArray=new Node[n+1];
            for (int i = 0; i < nodeArray.length; i++) {
                nodeArray[i]=new Node(i);
            }
            String line;
            while((line=br.readLine())!=null&&!line.isEmpty()){
                String[] split = line.split(" ");
                int from= Integer.parseInt(split[0]);
                int to= Integer.parseInt(split[1]);
                nodeArray[from].children.add(nodeArray[to]);
                nodeArray[to].children.add(nodeArray[from]);

            }
            Queue<Node> queue=new LinkedList<>();
            queue.add(nodeArray[1]);
            while (!queue.isEmpty()){
                Node cur=queue.poll();
                Node parent=cur.parent;
                for (Node child:cur.children) {
                    if(child!=parent){
                        child.parent=cur;
                        queue.add(child);
                    }
                }
            }
            StringBuffer sb=new StringBuffer();
            for (int i = 2 ; i <=n ; i++) {
                sb.append(nodeArray[i].parent.index).append("\n");
            }
            System.out.println(sb.toString());

        }
        static class Node{
            Node parent;
            int index;
            List<Node> children;
            public Node(int index) {
                this.index=index;
                this.children = new ArrayList<>();
            }
        }



}
