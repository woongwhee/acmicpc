package net.acmicpc.programmers;

public class lessons42628 {
    class Solution {
        class Queue{
            private Node head;
            //size가 1인경우 tail은 null
            private Node tail;
            private int size;
            public Queue(){
                this.size=0;
            }
            public void add(int value){
                Node newNode = new Node(value);
                size++;
                if (head == null) { // 큐가 비어 있는 경우
                    head = newNode;
                    tail = newNode;
                } else if (value <= head.value) {
                    // 새로운 노드가 가장 작을 때
                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                } else if (value >= tail.value) {
                    // 새로운 노드가 가장 클 때
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                } else {
                    // 중간에 삽입
                    Node current = head;
                    while (current != null && current.value < value) {
                        current = current.next;
                    }
                    newNode.next = current;
                    newNode.prev = current.prev;
                    if (current.prev != null) {
                        current.prev.next = newNode;
                    }
                    current.prev = newNode;
                }

            }
            public int getSize(){
                return this.size;
            }
            public int poll(){
                if (size == 0) {
                    return 0; // 비어 있는 경우
                }
                int value = head.value;
                if (size == 1) {
                    head = null;
                    tail = null;
                } else {
                    head = head.next;
                    head.prev = null;
                }
                size--;
                return value;
            }
            public int getTail(){
                if (size == 0) {
                    return 0; // 비어 있는 경우
                }
                int value = tail.value;
                if (size == 1) {
                    head = null;
                    tail = null;
                } else {
                    tail = tail.prev;
                    tail.next = null;
                }
                size--;
                return value;
            }
        }
        class Node{
            Node next;
            Node prev;
            int value;
            public Node(int value){
                this.value=value;
            }
            public int compareTo(Node n){
                return Integer.compare(this.value,n.value);
            };
        }
        private Queue queue;
        public void doOper(String operation){
            String[] split=operation.split(" ");
            if(split[0].equals("I")){
                int value=Integer.parseInt(split[1]);
                queue.add(value);
            }else if(split[0].equals("D")){
                int value=Integer.parseInt(split[1]);
                if(value==1){
                    queue.getTail();
                }else if(value==-1){
                    queue.poll();
                }
            }


        }

        public int[] solution(String[] operations) {
            this.queue=new Queue();
            for(int i=0;i<operations.length;i++){
                doOper(operations[i]);
            }
            if(queue.getSize()==1){
                int[] answer={queue.poll(),queue.poll()};
                return answer;
            }
            int[] answer={queue.getTail(),queue.poll()};
            return answer;
        }
    }

    public static void main(String[] args) {
        String[][] testCase={{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}, { "I 10", "I 20", "D 1", "I 30", "I 40", "D -1", "D -1" }};
        int[][] resultSet={{0,0},{333,-45},{40,40}};
        for (int i = 0; i < testCase.length; i++) {
            Solution s=new lessons42628().new Solution();
            int[] solution = s.solution(testCase[i]);
            System.out.println("result : "+solution[0]+","+solution[1]+" expected : "+resultSet[i][0]+","+resultSet[i][1]+" "+(solution[0]==resultSet[i][0]&&solution[1]==resultSet[i][1]));
        }




    }
}
