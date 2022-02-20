/**
    1. 재귀 구현 풀이
    
    2. 해당 문자열이 올바른지 체크 함수
    
    3. 해당 문자열 분리 함수
    
**/

import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        
        answer = solve(p);
        return answer;
    }
    
    public String solve(String p){
        
        if(p.equals("")){
            return "";
        }
        
        String[] uv = checkBalance(p);
        
        String u = uv[0];
        String v = uv[1];
        
        if(checkCorrect(u)){
            return u+solve(v);
        }else{
            String ans = "(";
            ans += solve(v);
            ans += ")";

            String temp = "";
            for(int i = 1; i < u.length()-1; i++){
                if(u.charAt(i) == '('){
                    temp += ")";
                }else{
                    temp += "(";
                }
            }

            ans += temp;

            return ans;
        }
    }
    
    // 올바른 문자열인지 체크 함수
    public boolean checkCorrect(String u){
        
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < u.length(); i++){
            if(u.charAt(i) == '('){
                stack.push('(');
            }else{
                if(!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    // 균형잡힌 괄호 문자열 분리 함수
    public String[] checkBalance(String p){
        
        int cnt = 0;
        int cnt2 = 0;
        
        String u = "";
        String v = "";
        for(int i = 0; i < p.length(); i++){
            char c = p.charAt(i);
            if(c == '(') cnt++;
            else cnt2++;
            
            if(cnt != 0 && cnt == cnt2){
                u = p.substring(0,i+1);
                v = p.substring(i+1,p.length());
                break;
            }
        }
        
        return new String[] {u,v};
    }
}