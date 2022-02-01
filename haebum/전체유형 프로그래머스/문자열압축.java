/**
    1. 압축 단위를 정하고, S를 돌면서 압축
    
    2. S의 길이는 1000이므로, 최대 압축단위는 500*S의길이 1000
    
    3. 슬라이딩 윈도우 크기를 압축단위로 지정하고, 돌면서 해당하는것을 압축
    
    4. 압축된 문자열의 숫자를 기록했다가 압축되지 않는 문자열을 만났을때, 결과 스트링에 더하기
    
    5. 결과 스트링의 길이 반환
    
    5. 최저 길이를 최종반환
**/

import java.util.*;

class Solution {
    
    public Map<String,Integer> map = new LinkedHashMap<>();
    
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
        if(s.length() == 1) return 1;
        int hs = s.length()/2;
        for(int w = 1; w <= hs; w++){
            int len = compress(w,s);
            answer = Math.min(answer,len);
        }
        return answer;
    }
    
    // 압축 함수
    public int compress(int w, String s){
        
        String result = "";
        String zip = s.substring(0,w);
        int cnt = 1;
        for(int i = w; i <= s.length(); i+= w){
            String next = s.substring(i, (i + w > s.length()) ? s.length() : i + w);
            if(zip.equals(next)){
                cnt++;
                continue;
            }
            if(cnt >1){
                result += String.valueOf(cnt);
                cnt = 1;
            }
            result += zip;
            zip = next;
        }
        result += zip;
        return result.length();
    }
}