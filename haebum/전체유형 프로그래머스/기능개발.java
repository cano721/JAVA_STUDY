/**
    1. queue에 작업 퍼센트와 속도를 객체로 큐에 넣기
    
    2. 하루 지날때마다 전체 객체의 속도에 따라 작업 퍼센트 증가
    
    3. 만약 첫번째 원소가 100퍼를 넘거나 같으면, 큐를 돌면서 100퍼 넘은건 다 같이 빼내기
    
    4. 개수 체크하여 정답리스트에 담기
    
    5. 2~4번 반복하여 정답리스트 구하기
    
    6. 정답리스트 배열로 반환
**/

import java.util.*;

class Solution {
    
    public class Program{
        int percent;
        int speed;
        
        public Program(int percent, int speed){
            this.percent = percent;
            this.speed = speed;
        }
    }
    
    public int[] solution(int[] progresses, int[] speeds) {
        
        Queue<Program> q = new LinkedList<>();
        
        for(int i = 0; i < speeds.length; i++){
            q.offer(new Program(progresses[i],speeds[i]));
        }
        
        int cnt = 0;
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        while(!q.isEmpty()){
            int qsize = q.size(); // for문 돌 큐의 크기
            
            // 100퍼 넘으면 배포
            if(q.peek().percent >= 100){
                // 같이 배포할 수 있는 것들 확인
                for(int i = 0; i < qsize; i++){
                    if(q.peek().percent >= 100){
                        Program cur = q.poll();
                        cnt++;
                    }else{
                        break;
                    }
                }
                ans.add(cnt);
                cnt = 0;
            }else{
                // 큐의 크기만큼 돌면서 증가
                // 100만드는데 걸리는 날짜 구하기(날짜만큼 돌기)
                int day = (int)Math.ceil((100 - q.peek().percent) / (double)q.peek().speed);
                for(int i = 0; i < qsize; i++){
                    Program cur = q.poll();
                    q.offer(new Program(cur.percent+cur.speed*day,cur.speed));
                }
            }
        }
        int[] answer = new int[ans.size()];
        
        for(int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}