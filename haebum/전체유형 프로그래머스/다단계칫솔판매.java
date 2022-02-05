/**
    1. 해시 맵을 통해 추천인 등록
    
    2. 판매했을때, 상위 추천인을 찾아 이익금의 10% 분배
    
    3. 최대 판매금액이 만원이므로, 최대치로 올라갈 수 있는건 4번
    
    4. 이익금을 해시맵에 저장해두고, 추후 배열로 반환
    
    시간복잡도: seller 범위 100,000*4
    
    
**/

import java.util.*;

class Solution {
    
    public Map<String,String> recommend = new HashMap<>();
    
    public Map<String,Integer> profit = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        for(int i = 0; i < referral.length; i++){
            recommend.put(enroll[i],referral[i]);
        }
        
        for(int i = 0; i < seller.length; i++){
            int percent = amount[i]*10;
            int money = amount[i]*100 - percent;
            
            profit.put(seller[i],profit.getOrDefault(seller[i],0)+money);
            pay(seller[i],percent);
        }
        
        int[] answer = new int[enroll.length];
        
        for(int i = 0; i < enroll.length; i++){
            answer[i] = profit.getOrDefault(enroll[i],0);
        }
        
        return answer;
    }
    
    public void pay(String person, int money){
        
        if(money == 0){
            return;
        }
        
        if(recommend.get(person).equals("-")){
            return;
        }
        
        String r = recommend.get(person);
        
        int percent = money/10;
        int m = money-percent;
        
        profit.put(r,profit.getOrDefault(r,0) + m);
        
        pay(r,percent);
    }
}