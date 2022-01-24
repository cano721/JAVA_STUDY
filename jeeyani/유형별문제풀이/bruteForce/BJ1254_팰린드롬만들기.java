package 유형별문제풀이.bruteForce;

import java.util.*;
import java.io.*;

public class BJ1254_팰린드롬만들기 {
	
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb; 

        String str = br.readLine();
        n = str.length();
        
        /*
         * 1. 문자열을 하나씩 자른 후, 자른 문자열과 해당 문자열의 역정렬문자열을 비교
         * 2. 역정렬문자열과 같은 경우, 자른문자열의 나머지 길이만큼 더해주기!!
         */
        for(int i = 0; i < n; i++){
            
            String temp = str.substring(i);

            // reverse기능을 쓰기위한 버퍼
            sb = new StringBuffer(str);
            String revStr = sb.reverse().toString();

            // 자바 문자열끼리 비교시엔 equals 써야함
            if(str.equals(revStr)){
                System.out.println(i+n);
                
                break; //**break!! 
            }
        }
    }


}
