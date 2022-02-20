package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class d220221_다리를지나는트럭 {

	public static void main(String[] args) {
		int bridge_length=2;
		int weight=10;
		int[] truck_weights = {7,4,5,6};
		int answer = solution(bridge_length,weight,truck_weights);
		System.out.println(answer);
	}

	private static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
        int sumOnBridge = 0;
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i = 0; i < truck_weights.length; i++) {
        	while(true) {
				// 큐에 아무것도 없는 경우 = 어떠한 트럭도 다리위에 없음 
				if(queue.isEmpty()) {
					queue.add(truck_weights[i]);
					sumOnBridge += truck_weights[i];
					answer++; // 다리에 오를 때만 시간 추가 
					break;
				} else if(queue.size() == bridge_length) { // 큐에 다리 길이만큼 트럭이 다 찬 경우 
					sumOnBridge -= queue.poll();
				} else  { 
					if(sumOnBridge + truck_weights[i] <= weight) { //다리 허용 무게를 만족할 때
						queue.add(truck_weights[i]);
						sumOnBridge += truck_weights[i];
						answer++;
						break;
					} else { //다리 허용 무게를 초과할 때 
						queue.add(0);
						answer++;
					}
				}
			}
        }
        return answer + bridge_length;	//마지막 트럭이 지나가야하므로 다리길이 더해주기
	}
}
