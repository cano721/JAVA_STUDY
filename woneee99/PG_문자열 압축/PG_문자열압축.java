public class PG_문자열압축 {
	class Solution {
	    public int solution(String s) {
	        char[] str = s.toCharArray();
	        int answer = str.length;
	        
	        for (int len=1; len<=str.length/2; len++) {
	            int start = 0, cnt = 1, sum = len;
	            
	            int i=len;
	            for ( ; i+len-1<str.length; i+=len) {
	                boolean check = true;
	                for (int j=0; j<len; j++) {
	                    if (str[i+j] != str[start+j]) {
	                        check = false;
	                        break;
	                    }
	                }
	                
	                if (!check) {
	                    if (cnt > 1) sum += (cnt+"").length();
	                    sum += len;
	                    start = i;
	                    cnt = 1;
	                }
	                else cnt++;
	            }
	            if (cnt > 1) sum += (cnt+"").length();
	            sum += str.length - i;
	            
	            answer = Math.min(answer, sum);
	        }
	        
	        return answer;
	    }
	}
}