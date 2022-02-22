import java.util.*;
class Solution {
    public String solution(String p) {
        return getAnswer(p);
    }
    
    static String getAnswer(String p){
        if(p.equals("")) return "";
        // u랑 v 분리. 
        int idx = splitUV(p);
        String u = p.substring(0, idx);
        String v = p.substring(idx, p.length());
        
        //u가 올바른 문자인지 판단.
        if(isRight(u)){
            return u + getAnswer(v);
        }else{
            return "(" + getAnswer(v) + ")" + reverse(u);
        }
        
    }
    
    static int splitUV(String s){
        // 균형잡인 문자열의 위치 반환.
        int front = 0, back =0;
        for(int i =0; i< s.length(); i++){
            if(s.charAt(i) =='(') front++;
            else back++;
            
            if(front !=0 && front == back){
                return i+1;
            }
        }
        return s.length();
    }
    
    static boolean isRight(String s){
        int idx = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) =='(') idx++;
            else idx--;
            
            if(idx <0) return false;
        }
        if(idx !=0 ) return false;
        return true;
    }
    
    static String reverse(String s){
        String val ="";
        for(int i =1; i<s.length()-1; i++){
            if(s.charAt(i) =='(') val +=")";
            else val += "(";
        }
        return val;
    }
}