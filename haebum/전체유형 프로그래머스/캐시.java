/**
    1. LRU 캐시 교체 알고리즘
    1-1) 캐시가 꽉 차 있으면 최근에 사용하지 않은 것을 교체
    
    2. 처음에 아무것도 없으면 시간 5, 캐시에 있으면 1
    
    3. queue로 최근 사용 여부를 확인
    
    q.remove() O(n)
    
    캐시 사이즈는 최대 30 * 도시 10^5
**/

import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Queue<String> q = new LinkedList<>();
        
        // 캐시 사이즈가 0일때
        if(cacheSize == 0){
            return cities.length * 5;
        }
        
        for(String city : cities){
            city = city.toLowerCase();
            // 캐시에 있을때
            if(q.contains(city)){
                q.remove(city); // 원소 삭제
                q.offer(city); // 맨 뒤로 추가
                answer+= 1;
            // 없을 때
            }else{
                if(q.size() == cacheSize){
                    q.poll();
                }
                q.offer(city);
                answer += 5;
            }
        }
        
        return answer;
    }
}