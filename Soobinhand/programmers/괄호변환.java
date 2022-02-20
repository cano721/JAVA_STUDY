package Soobinhand.programmers;

import java.util.Stack;

public class 괄호변환 {
    static int position;
    public String solution(String p) {
        String answer = "";
        if (p.length() == 0){
            return "";
        }
        boolean cor = isCorrect(p);
        String u = p.substring(0, position);
        String v = p.substring(position);
        if (cor){
            return u + solution(v);
        }
        answer = "(" +solution(v) +")";
        for (int i=1;i<u.length()-1;i++){
            if (u.charAt(i) == '('){
                answer += ")";
            }else{
                answer += "(";
            }
        }
        return answer;
    }

    static boolean isCorrect(String p){
        boolean ret = true;
        int left = 0;
        int right = 0;
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<p.length();i++){
            if (p.charAt(i) == '('){
                left++;
                stack.push('(');
            }else{
                right++;
                if (stack.isEmpty()){
                    ret = false;
                }else{
                    stack.pop();
                }
            }
            if (left == right){
                position = i + 1;
                return ret;
            }
        }
        return true;
    }
}
