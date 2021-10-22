/*
    문자열 슬라이싱을 통해 풀 예정
    문자열을 i번째부터 잘라내서 팰린드롬인지 확인

    팰린드롬인지 확인하는 방법은
    잘라낸 문자열과 역정렬한 문자열과 동일하면 팰린드롬

    그럼 앞에서 i만큼 잘라냈으니 뒤에도 i만큼 붙여줘서 팰린드롬을 만들 수 있음.
    그로인해 문자열길이 + i가 정답 

    ex) abab 에서 1만큼 잘라낸 bab 를 거꾸로해도 bab 이므로 팰린드롬
    a 한개 1만큼 잘라냈으므로 뒤에도 a 하나만 붙이면 팰린드롬임. ababa
    그러므로 기존 문자열 길이 4 + 1 = 5


*/


import java.util.*;
import java.io.*;

public class BJ1254_팰린드롬만들기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb; 

        String pelin = br.readLine();
        
        for(int i = 0; i < pelin.length(); i++){
            // i부터 시작하는 문자열
            String str = pelin.substring(i);

            // reverse기능을 쓰기위한 버퍼
            sb = new StringBuffer(str);
            String str2 = sb.reverse().toString();

            // 자바 문자열끼리 비교시엔 equals 써야함
            if(str.equals(str2)){
                System.out.println(i+pelin.length());
                break;
            }
        }
    }

}