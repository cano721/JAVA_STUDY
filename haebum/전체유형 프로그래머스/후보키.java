/**
    1. bit mask 이용해서 전체 조합 경우의 수 돌기
    
    2. 유일성과 최소성 확인
    
    3. 둘 다 해당되면 리스트에 담기
    
    4. 리스트 사이즈 반환
**/

import java.util.*;

class Solution {
    
    public int answer = 0;
    public boolean[] visited;
    
    public int solution(String[][] relation) {
        
        int collumnLen = relation[0].length;
        
        ArrayList<Integer> keys = new ArrayList<>();
        
        for(int i = 1; i < (1<<collumnLen); i++){
            
            // 최소성 확인
            if(!isMin(i,keys)) continue;
            
            // 유일성 확인
            if(checkHubo(i,relation)){
                keys.add(i);
            }
        }
        
        return keys.size();
    }
    
    // 최소성 체크
    public boolean isMin(int i, ArrayList<Integer> keys){
        
        for(int key : keys){
            if((i & key) == key) return false;
        }
        
        return true;
    }
    
    // 유일성 체크
    public boolean checkHubo(int bit, String[][] relation){
        
        Map<String,Integer> map = new HashMap<>();
        
        for(String[] row: relation){
            String str = "";
            for(int i = 0; i < row.length; i++){
                if(((bit>>i) &1) ==1){
                    str += row[i];
                }
            }
            if(map.containsKey(str)){
                return false;
            }else{
                map.put(str,1);
            }
        }
        
        return true;
        
    }
}