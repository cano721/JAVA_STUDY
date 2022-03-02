import java.util.*;
import java.util.Map.Entry;
import java.io.*;
class Solution {

    static boolean[] check = new boolean[11];
    static TreeMap<String, Integer> map = new TreeMap<>();
    
    
    static int MAX;
    static void dfs( String s, String temp, int k, int picked){
        if(picked == MAX+1) return;
        
        int n = temp.length();
        if(check[n]){
            
            if(map.containsKey(temp)){
                map.put(temp, map.get(temp)+1);
            }else {
                map.put(temp, 1);
            }
              
            
        }
        
        
        //s의 부분 문자열 만들어 내기.
        for(int i =k; i<s.length(); i++){
            dfs(s, temp+s.charAt(i), i+1, picked+1);
        }
        
    }
    
    public List<String> solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        MAX = course[course.length-1];
        
        for(int i =0; i< course.length; i++){
            check[course[i]] = true;
        }
            
        for(int i =0; i< orders.length; i++){
            char[] s = orders[i].toCharArray();
            Arrays.sort(s);
            dfs(String.valueOf(s), "",  0, 0);
        }
        
        for(int i =0; i< course.length; i++){
            int value = 0;
            List<String> list = new ArrayList<>();
            for(Entry<String, Integer> entry : map.entrySet()){
                
                String k = entry.getKey();
                int v = entry.getValue();
                if(v >= 2) {
                    if( k.length() == course[i]){
                        if(value < v){
                            list = new ArrayList();
                            list.add(k);
                            value = v;
                        }
                        else if( value == v){
                            list.add(k);
                        }
                    }
                }
                
            }   
            answer.addAll(list);
        }
        Collections.sort(answer);
        return answer;
    }
}