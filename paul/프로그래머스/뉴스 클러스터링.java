import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
    
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        
        Set<String> set = new HashSet<>();
        
        Map<String, Integer> map1 = getElement(str1, set);
        Map<String, Integer> map2 = getElement(str2, set);
        
        List<String> list = new ArrayList<>(set);
        
        double crossValue =0, unionValue =0;
        for(String s : list){
            crossValue += Math.min(map1.getOrDefault(s, 0), map2.getOrDefault(s, 0) );
            unionValue += Math.max(map1.getOrDefault(s, 0), map2.getOrDefault(s, 0) );
        }
        
        double answer = 0;
        if(unionValue == 0) answer =1;
        else answer = (crossValue / unionValue);
        // System.out.println(crossValue + ": " + unionValue + ": " + answer);
        return (int)(answer*65536);
    }
    
    static Map<String, Integer> getElement(String s, Set<String> set){
         Map<String, Integer> map = new HashMap<>();
        for(int i =0; i< s.length()-1; i++){
            if(Character.isAlphabetic(s.charAt(i)) && Character.isAlphabetic(s.charAt(i+1))){
                String t = s.substring(i, i+2);
                set.add(t);
                map.put(t, map.getOrDefault(t, 0)+1);
            }
        }
        return map;
    }
}