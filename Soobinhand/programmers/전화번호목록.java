package Soobinhand.programmers;

import java.util.HashMap;

public class 전화번호목록 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<phone_book.length;i++){
            map.put(phone_book[i], i);
        }
        for(int i=0;i<phone_book.length;i++){
            for(int j=1;j<phone_book[i].length();j++){
                if(map.containsKey(phone_book[i].substring(0,j))){
                    answer = false;
                    return answer;
                };
            }
        }
        return answer;
    }
}
