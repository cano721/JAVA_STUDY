/**
    1. 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 평균 반환
    1-1) 같은 시간에 요청중인 작업이 있다면 적은 작업시간부터 할 것!(sjf)
    
    2. 현재 작업시작시간과 끝나는 시간을 표기해두고, 그안에 요청 들어온 작업들을 하나씩 처리하는 형태로 구현.
    
    3. 우선순위큐에 요청시간 순 정렬해서 넣어두고, 그시간에 맞는 요청들을 하나씩 빼서 우선순위 대기큐에 넣어서 처리.
    
    4. 처리할때마다 작업종료시간-요청시간을 정답에 넣어두고, 작업개수로 나눠서 정답 반환.
**/

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2) -> o1[0] - o2[0]);
        
        for(int[] job : jobs){
            int demand = job[0];
            int time = job[1];
            
            q.offer(new int[] {demand,time});
        }
        
        int before = -1; // 작업시작시간
        int after = 0; // 작업종료시간
        int cnt = 0; // 작업완료한 개수
        PriorityQueue<int[]> readyQ = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);
        
        while(cnt < jobs.length){
            
            while(!q.isEmpty()){
                int[] cur = q.peek();
                int demand = cur[0];
                int time = cur[1];

                // 이작업이 시작한 이후면서 종료전에 들어왔으면 대기큐에 넣기.
                if(before < demand && demand <= after){
                    readyQ.offer(q.poll());
                }else{
                    break;
                }
            }
            
            //대기중인 작업이 없으면 시간 증가 후 다시 확인.
            if(readyQ.isEmpty()){
                after++;
                continue;
            }
            
            int[] task = readyQ.poll();
            int demand = task[0];
            int time = task[1];
            
            before = after;
            after += time;
            answer += after - demand;
            cnt++;
        }
        
        return answer/cnt;
    }
}