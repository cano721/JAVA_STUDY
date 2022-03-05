/**
    1. 맵에 조건별로 key:점수리스트저장?
    1-1) dfs를 통해 - 조건의 가능성도 만들어서 저장
    
    2. 맵에 저장된 value 리스트 정렬
    
    3. query문을 돌면서 해당 조건 key의 벨류를 불러오기
    
    4. query 점수에 따라 이분탐색으로 key의 벨류의 리스트에서 인원 파악
**/

import java.util.*;

class Solution {
    Map<String,ArrayList<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        // 조합 별 점수 리스트 생성
        for(String str : info){
            String[] strs = str.split(" ");
            dfs(0,"",strs);
        }
        
        // 점수 리스트 정렬
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        
        // 쿼리 별 인원 체크
        for(int i = 0; i < query.length; i++){
            String q = query[i];
            q = q.replaceAll(" and ","");
            String[] qs = q.split(" ");
            String checkQuery = qs[0];
            int score = Integer.parseInt(qs[1]);
            
            int people = binary_search(checkQuery,score);
            answer[i] = people;
        }
        
        return answer;
    }
    
    public int binary_search(String checkQuery ,int score){
        
        // key에 없으면 0명
        if(!map.containsKey(checkQuery)){
            return 0;
        }
        ArrayList<Integer> arr = map.get(checkQuery);
        
        int start = 0;
        int end = arr.size()-1;
        
        int result = -1;
        
        while(start <= end){
            
            int mid = (start+end)/2;
            int midNum = arr.get(mid);
            if(midNum < score){
                start = mid +1;
            }else{
                end = mid -1;
                result = mid;
            }
        }
        
        // 한번도 변경 안되었으면 없다는 뜻
        if(result == -1){
            return 0;
        }
        
        return arr.size() - result;
    }
    
    public void dfs(int stage, String temp, String[] strs){
        
        if(stage == strs.length-1){
            int score = Integer.parseInt(strs[strs.length-1]);
            if(!map.containsKey(temp)){
                map.put(temp,new ArrayList<>());
            }
            map.get(temp).add(score);
            return;
        }
        
        dfs(stage+1, temp + strs[stage], strs);
        dfs(stage+1, temp + "-", strs);
    }
}