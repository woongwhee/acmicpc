package net.acmicpc.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2263 {
    static class Node{
        int value;
        Node left;
        Node right;
        Node(int value){
            this.value=value;
        }
    }
    public static void main(String[] args) {
        int size=0;
        String inorderStr="";
        String postorderStr="";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
            size=Integer.parseInt(br.readLine());
            inorderStr=br.readLine();
            postorderStr=br.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
        int[] inorder=new int[size];
        int[] postorder=new int[size];
        String[] inorderSplit=inorderStr.split(" ");
        String[] postorderSplit=postorderStr.split(" ");
        for(int i=0;i<size;i++){
            inorder[i]=Integer.parseInt(inorderSplit[i]);
            postorder[i]=Integer.parseInt(postorderSplit[i]);
        }

        Node root=new Node(postorder[size-1]);

    }
}
