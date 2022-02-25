import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<String>();
        int count = 0;
        int flag= 0;
        String prev = words[0];
        set.add(prev);
        for(int i =1; i<words.length; i++){
            String now = words[i];
            flag= i;
            if(prev.charAt(prev.length()-1) == now.charAt(0) && set.add(now)){
                count++;
                prev = now;
            }else{
                answer[0] = i%n +1;
                answer[1] = i/n + 1;
                break;
            }
            
        }
        
        return answer;
    }
}