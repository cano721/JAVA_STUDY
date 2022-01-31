/**
    1. dfs로 해당 숫자 + 또는 - 선택
    
    2. 모든 숫자 선택했을 시, 타겟이랑 동일하면 정답 증가
    
    
**/

class Solution {
    
    public int answer;
    public int solution(int[] numbers, int target) {
        solve(0,0,0,numbers,target);
        return answer;
    }
    
    public void solve(int stage, int idx,int sum, int[] numbers, int target){
        
        // 다 골랐을때 비교
        if(stage == numbers.length){
            if(sum == target) answer++;
            return;
        }
        
        solve(stage+1,idx+1,sum+numbers[idx],numbers,target);
        solve(stage+1,idx+1,sum-numbers[idx],numbers,target);
    }
}