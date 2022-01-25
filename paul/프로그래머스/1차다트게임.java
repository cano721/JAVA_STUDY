import java.util.*;
class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        Stack<Integer> st= new Stack<>();
        int n = dartResult.length();
        
        for(int i =0; i<n; i++){
            
            char k = dartResult.charAt(i);
            
            if(k >= '0' && k <='9'){
                int point = k-'0';
                if(i != n-1 && k == '1' && dartResult.charAt(i+1) =='0'){
                    point = 10;
                    i++;
                }
                st.push(point);
            }
            else{
                int top = 0;
                switch(k){
                    case 'S' :
                        top = st.pop();
                        st.push(top);
                        break;
                    case 'D' :
                        top = st.pop();
                        st.push(top*top);
                        break;
                    case 'T' :
                        top = st.pop();
                        st.push(top * top *top);
                        break;
                    case '*' :
                        top = st.pop();
                        if(!st.empty()){
                            int second = st.pop();
                            st.push(second * 2);
                        }
                        st.push(top * 2);
                        break;
                    case '#' :
                        top = st.pop();
                        st.push(top * (-1));
                        break;
                }
            }
            
            
        }
        
        while(!st.empty()){
            answer += st.pop();
        }
        
        return answer;
    }
}