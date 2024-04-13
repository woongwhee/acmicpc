package net.acmicpc.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class problem1991 {

    static class Node {
        private Node leftChild;
        private Node rightChild;
        private Node parent;
        private char name;

        public Node() {
        }

        public Node(char name) {
            this.name=name;
        }
        //getter and setter
        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public Node getParent() {
            return parent;
        }

        public char getName() {
            return name;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }

    static class Tree {
        private Node root;
        private List<Node> nodeList;
        private StringBuffer sb;
        private final static int IN_ORDER=0;
        private final static int POST_ORDER =1;
        private final static int PRE_ORDER=2;

        private Tree() {

        }

        /**트리생성을위한 팩토리얼 메소드
         */
        public static Tree makeTree(int size) {
            Tree tree = new Tree();
            tree.nodeList = new ArrayList<>();
            tree.root = new Node('A');
            tree.nodeList.add(tree.root);
            for (int i = 1; i < size; i++) {
            char name= (char) ('A'+i);
            tree.nodeList.add(new Node(name));
            }
            return tree;
        }

        /**
         *   문자열 정보를 바탕으로 노드의 자식과 부모노드를 연결하는 메서드
         */
        public void addNode(String str) {
            String[] split = str.split(" ");
            Node cur = nodeList.get(split[0].charAt(0) - 'A');
            Node left=null;
            Node right=null;
            if (split[1].charAt(0) != '.') {
                left = nodeList.get(split[1].charAt(0) - 'A');
            }
            if (split[2].charAt(0) != '.') {
                right = nodeList.get(split[2].charAt(0) - 'A');
            }
            cur.setLeftChild(left);
            cur.setRightChild(right);
            if(left!=null)left.setParent(cur);
            if(right!=null)right.setParent(cur);
        }

        /**
         * 지정된 순회 방식으로 트리를 순회하며 결과를 출력하는 메서드.
         * 순회 방식을 지정하기 위해 Tree 클래스에 선언된 상수 (IN_ORDER, POST_ORDER, PRE_ORDER) 중 하나를 전달해야 합니다.
         *
         * @param traverseType Tree의 상수 중 하나 (IN_ORDER, POST_ORDER, PRE_ORDER)
         */
        public void printTraverse(int traverseType) {
            StringBuffer sb = new StringBuffer();

            switch (traverseType) {
                case POST_ORDER:
                    postOrderTraverse(root, sb);
                    break;
                case IN_ORDER:
                    inOrderTraverse(root, sb);
                    break;
                case PRE_ORDER:
                    preOrderTraverse(root, sb);
                    break;
            }

            System.out.println(sb.toString());
        }

        /**
         * 전위 순회 메서드
         * @param node
         * @param sb 값을 저장하기위한 StringBuffer
         */
        private void postOrderTraverse(Node node, StringBuffer sb) {
            sb.append(node.getName());
            if (node.getLeftChild() != null) {
                postOrderTraverse(node.getLeftChild(), sb);
            }
            if (node.getRightChild() != null) {
                postOrderTraverse(node.getRightChild(), sb);
            }
        }

        /**
         * 중위 순회 메서드
         * @param node
         * @param sb 반환을 위한 StringBuffer
         */
        private void inOrderTraverse(Node node, StringBuffer sb) {
            if (node.getLeftChild() != null) {
                inOrderTraverse(node.getLeftChild(), sb);
            }
            sb.append(node.getName());
            if (node.getRightChild() != null) {
                inOrderTraverse(node.getRightChild(), sb);
            }
        }

        /**
         * 전위 순회 메서드
         * @param node
         * @param sb 반환을 위한 StringBuffer
         */
        private void preOrderTraverse(Node node, StringBuffer sb) {
            if (node.getLeftChild() != null) {
                preOrderTraverse(node.getLeftChild(), sb);
            }
            if (node.getRightChild() != null) {
                preOrderTraverse(node.getRightChild(), sb);
            }
            sb.append(node.getName());
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Tree tree = Tree.makeTree(N);
        for (int i = 0; i < N; i++) {
            tree.addNode(br.readLine());
        }
        tree.printTraverse(Tree.POST_ORDER);
        tree.printTraverse(Tree.IN_ORDER);
        tree.printTraverse(Tree.PRE_ORDER);


    }
}
