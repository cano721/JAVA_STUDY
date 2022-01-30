class Solution {
    static int answer = 0; 
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
    public static void dfs(int[] numbers, int target, int picked, int ans){
        if(picked == numbers.length){
            if(ans == target) answer++;
            return;
        }
        
        dfs(numbers, target, picked+1, ans + numbers[picked]);
        dfs(numbers, target, picked+1, ans - numbers[picked]);
        
    }
}