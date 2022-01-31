/**
     1. 문자열 파싱
     1-1) 맨처음 중괄호 삭제(index 0 과 끝은 안 돌림)
     1-2) '{'을 만나면 다음 index를 시작 index로 저장
     1-3) '}'을 만나면 종료 인덱스로 저장
     1-4) 해당 서브스트링을 어레이리스트 저장
     1-5) 문자열 다 서브스트링으로 파싱 후 어레이리스트 돌기
     1-6) 해당 서브스트링 ','을 기준으로 원소 분리
     1-7) 해당 원소집합 해시 저장
     
     2. 해시를 벨류가 큰값을 기준으로 정렬
     
     3. 해시리스트를 돌며 배열에 담아서 반환
**/

import java.util.*;

class Solution {
    
    Map<Integer,Integer> map = new HashMap<>();
    
    public int[] solution(String s) {
        
        ArrayList<String> subs = new ArrayList<>();
        int start = 0;
        
        // 전체 문자열 돌면서 집합의 원소 분리(10^6)
        for(int i = 1; i < s.length()-1; i++){
            if(s.charAt(i) == '{'){
                start = i+1;
            }
            else if(s.charAt(i) == '}'){
                String sub = s.substring(start,i);
                subs.add(sub);
            }
        }
        
        // 집합의 원소  돌면서 원소 확인(10^5 * 5*10^2);
        for(String str : subs){
            // 원소집합
            String[] elements = str.split(",");
            save(elements);
        }
        
        // 해시 값 기준 오름차순 정렬
        List<Map.Entry<Integer,Integer>> mapList = new LinkedList<>(map.entrySet());
        mapList.sort((o1,o2) -> map.get(o2.getKey()) - map.get(o1.getKey()));
        
        // 정렬 순서대로 배열 집어 넣기
        int[] answer = new int[mapList.size()];
        for(int i = 0; i < mapList.size(); i++){
            Map.Entry<Integer,Integer> v = mapList.get(i);
            answer[i] = v.getKey();
        }
        
        return answer;
    }
    
    // 원소 해시 저장 함수
    public void save(String[] elements){
        for(String e: elements){
            int en = Integer.parseInt(e);
            map.put(en,map.getOrDefault(en,0)+1);
        }
    }
    
}