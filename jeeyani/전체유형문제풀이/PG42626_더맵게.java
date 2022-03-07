package 전체유형문제풀이;

import java.util.PriorityQueue;

/*
 * [우선순위 큐] - 힙
 * 
 * 
 * */

public class PG42626_더맵게 {

	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;

		int result =0;
		result = solution(scoville , K);
	}

	private static int solution(int[] scoville, int K) {
		
		int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<scoville.length; i++){
        	pq.offer(scoville[i]);
        }
        
        while(pq.peek() < K){
        	
            if(pq.size() < 2) return -1;
            
            int f1 = pq.poll();
            int f2 = pq.poll();
            
            //섞은 음식의 스코빌 지수 구하기
            int newFood = f1 + (f2 * 2);
            
            pq.offer(newFood);
            
            answer++;
            
        }
         
        
        return answer;
	}

}
