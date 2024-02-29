package net.acmicpc.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class problem5639 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BinarySearchTree<Integer> bst=new BinarySearchTree<>();
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            bst.add(Integer.parseInt(line));
        }
        String result= bst.printPostOrder();
        System.out.println(result);
    }

    static class BinarySearchTree<T> {

        class TreeNode<T> {
            T value;
            TreeNode left;
            TreeNode right;

            public TreeNode(T value) {
                this.value = value;
            }
        }

        private Comparator<T> comparator;
        private TreeNode<T> root;

        public BinarySearchTree() {
            comparator = null;
        }

        public void add(T value) {
            if (root == null) {
                root = new TreeNode<>(value);
            } else {
                addRecursive(root, value);
            }


        }

        private void addRecursive(TreeNode<T> current, T value) {
            int compare = comparator == null ? ((Comparable<T>) current.value).compareTo(value) : comparator.compare(current.value, value);
            if (compare > 0) {
                if (current.left == null) {
                    current.left = new TreeNode<>(value);
                } else {
                    addRecursive(current.left, value);
                }
            } else if (compare < 0) {
                if (current.right == null) {
                    current.right = new TreeNode<>(value);
                } else {
                    addRecursive(current.right, value);
                }
            }
        }

        public String printPostOrder() {
            StringBuffer sb = new StringBuffer();
            postOrder(sb, root);
            return sb.toString();
        }

        private void postOrder(StringBuffer sb, TreeNode<T> node) {
            if (node.left != null) {
                postOrder(sb, node.left);
            }
            if (node.right != null) {
                postOrder(sb, node.right);
            }
            sb.append(node.value).append("\n");
        }


    }

}
