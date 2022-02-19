import java.util.*;

class Solution {
    public int solution(String s) {    
        int answer = 0;
		
		for(int i =0; i < s.length(); i++) {
			s = s.substring(1) + s.substring(0,1);
			
			if(check(s)) 
				answer++;
		}
		
		return answer;
	}
	
	private boolean check(String s) {
		Stack <Character> stack = new Stack<>();
        
		for(int i = 0; i<s.length(); i++) {
			char ch = s.charAt(i);
			if(stack.empty())
				stack.add(ch);
			else {
				if(s.charAt(i) == ']'){
                    if(stack.peek() == '['){
                        stack.pop();
                    }else{
                        stack.push(s.charAt(i));
                    }
                }else if(s.charAt(i) == ')'){
                    if(stack.peek() == '('){
                        stack.pop();
                    }else{
                        stack.push(s.charAt(i));
                    }
                }else if(s.charAt(i) == '}'){
                    if(stack.peek() == '{'){
                        stack.pop();
                    }else{
                        stack.push(s.charAt(i));
                    }
                }else{
                    stack.push(s.charAt(i));
                }
			} 
		}
		if(stack.isEmpty()) return true;
		else return false;
	}


    
}
