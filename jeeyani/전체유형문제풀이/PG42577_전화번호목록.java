package 전체유형문제풀이;

import java.util.*;


/*
 * 
 * 해시맵 자료구조 
 * 
 * 앞부분부터 짜르면서 접두어에 있는지 확인하기
 * 
 * */


public class PG42577_전화번호목록 {

	public static void main(String[] args) {

		String[] phone_book = {"12","123","1235","567","88"};

		boolean result = solution(phone_book);

		System.out.println(result);

	}

	private static boolean solution(String[] phone_book) {
		boolean answer = true;
        
        Map <String, Integer> map = new HashMap<>();
        for(int i = 0; i<phone_book.length; i++){
            map.put(phone_book[i],i);
        }
        
        for(int i = 0; i<phone_book.length; i++){
            for(int j = 0; j<phone_book[i].length(); j++){
                
                if(map.containsKey(phone_book[i].substring(0,j))){
                    return false;
                } 
            }
        }
        
        return answer;
	}

	
}