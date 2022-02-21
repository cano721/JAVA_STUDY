/**
    1. 전체 번호를 해시에 등록해두기
    
    2. 전체 번호를 하나씩 돌면서, 전화번호의 일부가 해시에 있는지 확인
    2-1) Stringbuilder 를 통해 전화번호를 만드는 연산 시간복잡도를 O(1)
    2-2) substring을 써도 시간복잡도엔 문제 없을 것으로 보임
         1~20 까지 의 합이므로, 20*21/2 즉 시간복잡도 = 10*21 * 10^6
    
    3. 1_000_000*20 = 10^7*2 시간복잡도
**/

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        Map<String,Integer> map = new HashMap<>();
        
        // 해시 등록
        for(String phone_number: phone_book){
            if(!map.containsKey(phone_number)){
                map.put(phone_number,1);
            }else{
                return false;
            }
        }
        
        // 전화번호의 일부가 해시에 들어있다면, false 반환
        for(String phone_number: phone_book){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < phone_number.length()-1; i++){
                sb.append(phone_number.charAt(i));
                
                if(map.containsKey(sb.toString())){
                    return false;
                }
            }
        }
        
        return true;
    }
}