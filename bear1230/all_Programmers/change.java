import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        answer = solve(p);
        return answer;
    }

    public String solve(String str){
        if(str.length() == 0) return "";

        String u = "", v = "";
        int left = 0;
        int right = 0;

        for(int i = 0 ; i < str.length() ; ++i){
            char ch = str.charAt(i);
            if(ch == '(') left++;
            else if(ch == ')') right++;

            if(left == right){
                u = str.substring(0, i + 1);
                v = str.substring(i + 1, str.length());
                break;
            }
        }
        
        v = solve(v);

        if(isRight(u)){
            return u + v;
        } else {
            String result = "(" + v + ")";
            char[] cu = u.toCharArray();

            for(int i = 1 ; i < cu.length - 1 ; ++i){
                if(cu[i] == '(') result += ')';
                else result += '(';
            }
            return result;
        }
    }

    public boolean isRight(String u){
        Stack<Character> stack = new Stack<>();

        for(int i = 0 ; i < u.length() ; ++i){
            char ch = u.charAt(i);

            if(ch == '('){
                stack.push(ch);
            } else {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }

        if(stack.isEmpty()) return true;
        else return false;
    }
}
