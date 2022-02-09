/**
    1. dp 풀이
    
    2. 점화식생성
    2-1) 가로가 n일때, 가로 n에 올 수 있는 경우의 수는
            n-1에서 세로로 배치하여 오는경우
            n-2에서 가로2개를 배치해서 오는경우가 있음
            dp[n] = dp[n-1] + dp[n-2]
            
            
    3. 초기값넣기
        dp[1] = 1
        dp[2] = 2
        
    4. 해당 n의 dp값 반환
**/

class Solution {
    public int solution(int n) {
        int[] dp = new int[n+1];
        int div = 1_000_000_007;
        
        for(int i = 1; i <=n; i++){
            if(i == 1) dp[1] = 1;
            else if(i == 2) dp[2] = 2;
            else{
                dp[i] = (dp[i-1]%div+dp[i-2]%div)%div;
            }
        }
        
        return dp[n];
    }
}