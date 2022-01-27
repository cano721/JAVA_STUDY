/**
    사전을 해시맵으로 구현
    
    1. 초기 영문 대문자 등록
    
    2. msg를 돌기
    1-1) 현재글자를 더한게 사전에 존재하면 임시 문자열에 더하고 넘어가기
    1-2) 존재하지 않으면 더한것을 사전에 등록하고 더하기전 문자열에 맞는 출력번호 리스트에 담고 문자열을 현재글자로 초기화
    
    3. 출력된 번호들을 리스트에 담기
    
    4. 배열로 반환
    
    
**/

import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String,Integer> map = new HashMap<>();
        
        int idx = 1;
        // 초기 등록
        for(int i = 65; i < 65+26; i++){
            String str = String.valueOf((char)i);
            map.put(str,idx++);
        }

        String ptemp = "";
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < msg.length(); i++){
            // 현재글자
            String temp = ptemp + String.valueOf(msg.charAt(i));
            // 사전에 존재함
            if(map.containsKey(temp)){
                ptemp = temp;
                continue;
            // 존재하지 않음
            }else{
                map.put(temp,idx++);
                arr.add(map.get(ptemp));
                ptemp = String.valueOf(msg.charAt(i));
            }
        }
        // 마지막 남는 글자 처리
        arr.add(map.get(ptemp));
        
        //배열처리
        int[] answer = new int[arr.size()];
        
        int cnt = 0;
        for(int v: arr){
            answer[cnt++] = v;
        }
        return answer;
    }
    
    
}