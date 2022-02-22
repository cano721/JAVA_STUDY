/**
    1. 문자열 파싱하여 다중집합 만들기
    1-1) 정규식 사용
    
    2. 다중집합 비교하여, 교집합과 합집합 구하기
    2-1) 같은 원소가 들어갈 수 있으므로, 2개의 맵을 사용하여 원소별 개수 파악
    2-2) 한개의 맵을 돌면서, 다른 맵과 비교하여 교집합 개수 파악(공통부분의 최소 개수)
    2-3) 두개의 맵을 돌면서, 합집합의 개수 파악(공통 부분은 최대 개수, 공통되지 않은 부분의 수 더하기)
    
    3. 교집합,합집합 이용하여 유사도 출력
    
**/

import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        ArrayList<String> strs1 = parsing(str1);
        ArrayList<String> strs2 = parsing(str2);
        
        if(strs1.size() == 0 && strs2.size() == 0){
            return 65536;
        }else if(strs1.size() == 0 || strs2.size() == 0){
            return 0;
        }
        
        int[] num = checkNum(strs1,strs2);
        int same = num[0];
        int sum = num[1];
        
        double ans = same/(double)sum;
        answer =  (int)(ans*65536);
        
        return answer;
    }
    
    public int[] checkNum(ArrayList<String> strs1, ArrayList<String> strs2){
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        
        for(String str1: strs1){
            map1.put(str1,map1.getOrDefault(str1,0)+1);
        }
        
        for(String str2: strs2){
            map2.put(str2,map2.getOrDefault(str2,0)+1);
        }
        
        int same = 0;
        int sum = 0;
        
        for(String key : map1.keySet()){
            int map1Value = map1.get(key);
            int map2Value = 0;
            
            if(map2.containsKey(key)){
                map2Value = map2.get(key);
            }
            
            same += Math.min(map1Value,map2Value);
            sum += Math.max(map1Value,map2Value);
        }
        
        for(String key : map2.keySet()){
            
            if(!map1.containsKey(key)){
                sum += map2.get(key);
            }
        }
        
        return new int[] {same,sum};
    }
    
    public ArrayList<String> parsing(String str){
        // str = str.replaceAll("[^a-zA-Z]*","");
        
        ArrayList<String> strs = new ArrayList<>();
        
        for(int i = 0; i < str.length()-1; i++){
            
            String check = str.substring(i,i+2);
            
            if(check.matches("[a-zA-Z]*")){
                strs.add(check.toUpperCase());
            }
        }
        
        return strs;
    }
}