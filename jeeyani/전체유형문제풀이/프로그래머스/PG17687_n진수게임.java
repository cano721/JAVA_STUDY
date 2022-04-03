package 전체유형문제풀이.프로그래머스;

import java.util.*;

/*
 * 1. 각 0부터 숫자를 최대 t*m길이만큼 구하기  (1000 * 100) 
 * 
 * 2. 각 n진수를 구하여 새로운 String에 담기
 * n진수 구하기 https://hongjw1938.tistory.com/43
 * 
 * 3. 튜브의 순서에 따른 문자만 출력하기 
 * 
 * */

public class PG17687_n진수게임 {

	public static void main(String[] args) {

		int n = 2;
		int t = 4;
		int m = 2;
		int p = 1;

		String result = solution(n,t,m,p);

		System.out.println(result);

	}

	private static String solution(int n, int t, int m, int p) {
		String answer = "";
        
        String tempStr = "0";
        int idx = 1;
        
        while(true){
            
            if(tempStr.length() >= t*m) break;
            
            String temp = conversion(idx,  n); 
            tempStr += temp;
            idx++;
            
        }
        
        for(int i = p-1; i<t*m; i+=m){
            answer +=tempStr.charAt(i);
        }
        
        
        return answer;
	}

	private static String conversion(int number, int N) {
		StringBuilder sb = new StringBuilder();
	    int num = number;
	    
        
        while(num > 0){
        	// 만약 N으로 나누었는데 10보다 작다면 해당 숫자를 바로 append
            if(num % N < 10){
                sb.append(num % N);

            } 
            
			/*만약 N이 10보다 큰 경우, N으로 나누었는데 10 이상이면 A, B등으로 표현하므로 기존 숫자는 10진법이므로 10만큼 빼고 'A'를 더한다. 
			왜냐면 1~9까지는 숫자로 표기하지만, 10 부터는 'A', 'B' 순서로 나타내기 때문이다.
			나머지가 10이라면 'A' + 10이 아니라 'A'로 나타내야 하기 때문*/
            else {
                sb.append((char)(num % N - 10 + 'A'));
            }
            num /= N;
        }
        
        return sb.reverse().toString();
	}
	
	
}