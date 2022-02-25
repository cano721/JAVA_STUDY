/**
    1. 첫번째 문자만 대문자 변경
    
    2. 나머진 소문자
**/

import java.util.*;

class Solution {
    public String solution(String s) {
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(i == 0){
                sb.append(Character.toUpperCase(s.charAt(i)));
            }else{
                if(s.charAt(i-1) == ' '){
                    sb.append(Character.toUpperCase(s.charAt(i)));
                }else{
                    sb.append(Character.toLowerCase(s.charAt(i)));
                }
            }
        }
        
        return sb.toString();
    }
}