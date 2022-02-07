import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {0, gems.length};
        Set<String> set = new HashSet<>();
        for(int i =0; i< gems.length; i++){
            set.add(gems[i]);
        }
        int n = set.size();
        Map<String, Integer> map = new HashMap<>();
        int left = 0, right = n-1;
        for(int i =0; i<=right; i++){
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
        }
        
        while(right < gems.length && right >= left){
            int size = map.size();
            if(size < n){
                right++;
                if(right == gems.length) break;
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                
            }
            else if( size >= n){
                if( size == n){
                    int k = right - left;
                    if( answer[1] - answer[0] > right - left){
                        answer[0] = left+1;
                        answer[1] = right+1;
                    }
                    //System.out.println(size + "," + n + " keft :" +left + " right:" + right);
                }
                
                map.replace(gems[left], map.get(gems[left]) -1 );
                if(map.get(gems[left]) == 0) {
                    //System.out.println(left + " ," + gems[left] + " ," + map.get(gems[left]));
                    map.remove(gems[left]);
                }
                left++;
            }
    
        }
        
        return answer;
    }
}