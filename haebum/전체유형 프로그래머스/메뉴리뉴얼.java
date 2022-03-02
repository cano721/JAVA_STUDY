/**
    1. course를 돌며 몇개를 조합해야 하는지 정하기
    
    2. course에 정해진대로 orders를 돌며 조합(중복x,오름차순,order 문자열 정렬)
    
    3. 조합 된 것을 해시에 담으면서 value 증가
    
    4. 가장 많이 주문한 셋트 메뉴 result에 담기
    
    5. result 오름차순하여 배열 반환
    
    코스 10 * orders 20 * 조합(10*9)
**/

import java.util.*;

class Solution {
    
    public Map<String,Integer> map;
    public int max = 0;
    
    public String[] solution(String[] orders, int[] course) {
        
        ArrayList<String> ans = new ArrayList<>();
        
        for(int c : course){
            map = new HashMap<>();
            max = 0;
            
            for(String order: orders){
                char[] strs = order.toCharArray();
                Arrays.sort(strs);
                order = new String(strs);
                checkOrder(0,-1,order,"",c);
            }
            
            for(String key : map.keySet()){
                int value = map.get(key);
                if(value > 1 && max == value){
                    ans.add(key);
                }
            }
        }
        
        Collections.sort(ans);
        String[] answer = ans.toArray(new String[ans.size()]);
        
        return answer;
    }
    
    public void checkOrder(int stage, int pidx, String order,String cur, int end){
        if(stage == end){
            map.put(cur,map.getOrDefault(cur,0)+1);
            max = Math.max(max,map.get(cur));
        }
        
        for(int i = pidx+1; i < order.length(); i++){
            checkOrder(stage+1,i,order,cur + order.charAt(i),end);
        }
    }
}