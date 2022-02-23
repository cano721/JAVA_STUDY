/**
    1. word를 지정할 idx 변수를 둠
    
    2. words의 길이를 넘어가면, [0,0] 반환
    
    3. 단어 길이 체크
    
    4. 중복 단어 체크
    
    5. 이전에 말했던 단어의 마지막이 현재 단어의 시작부분인지 확인
    
    6. 만약 위의 조건에 안맞으면, 현재사람의 번호와 차례 반환
    
    7. 사람과 차례는 나머지 연산을 통해 구하기
**/

import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        
        int idx = 0;
        
        Map<String,Integer> map = new HashMap<>();
        
        while(true){
            
            // 현재 사람과 차례변수
            int p  = (idx%n) +1;
            int num = idx/n +1;
            
            // 단어길이 넘어갔는지 체크
            if(words.length <= idx){
                return new int[] {0,0};
            }
            
            String word = words[idx];
            
            // 단어 길이 2글자 이하
            if(word.length() < 2){
                return new int[] {p,num};
            }
            
            // 중복단어 체크
            if(map.containsKey(word)){
                return new int[] {p,num};
            }else{
                map.put(word,1);
            }
            
            // 조건 체크
            if(idx > 0){
                String pword = words[idx-1];
                if(pword.charAt(pword.length()-1) != word.charAt(0)){
                    return new int[] {p,num};
                }
            }
            
            // 다음 단어 확인
            idx++;
        }
    }
}