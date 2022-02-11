/**
    1. n이 0이 될때까지 구하기
    
    2. 짝수면 나누기, 홀수면 빼기
    
    3. 정답 반환
**/

import java.util.*;

public class Solution {
    
    public int[] dp;
    public int solution(int n) {
        int ans = 0;
        
        while(n != 0){
            
            if(n % 2 == 0){
                n/=2;
            }else{
                n-=1;
                ans++;
            }
        }
        

        return ans;
    }
    
    
}