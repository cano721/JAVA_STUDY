/**
    1. 투포인터로 풀이
    
    2. 해시셋을 통해 총 보석 종류의 개수 확인
    
    3. start와 end 인덱스를 지정해두고, 해당 구간 해시 저장
    
    3. 셋에 담긴 개수와 동일할때, 현재 길이를 저장
    
    4. 가장 적은 길이를 반환
    
    시간복잡도 20만
**/

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        // 보석종류 담은 셋
        HashSet<String> set = new HashSet<>(Arrays.asList(gems));
        
        Map<String,Integer> map = new HashMap<>();
        
        int start = 0; //스타트 인덱스
        int end = 0; // 끝 인덱스
        int arrLength = Integer.MAX_VALUE; // 가장짧은 길이를 저장할 변수
        int size = set.size(); // 보석 종류 개수
        
        
        while(start < gems.length){
            
            // 끝까지 확인 했거나 현재길이가 저장된 길이보다 길면 빼기
            if(end == gems.length || end-start-1 >= arrLength){
                if(map.get(gems[start]) == 1){
                    map.remove(gems[start++]);
                }else{
                    map.put(gems[start],map.get(gems[start])-1);
                    start++;
                }
            // 아니라면 더하기
            }else{
                map.put(gems[end],map.getOrDefault(gems[end],0)+1);
                end++;
            }
            
            // 현재 보석종류가 다 있고, 길이가 더 짧으면 정답 변경
            if(map.size() == size && arrLength > end-start-1){
                arrLength = end-start-1;
                answer[0] = start+1;
                answer[1] = end;
            }
        }
        return answer;
    }
}