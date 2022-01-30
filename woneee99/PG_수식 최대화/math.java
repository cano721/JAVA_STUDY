import java.util.*;

public class math {
	class Solution {
		char[] character = {'+', '*', '-'};
		boolean[] check = new boolean[3];
	    long answer;
	    
	    public long solution(String expression) {
	        String[] str = expression.split("\\-|\\*|\\+");
	        String[] op  = expression.split("[0-9]{1,}");
	        
	        ArrayList<Long> num = new ArrayList<>();
	        for (int i=0; i<str.length; i++) {
	        	num.add(Long.parseLong(str[i]));
	        }
	        
	        ArrayList<Character> list_op = new ArrayList<>();
	        for (int i=1; i<op.length; i++) {
	        	list_op.add(op[i].charAt(0));
	        }
	        
	        dfs(0, num, list_op);
	        
	        return answer;
	    }
	    
		public void dfs(int cnt, ArrayList<Long> num, ArrayList<Character> op) {
	        if (cnt == 3) {
	            answer = Math.max(answer, Math.abs(num.get(0)));
	            return;
	        }
	        for (int i=0; i<3; i++) {
	            if (check[i]) continue;
	            
	            ArrayList<Long> temp_num = new ArrayList<>();
	            temp_num.addAll(num);
	            
	            ArrayList<Character> temp_op = new ArrayList<>();
	            temp_op.addAll(op);
	            
	            for (int j=0; j<temp_op.size(); j++) {
	            	if (character[i] == temp_op.get(j)) {
	            		func(j, character[i], temp_num);
	            		temp_op.remove(j);
	            		j--;
	            	}
	            }
	            
	            check[i] = true;
	            dfs(cnt+1, temp_num, temp_op);
	            check[i] = false;
	        }
	    }
	    
	    public void func(int index,  char c, ArrayList<Long> num) {
	        if (c == '-') {
	        	num.set(index, num.get(index) - num.get(index+1));
	        	num.remove(index+1);
	        }
	        else if (c == '+') {
	        	num.set(index, num.get(index) + num.get(index+1));
	        	num.remove(index+1);
	        }
	        else {
	        	num.set(index, num.get(index) * num.get(index+1));
	        	num.remove(index+1);
	        }
	    }
	}
}