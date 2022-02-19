/**
    1. 큐를 이용한 풀이
    
    2. 현재 다리에 담긴 무게 확인 변수
    
    3. 트럭 무게 배열을 돌면서 해당 무게를 넣을 수 있으면 넣기
    
    4. 못 넣는 상황일때, 다리가 꽉 차서 못 넣는거면 트럭을 빼기
    
    5. 못 넣는 상황일때, 무게제한으로 인해 못넣는거면 빈차를 넣어서 앞의 트럭이 지나가야 넣을 수 있게 설정
    
    6. 트럭이나 빈 트럭이 올라갈때마다 시간을 증가시키고, 다리를 건너는 최종 시간을 더해주면 답
    
    
    주의사항
    1. 1초에 한대씩 밖에 못올라옴..
    
    2. 다리의 길이만큼 건너는 시간이 걸림
    
    3. 다리의 길이만큼 트럭이 올라갈 수 있음
    
    
**/

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        int time = bridge_length;
        int cur_weight = 0;
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int v: truck_weights){
            
            while(true){
                if(q.size() < bridge_length && cur_weight + v <= weight){
                    q.offer(v);
                    cur_weight += v;
                    time++;
                    break;
                }else if(q.size() == bridge_length){
                    cur_weight -= q.poll();
                }else{
                    q.offer(0);
                    time++;
                }
            }
        }
        return time;
    }
}