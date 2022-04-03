package 전체유형문제풀이.프로그래머스;

import java.util.*;


public class PG12899_124나라의숫자 {

	public static void main(String[] args) {

		int n = 4;

		String result = solution(n);

		System.out.println(result);

	}
	
	static String[] num = {"4","1","2"};

	private static String solution(int n) {
		String answer = "";
	       
        while(n > 0){
            answer = num[n%3] + answer;
            n =(n-1)/3;
        }
        
        
        return answer;
	}

}