/**
    찍은 로또 번호(가려진 것 포함)와 정답 번호 받기
    
    모든원소는 45이므로, 46길이 배열 만들어서 정답 체크
    
    로또를 돌면서, 정답에 있는 개수 파악과
    
    0의 개수 파악
    
    0의 개수 다 맞았을 시 등수와
    그대로의 등수 출력
*/

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int[] arr = new int[46];
        
        int cnt = 0;
        int zero = 0;
        for(int v: win_nums){
            arr[v]++;
        }
        
        for(int v: lottos){
            if(arr[v] > 0){
                cnt++;
            }
            if(v == 0){
                zero++;
            }
        }
        
        if(cnt >= 1){
            answer[0] = 7-(cnt+zero);
            answer[1] = 7-cnt;
            return answer;
        }else{
            if(zero == 0){
                answer[0] = 6;
            }else{
                answer[0] = 7 -zero;   
            }
            answer[1] = 6;
            return answer;
        }
    }
}