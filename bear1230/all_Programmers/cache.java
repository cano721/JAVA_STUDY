import java.util.*; 
 
class Solution {
    public int solution(int cacheSize, String[] cities) {
        
        int answer = 0;
        List<String> list = new ArrayList<>();
        
        for(String beforeCity : cities) {
            String city = beforeCity.toLowerCase();
            
            if(list.contains(city)) {
                list.remove(city);
                list.add(city);
                
                answer += 1;
            } else if(list.size() < cacheSize) {
                list.add(city);
                answer += 5;
            } else {
                if(cacheSize > 0) {
                    list.remove(0);
                    list.add(city);
                }   
                answer += 5;
            } 
        }
        
        return answer;
    }
}
