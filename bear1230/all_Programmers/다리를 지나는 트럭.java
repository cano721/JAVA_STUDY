import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int sum = 0;
        Queue<Integer> que = new LinkedList<>();

        for(int i = 0; i < bridge_length; i++){
            que.add(0);
        }

        for(int i = 0; i < truck_weights.length;){
            sum -= que.poll();
            
            if(sum + truck_weights[i] <= weight){
                que.add(truck_weights[i]);
                sum += truck_weights[i];
                i++;
            } else {
                que.add(0);
            }
            
            answer++;
        }

        while(!que.isEmpty() && que.peek()!=0){
            que.poll();
            answer++;
        }

        return answer + que.size();
    }
}
