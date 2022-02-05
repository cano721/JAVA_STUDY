import java.util.*;
import java.io.*;
class Solution {
    static class Edge{
        int to, weight;
        public Edge(int t, int w){
            to = t;
            weight = w;
        }
    }
    
    static ArrayList<Edge>[] list;
    static int[] vis;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        list = new ArrayList[N+1];
        vis = new int[N+1];
        for(int i = 1; i<N+1; i++) {
            list[i] = new ArrayList<>();
            vis[i] = Integer.MAX_VALUE;   
        }
        
        
        for(int i =0; i<road.length; i++){
            int from = road[i][0];
            int to = road[i][1];
            int weight = road[i][2];
            list[from].add(new Edge(to,weight));
            list[to].add(new Edge(from, weight));
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>( (o1,o2) -> o1.weight- o2.weight);
        pq.add(new Edge(1, 0));
        vis[1] = 0;
        while(!pq.isEmpty()){
            
            Edge now = pq.poll();
            if(vis[now.to] < now.weight) continue;
            for(Edge edge : list[now.to]){
                if(edge.weight + vis[now.to] >= vis[edge.to]) continue;
                vis[edge.to] = edge.weight+vis[now.to];
                pq.add(new Edge(edge.to, vis[edge.to]));
            }
        }
        
        for(int i = 1; i<N+1; i++){
            
            if(vis[i] > K) continue;
            //System.out.print(vis[i] + " ");
            answer++;
            
        }
        
        return answer;
    }
}