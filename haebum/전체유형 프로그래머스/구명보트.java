/**
    1. 2명씩 탑승 가능
    
    2. 구명보트 무게 제한 100kg
    
    3. 구명 보트 최대한 적게 사용
    
    4. 정렬 배열에 투포인터로 처리.
    
    5. 그리디로 가장 작은 몸무게와 가장 큰 몸무게 탑승 처리
    
    
**/

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int start = 0;
        int end = people.length-1;
        
        while(start <= end){
            // 2명탑승
            if(people[start] + people[end] <= limit){
                start++;
                end--;
                answer++;
            // 가장 무거운사람 한명 탑승
            }else{
                end--;
                answer++;
            }
        }
        return answer;
    }
}