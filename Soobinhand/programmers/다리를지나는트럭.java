package Soobinhand.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int cnt = 0;
        int nowBridge = 0;
        Queue<Integer> bridge = new LinkedList<>();
        while (true){

            if (cnt == truck_weights.length){
                break;
            }

            if (bridge.size() == bridge_length){
                nowBridge -= bridge.poll();
            }
            else if (nowBridge + truck_weights[cnt] > weight){
                bridge.add(0);
                answer++;
            }else{
                bridge.add(truck_weights[cnt]);
                nowBridge += truck_weights[cnt];
                answer++;
                cnt++;
            }
        }
        return answer + bridge_length;
    }
}