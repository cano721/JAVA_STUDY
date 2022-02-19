import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        for(int i =0; i<s.length(); i++){
            String t= s.substring(i ,s.length()) + s.substring(0, i);
            answer += check(t);
        }
        return answer;
    }
    
    static public int check(String s){
        
        int[] front = new int[3];
        Stack<Integer> st = new Stack<>();
        for(int i =0; i< s.length(); i++){
            switch(s.charAt(i)){
                case '(':
                    front[0]++;
                    st.push(0);
                    break;
                case '[':
                    front[1]++;
                    st.push(1);
                    break;
                case '{':
                    front[2]++;
                    st.push(2);
                    break;
                case ')':
                    if(--front[0] < 0 || st.pop() != 0) return 0;
                    break;
                case ']':
                    if(--front[1] < 0 || st.pop() != 1) return 0;
                    break;
                case '}':
                    if(--front[2] < 0 || st.pop() != 2) return 0;
                    break;
            }
        }
        
        if( front[0] + front[1] + front[2] != 0) return 0;
        return 1;
                
    }
}