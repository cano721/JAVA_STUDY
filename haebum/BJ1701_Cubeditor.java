/**
 * 
 * 문자열 문제
 * 
 * 1. kmp 알고리즘 중 부분 문자열 내부에 패턴 파악 코드 사용
 * 
 * 2. 패턴 경계 길이 중 가장 긴 것 출력
 * 
 * 3. 하지만 기존 코드로는 앞에서부터만 체크하니, 전체 문자열 돌면서 서브스트링 만들기
 * 
 * 전체문자열 for문 O(n)
 * 패턴 구하기 for문 O(n)
 * 
 * n^2 풀이
 * 
 */
import java.util.*;
import java.io.*;

public class BJ1701_Cubeditor {
    
    public static String str;
    public static int[] pi;
    public static int max;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //문자열
        str = br.readLine();
        for(int i = 0; i < str.length(); i++){
            String subStr = str.substring(i, str.length());
            getPi(subStr);
        }

        System.out.println(max);
    }

    public static void getPi(String subStr){
        pi = new int[subStr.length()];

        int j = 0;
        for(int i = 1; i < subStr.length(); i++){
            while(j > 0 && subStr.charAt(i) != subStr.charAt(j)){
                j = pi[j-1];
            }

            if(subStr.charAt(i) == subStr.charAt(j)){
                pi[i] = ++j;
                max = Math.max(max,pi[i]);
            }
        }
    }
}
