package net.acmicpc.programmers;

import java.util.*;
public class lessons150364 {
        class Solution{
            static class Tree{
                Node root;
                int leafCount;
                int[] leafIdx;
                Tree(int[][] edges,int n){
                    makeTree(edges,n);
                }
                private void makeTree(int[][] edges,int n){
                    Node[] nodes=new Node[n+1];
                    for(int i=1;i<=n;i++){
                        nodes[i]=new Node(i);
                    }
                    for(int[]edge:edges){
                        nodes[edge[0]].children.add(nodes[edge[1]]);
                    }
                    for(int i=1;i<=n;i++){
                        nodes[i].sortChild();
                    }
                    //leaf처리
                    this.leafCount=0;
                    this.leafIdx=new int[nodes.length];
                    for(int i=1;i<=n;i++){
                        if(nodes[i].children.isEmpty()){
                            leafCount++;
                            leafIdx[i]=leafCount;
                        }
                    }
                    this.root=nodes[1];
                }
                public int[] getNewTarget(int[] targets){
                    int[] newTarget=new int[this.leafCount];
                    for(int i=0;i<targets.length;i++){
                        if(targets[i]>0){
                            newTarget[leafIdx[i+1]-1]=targets[i];
                        }
                    }
                    return newTarget;
                }
                public List<Integer> findRoute(int targetSum){
                    List<Integer> route=new ArrayList<>(targetSum);
                    for(int i=0;i< targetSum;i++){
                        int n=this.root.drop();
                        route.add(leafIdx[n]-1);
                    }
                    return route;
                }
            }
            static class Node{
                int idx;
                List<Node> children;
                int target;
                Node(int idx){
                    this.children=new ArrayList<>();
                    this.target=0;
                    this.idx=idx;
                }
                public void sortChild(){
                    Collections.sort(children,(e1,e2)->e1.idx-e2.idx);
                }
                public int drop(){
                    //leaf
                    if(children.size()==0){
                        return idx;
                    }
                    int result=children.get(target).drop();
                    this.target=target<children.size()-1?target+1:0;
                    return result;
                }
            }
            public int[] dynamicProg(List<Integer> route,int[] target,int targetSum){
                int[] vCount=new int[target.length];
                BitSet bitSet=new BitSet(target.length);
                BitSet clear=new BitSet(target.length);
                clear.set(0,target.length);
                int idx=0;
                //최소 횟수 찾기
                for(int i=0;i<target.length;i++){
                    if(target[i]==0){
                        bitSet.set(i);
                    }
                }
                while (idx<targetSum){
                    int cur=route.get(idx);
                    vCount[cur]++;
                    int maxPos = vCount[cur] * 3;
                    int minPos=vCount[cur] * 1;
                    if(maxPos >=target[cur]){
                        bitSet.set(cur);
                    }
                    if(minPos>target[cur]){
                        break;
                    }
                    idx++;
                    if(bitSet.equals(clear)){
                        break;
                    }
                }
                if(!bitSet.equals(clear)){
                    return new int[]{-1};
                }
                //find sequence
                Map<Integer,int[]> seqMap=new HashMap<>();
                int[][] eachSequence=new int[target.length][];
                for(int i=0;i<target.length;i++){
                    int t=target[i]*101+vCount[i];
                    if(target[i]==0){
                        continue;
                    }
                    if(seqMap.containsKey(t)){
                        eachSequence[i]=seqMap.get(t);
                    }else{
                        int[] sequence=findSequence(vCount[i],target[i]);
                        seqMap.put(t,sequence);
                        eachSequence[i]=sequence;
                    }
                }
                List<Integer> sequence=new ArrayList<>(idx+1);
                int[] eachVisited=new int[target.length];
                for(int i=0;i<idx;i++){
                    int leaf = route.get(i);
                    sequence.add(eachSequence[leaf][eachVisited[leaf]]);
                    eachVisited[leaf]++;
                }
                return sequence.stream().mapToInt(Integer::intValue).toArray();
            }
            private int[] findSequence(int n,int m){
                int[] seq = new int[n];
                boolean pos=seqDfs(n,m,0, seq);
                if(!pos){
                    return new int[]{-1};
                }
                return seq;
            }
            private boolean seqDfs(int n,int remain,int depth,int[] seq){
                if(remain==0){
                    return true;
                }
                int minPossible = n - depth;  // 모두 1을 선택할 때
                int maxPossible = (n - depth) * 3;  // 모두 3을 선택할 때
                if (remain < minPossible || remain > maxPossible) {
                    return false;
                }
                for (int i=1;i<=3;i++){
                    seq[depth]=i;
                    if(seqDfs(n,remain-i,depth+1,seq)){
                        return true;
                    }
                    seq[depth]=0;
                }
                return false;
            }
            public int[] solution(int[][] edges, int[] target) {
                Tree t=new Tree(edges,target.length);
                int targetSum=0;
                for(int i=0;i<target.length;i++){
                    targetSum+=target[i];
                }
                target=t.getNewTarget(target);
                List<Integer>route=t.findRoute(targetSum);
                int[] answer=dynamicProg(route,target,targetSum);
                return answer;
            }
        }
    public static void main(String[] args) {
        Solution sol=new lessons150364().new Solution();
        int[][][]edges={{{2,4},{1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}},{{2,4},{1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}},{{1,2},{1,3}},{{1,3},{1,2}}};
        int[][]target={{0, 0, 0, 3, 0, 0, 5, 1, 2, 3},{0, 0, 0, 3, 0, 0, 3, 1, 2, 0},{0, 7, 3},{0, 7, 1}};
        int[][]result={{1, 1, 2, 2, 2, 3, 3},{3,3,1,2},{1, 1, 3, 2, 3},{-1}};
        for(int i=0;i<edges.length;i++){
            System.out.println(Arrays.equals(sol.solution(edges[i],target[i]),result[i]));
        }
    }
}
