package net.acmicpc.programmers;
import java.util.*;

public class lessons42892 {
    class Solution {
        class Tree{
            Node head;
            int size;
            public Tree(Node head){
                this.head=head;
                this.size=1;
            }
            public void add(Node node){
                add(node,head);
                size++;
            }
            private void add(Node newNode,Node cur){
                //모든 노드는 서로 다른 x값을 가진다.
                if(cur.value<newNode.value){
                    if(cur.leftChild==null){
                        cur.leftChild=newNode;
                    }else{
                        add(newNode,cur.leftChild);
                    }
                }else{
                    if(cur.rightChild==null){
                        cur.rightChild=newNode;
                    }else{
                        add(newNode,cur.rightChild);
                    }
                }
            }
            public int[] printTreeFront(){
                List<Integer> visitList=new ArrayList<>(size);
                visiteFront(head,visitList);
                int[] result=new int[size];
                for(int i=0;i<size;i++){
                    result[i]=visitList.get(i);
                }
                return result;
            }
            public int[] printTreeRear(){
                List<Integer> visitList=new ArrayList<>(size);
                visiteRear(head,visitList);
                int[] result=new int[size];
                for(int i=0;i<size;i++){
                    result[i]=visitList.get(i);
                }
                return result;
            }
            private void visiteFront(Node cur,List<Integer> result){
                result.add(cur.key);
                if(cur.leftChild!=null){
                    visiteFront(cur.leftChild,result);
                }
                if(cur.rightChild!=null){
                    visiteFront(cur.rightChild,result);
                }
            }
            private void visiteRear(Node cur,List<Integer> result){
                if(cur.leftChild!=null){
                    visiteRear(cur.leftChild,result);
                }
                if(cur.rightChild!=null){
                    visiteRear(cur.rightChild,result);
                }
                result.add(cur.key);
            }

        }
        class Node{
            int value;
            int key;
            public Node(int key,int value){
                this.key=key;
                this.value=value;
            }
            Node parent;
            Node leftChild;
            Node rightChild;
        }
        public int[][] solution(int[][] nodeinfo) {
            //레이어를 샌다, 레이어순으로 트리에 집어넣는다 treeSet으로 만든다?
            //추가시 트리의 재구성을 안한다.
            TreeMap<Integer,List<Node>>levelMap=new TreeMap<>(Comparator.reverseOrder());
            for(int i=0;i<nodeinfo.length;i++){
                levelMap.putIfAbsent(nodeinfo[i][1], new ArrayList<>());
                Node cur=new Node(i+1,nodeinfo[i][0]);
                levelMap.get(nodeinfo[i][1]).add(cur);
            }
            boolean flage=true;
            Tree tree=null;
            for(Map.Entry<Integer,List<Node>> entry:levelMap.entrySet()){
                if(flage){
                    tree=new Tree(entry.getValue().get(0));
                    flage=false;
                    continue;
                }
                List<Node> nodeList=entry.getValue();
                for(Node node:nodeList){
                    tree.add(node);
                }

            }
            int[][] answer = new int[2][];
            answer[0]=tree.printTreeFront();
            answer[1]=tree.printTreeRear();
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution s = new lessons42892().new Solution();
        int[][] result = s.solution(new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}});
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + ",");
            }
            System.out.println();
        }
    }
}
