package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem1525meoryout {
    static boolean isSolved(int[][] puzzle) {
        int[] arr = new int[9];
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i * 3 + j] = puzzle[i][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (arr[i] > 0 && arr[j] > 0 && arr[i] > arr[j]) {
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

    private static int solution(int blankX, int blankY, int[][] puzzle) {
        int[] arr=new int[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i*3+j]=puzzle[i][j];
            }
        }
        Node start=new Node(arr,blankX*3+blankY,-1,0);
        Queue<Node> queue = new LinkedList<>();
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
        int[][] puzzle = new int[3][3];
        int blankX=0;
        int blankY=0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                puzzle[i][j] = Integer.parseInt(st.nextToken());
                if (puzzle[i][j] == 0) {
                    blankX = i;
                    blankY = j;
                }
            }
        }
        if (!isSolved(puzzle)) {
            System.out.println("-1");
            return;
        }
        int result=solution(blankX,blankY,puzzle);
        System.out.println(result);
    }

    static class Node {
        int[] puzzle;
        int deep;
        int blank;
        int parentBlank;
        public Node(int[] puzzle, int blank, int parentBlank, int deep) {
            this.puzzle = puzzle;
            this.deep = deep;
            this.blank = blank;
            this.parentBlank = parentBlank;
        }
    }
}
