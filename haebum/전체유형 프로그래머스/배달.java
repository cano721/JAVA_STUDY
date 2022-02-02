/**
    1. 다익스트라 알고리즘
    
**/

import java.util.*;

class Solution {

    
    public ArrayList<Node>[] graph;
    public int[] distance;
    public int answer;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        graph = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        
        distance = new int[N+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        
        for(int[] r : road){
            int start = r[0];
            int end = r[1];
            int cost = r[2];
            
            graph[start].add(new Node(end,cost));
            graph[end].add(new Node(start,cost));
        }
        
        dijkstra();
        for(int i = 1; i <= N; i++){
            if(distance[i] <= K){
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dijkstra(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1,0));
        distance[1] = 0;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            for(int i = 0; i < graph[cur.idx].size(); i++){
                Node next = graph[cur.idx].get(i);
                if(distance[cur.idx] + next.cost < distance[next.idx]){
                    distance[next.idx] = distance[cur.idx] + next.cost;
                    q.offer(new Node(next.idx,distance[next.idx]));
                }
            }
        }
    }
    
    public class Node{
        int idx;
        int cost;
        
        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }
}