/**
    1. s의 길이만큼 회전
    1-1) stringBuilder 사용
    
    2. 회전시 해당 문자열이 올바른 괄호 문자열인지 체크
    2-1) stack 사용
**/

import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < s.length(); i++){
            if(check(sb.toString())) answer++;
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }
        return answer;
    }
    
    public boolean check(String s){
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i< s.length(); i++){
            
            if(s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '('){
                stack.push(s.charAt(i));
            }else{
                
                if(stack.isEmpty()){
                    return false;
                }
                char c = stack.peek();
                if(c == '[' && s.charAt(i) == ']'){
                    stack.pop();
                }else if(c == '{' && s.charAt(i) == '}'){
                    stack.pop();
                }else if(c == '(' && s.charAt(i) == ')'){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
}