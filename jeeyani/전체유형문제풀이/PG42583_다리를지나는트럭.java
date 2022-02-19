package 전체유형문제풀이;

import java.util.*;


public class PG42583_다리를지나는트럭 {

	public static void main(String[] args) {

		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7,4,5,6};

		int result = solution(bridge_length, weight, truck_weights);

		System.out.println(result);

	}


	private static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
        Queue<Integer> q =  new LinkedList<Integer>();
        
		int sum =0;
		
		for(int w : truck_weights) {
			
			while(true) {
				//1. 다리에 아무 트럭도 안지나갈때
				if(q.isEmpty()) {
					q.add(w);
					sum +=w;
					answer++;
					break;
					
				//2. 트럭이 모두 다리 위에 있는 경우(=가장 먼저 진입한 트럭이 다리의 끝자락에 도착)	
				//다리는 지난간 트럭의 무게만큼 무게의 합 빼주기
				}else if(q.size() == bridge_length) {
					sum -=q.poll(); 
					
				//3. 트럭이 다리위에 있지만 q가 가득차 있지 않은 상태	
				}else {
					if(sum+w > weight) { //무게의 합이 초과되면 0을 대입
						answer++;
						q.add(0);
					}else {
						q.add(w);
						sum +=w;
						answer++;
						break;
					}
				}
			}
			
		}

        return answer+bridge_length; //모든 트럭이 다리를 모두 지나야 하기때문에 더해준다.
	}

}