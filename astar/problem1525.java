package net.acmicpc.astar;

import net.acmicpc.bfs.problem1525meoryout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem1525 {
    static boolean isSolved(int[] puzzle) {
        int count=0;
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (puzzle[i] > 0 && puzzle[j] > 0 && puzzle[i] > puzzle[j]) {
                    count++;
                }
            }
        }
        return count % 2 == 0 ? true : false;
    }
    static boolean isClear(Node node) {
        if (node.puzzle[8] != 0) {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            if (node.puzzle[i] != i+1) {
                return false;
            }
        }
        return true;
    }

    private static int solution(int blank, int[] puzzle) {
        Node start=new Node(puzzle,blank,-1,0);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            if(isClear(cur)){
                return cur.deep;
            }
            int newBlank=cur.blank+3;
            if(cur.parentBlank!=newBlank&&newBlank<=8){
                int[] newArr= Arrays.copyOf(cur.puzzle,9);
                swap(newBlank, cur.blank,newArr);
                queue.add(new Node(newArr,newBlank, cur.blank, cur.deep+1));
            }
            newBlank=cur.blank-3;
            if(cur.parentBlank!=newBlank&&newBlank>=0){
                int[] newArr= Arrays.copyOf(cur.puzzle,9);
                swap(newBlank, cur.blank,newArr);
                queue.add(new Node(newArr,newBlank, cur.blank, cur.deep+1));
            }
            newBlank=cur.blank-1;
            if(cur.parentBlank!=newBlank&&newBlank>=0&&newBlank%3!=2){
                int[] newArr= Arrays.copyOf(cur.puzzle,9);
                swap(newBlank, cur.blank,newArr);
                queue.add(new Node(newArr,newBlank, cur.blank, cur.deep+1));
            }
            newBlank=cur.blank+1;
            if(cur.parentBlank!=newBlank&&newBlank<=8&&newBlank%3!=0){
                int[] newArr= Arrays.copyOf(cur.puzzle,9);
                swap(newBlank, cur.blank,newArr);
                queue.add(new Node(newArr,newBlank, cur.blank, cur.deep+1));
            }
        }
        return -1;
    }
    public static void swap(int x,int y,int[] arr){
        int temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] puzzle = new int[9];
        int blank=0;

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                puzzle[j+3*i] = Integer.parseInt(st.nextToken());
                if (puzzle[j+3*i] == 0) {
                    blank=j+3*i;
                }
            }
        }
        if (!isSolved(puzzle)) {
            System.out.println("-1");
            return;
        }
        int result=solution(blank,puzzle);
        System.out.println(result);
    }

    static class Node implements Comparable<Node>{
        int[] puzzle;
        int deep;
        int blank;
        int parentBlank;
        int value;
        public Node(int[] puzzle, int blank, int parentBlank, int deep) {
            this.puzzle = puzzle;
            this.deep = deep;
            this.blank = blank;
            this.parentBlank = parentBlank;
            this.value=deep;
            for(int i=0;i<8;i++){
                if(this.puzzle[i]!=i+1){
                    this.value++;
                }
            }
            if(puzzle[8]!=0){
                this.value++;
            }
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value,o.value);
        }
    }
}
