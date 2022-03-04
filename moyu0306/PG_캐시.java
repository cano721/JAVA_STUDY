import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        ArrayList<String> list = new ArrayList<>();
        int cnt =0;

        for(int i=0; i<cities.length;i++){
            boolean hit = false;
            String word= cities[i].toLowerCase();
            if(cacheSize == 0){
                cnt = cnt+5;
                continue;
            }

            if(list.contains(word)){
                cnt++;
                list.remove(word);
                list.add(word);
            }else if(list.size()==cacheSize){
                cnt= cnt+5;
                list.remove(0);
                list.add(word);
            }else{
                cnt = cnt+5;
                list.add(word);
            }
        }

        return cnt;
    }
}