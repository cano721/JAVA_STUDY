import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        Set<String> set = new HashSet<>();
        for(int i =0; i< phone_book.length; i++){
            if(!set.add(phone_book[i])) return false;
            for(int j=0; j<phone_book[i].length()-1; j++){
                if(set.contains(phone_book[i].substring(0,j+1))) return false;
            }
            
        }
        return answer;
    }
}