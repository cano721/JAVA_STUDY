import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        HashMap<String, Integer> map = new HashMap<>();
        int time = 0;
        int answer = 0;
        for(String s : cities){
            s = s.toUpperCase();
            time++;
            if(!map.containsKey(s)){
                answer+=5;
                map.put(s, time);
                //가장 과거에? 참조된 값 찾아서 삭제
                if(map.size() > cacheSize){
                    List<Map.Entry<String, Integer> > list = new LinkedList<>(map.entrySet());
                    list.sort(Map.Entry.comparingByValue());
                    map.remove(list.get(0).getKey());
                }
            }else{
                answer+=1;
                map.replace(s, time);
            }
        }
        
        return answer;
    }
}