/**
    다익스트라 또는 플로이드 워셜

    1. 다익스트라로 지점에서 다른 지점으로 가는 최소 비용 구하기
    (Vlog(V+E))
    
    2. 지점이 바뀔때마다, 시작지점에서 해당지점까지 + 해당지점에서 각 A,B의 도착지점까지의 비용 더한게 가장 적은지 비교
    
    최종 시간복잡도는 V+Vlog(V+E)

    
    플로이드 워셜

    1. 플로이드 워셜로 전체 지점에서 다른 지점으로 가는 최소 비용 구하기

    2. 지점이 바뀔때마다, 시작지점에서 해당지점까지 + 해당지점에서 각 A,B의 도착지점까지의 비용 더한게 가장 적은지 비교

    시간복잡도 v^3
**/

// 다익스트라 풀이
// import java.util.*;

// class Solution1 {
    
//     public PriorityQueue<Taxi> q;
    
//     public class Taxi{
//         int idx;
//         int cost;
//         public Taxi(int idx, int cost){
//             this.idx = idx;
//             this.cost = cost;
//         }
//     }
    
//     public ArrayList<Taxi>[] graphs;
    
//     public int maxInt = 100_000*200;
    
//     public int solution(int n, int s, int a, int b, int[][] fares) {
        
//         int answer = maxInt*2;
        
//         // 간선 생성 및 삽입
//         graphs = new ArrayList[n+1];
//         for(int i = 0; i <= n; i++){
//             graphs[i] = new ArrayList<>();
//         }
        
//         for(int[] fare: fares){
//             int start = fare[0];
//             int end = fare[1];
//             int cost = fare[2];
            
//             graphs[start].add(new Taxi(end,cost));
//             graphs[end].add(new Taxi(start,cost));
//         }
        
//         int[] costS = dijkstra(s);
//         int[] costA = dijkstra(a);
//         int[] costB = dijkstra(b);
        
//         // 지점별 비용 계산
//         for(int i = 1; i <= n; i++){
//             int cost = costS[i] + costA[i] + costB[i];
//             answer = Math.min(answer,cost);
//         }
//         return answer;
//     }
    
//     public int[] dijkstra(int start){
        
//         int[] distance = new int[graphs.length];
//         Arrays.fill(distance,maxInt);
//         q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        
//         distance[start] = 0;
//         q.offer(new Taxi(start,0));
        
//         while(!q.isEmpty()){
//             Taxi cur = q.poll();
            
//             if(distance[cur.idx] < cur.cost){
//                 continue;
//             }
            
//             for(int i = 0; i < graphs[cur.idx].size(); i++){
//                 Taxi next = graphs[cur.idx].get(i);
//                 if(distance[next.idx] > cur.cost + next.cost){
//                     distance[next.idx] = cur.cost + next.cost;
//                     q.offer(new Taxi(next.idx,distance[next.idx]));
//                 }
//             }
//         }
        
//         return distance;
//     }
    
// }


// 플로이드 워셜
import java.util.*;

class Solution {
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        int maxInt = 100_001*200;
        
        int[][] costs = new int[n+1][n+1];
        
        for(int i = 0; i <= n; i++){
            Arrays.fill(costs[i],maxInt);
            costs[i][i] = 0;
        }
        
        for(int[] fare : fares){
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];
            
            costs[start][end] = cost;
            costs[end][start] = cost;
        }
        
        for(int mid = 1; mid <= n; mid++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    costs[i][j] = Math.min(costs[i][j], costs[i][mid] + costs[mid][j]);
                }
            }
        }
        answer = maxInt;
        
        for(int i = 1; i <=n; i++){
            answer = Math.min(answer, costs[s][i] + costs[i][a] + costs[i][b]);
        }
        
        return answer;
    }
}