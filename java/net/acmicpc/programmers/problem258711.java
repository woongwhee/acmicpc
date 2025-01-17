package net.acmicpc.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class problem258711 {
    class Solution {
        public int[] solution(int[][] edges) {
            Graph g=new Graph(edges);
            int[] answer = g.checkGraph();
            return answer;
        }
        class Graph{
            private List<Integer>[] graph;
            private int[] toCount;
            private int size;
            public Graph(int[][] edges){
                size=checkSize(edges);
                graph=new List[size+1];
                toCount=new int[size+1];
                for(int i=0;i<=size;i++){
                    graph[i]=new ArrayList();
                }
                for(int[] edge:edges){
                    graph[edge[0]].add(edge[1]);
                    toCount[edge[1]]++;
                }
            }
            private int checkSize(int[][] edges){
                int max=0;
                for(int[] edge:edges){
                    if(edge[0]>max){
                        max=edge[0];
                    }
                    if(edge[1]>max){
                        max=edge[1];
                    }
                }
                return max;
            }
            private int findCenter(){
                for(int i=1;i<=size;i++){
                    if(toCount[i]==0&&graph[i].size()>1){
                        return i;
                    }
                }
                return 0;
            }
            public int[] checkGraph(){
                int[] result=new int[4];//추가정점 도넛 막대 8
                boolean[] visited=new boolean[size+1];
                int center=findCenter();
                result[0]=center;
                Queue<Integer> queue=new LinkedList();
                firstLoop:for(int start:graph[center]){
                    if(graph[start].size()==2){
                        result[3]++;
                        continue;
                    }
                    queue.addAll(graph[start]);
                    visited[start]=true;
                    while(!queue.isEmpty()){
                        int cur=queue.poll();
                        if(graph[cur].size()==2){
                            result[3]++;
                            queue.clear();
                            continue firstLoop;
                        }

                        for(int v:graph[cur]){
                            if(visited[v]){
                                result[1]++;
                                queue.clear();
                                continue firstLoop;
                            }
                            visited[v]=true;
                            queue.add(v);
                        }
                    }
                    result[2]++;
                }
                return result;
            }
        }
    }}
