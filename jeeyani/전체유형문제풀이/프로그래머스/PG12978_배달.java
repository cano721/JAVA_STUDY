package 전체유형문제풀이.프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class PG12978_배달 {

	public static void main(String[] args) {
		int N = 5;
		int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
		int K = 3;
		int result = solution(N,road, K);
		System.out.println(result);

	}

	private static int solution(int N, int[][] road, int K) {
		int answer = 0;

        ArrayList<nodeDelivery>[] list = new ArrayList[N+1];
        int[] dist = new int[N+1];
        
        for(int i = 1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0; //시작마을은 1번 마을은 초기값 0으로 설정

        
        //마을의 도로 정보 초기화
        for(int i = 0; i<road.length; i++){
            int[] temp = new int[3];
            for(int j = 0; j<road[i].length; j++){
                temp[j] = road[i][j];
            }
        
            list[temp[0]].add(new nodeDelivery(temp[1],temp[2]));
            list[temp[1]].add(new nodeDelivery(temp[0],temp[2]));
        }
        
        answer = getCntTown(N,K,dist,list);
        
        return answer;
	}

	 public static int getCntTown(int N, int K,int[] dist, ArrayList<nodeDelivery>[] list){
	        
	        PriorityQueue<nodeDelivery> q = new PriorityQueue<nodeDelivery>();
	        q.add(new nodeDelivery(1,0));
	        
	        while(!q.isEmpty()){
	            
	            nodeDelivery node = q.poll();
	            int idx = node.idx;
	            int cost = node.cost;
	            
	            for(int i = 0; i<list[idx].size(); i++){
	                int nextIdx = list[idx].get(i).idx;
	                int newCost = list[idx].get(i).cost + cost;
	                
	                if(newCost < dist[nextIdx]){
	                    dist[nextIdx] = newCost;
	                    q.add(new nodeDelivery(nextIdx, newCost));
	                }
	            }
	        }
	        
	        int cnt = 0;
	        for(int i = 1; i<=N; i++){
	            if(dist[i] <= K) cnt++;
	        }
	        
	        return cnt;
	    }
	    
	    public static class nodeDelivery implements Comparable<nodeDelivery>{
	        int idx;
	        int cost;
	        
	        public nodeDelivery(int idx, int cost){
	            this.idx = idx;
	            this.cost = cost;
	        }
	        
	        @Override
	        public int compareTo(nodeDelivery o){
	            return cost - o.cost;
	        }
	    }

}
