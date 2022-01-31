/*
DFS - 타겟 넘버
*/

class Solution {
    static int answer = 0;

    public int solution(int[] numbers, int target) {

        dfs(0, 0, numbers, target);
        
        return answer;
    }
    
    static void dfs(int num, int idx, int[] numbers, int target){
        if(num == numbers.length){
            if(idx == target)
                answer++;
        } else {
            dfs(num + 1, idx + numbers[num], numbers, target);
            dfs(num + 1, idx - numbers[num], numbers, target);
                
        }
    }
}
