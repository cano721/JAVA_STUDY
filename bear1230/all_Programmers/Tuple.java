/*
1. },{ 을 기존으로 split
*/
import java.util.Arrays;
import java.util.ArrayList;
class Solution {
    public int[] solution(String s) {   
        ArrayList<String> list = new ArrayList<>();
        s = s.substring(2, s.length()); // {{ 괄호 제거
        s = s.substring(0,s.length()-2); // }} 제거
        String[] arr = s.split("},\\{"); // 중간 괄호 기준으로 split

        Arrays.sort(arr, (s1, s2) -> s1.length() - s2.length());


        for(int i = 0; i < arr.length; i++){
            for(String str : arr[i].split(",")){  
                if(!list.contains(str)) 
                    list.add(str); 
            } 
        } 
        int[] answer = new int[list.size()]; 
        for(int i = 0; i < list.size(); i++) 
            answer[i] = Integer.parseInt(list.get(i)); 
        return answer;

    }
}