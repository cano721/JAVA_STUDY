/**
 * 전체 문자열과 패턴이 주어졌을때,
 * 
 * 전체 문자열에 패턴이 존재하는지 확인
 * 
 * KMP 사용 풀이
 */

import java.util.*;
import java.io.*;

public class BJ16916_부분문자열 {
    
    public static int answer,pi[];
    public static String all,pattern;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        all = br.readLine();
        pattern = br.readLine();

        pi = new int[pattern.length()];

        getPi();
        kmp();
        System.out.println(answer);

    }

    // 누적합 배열 만드는 함수
    public static void getPi(){
        int j = 0; 
        for(int i = 1; i < pattern.length(); i++){
            while(j > 0 && pattern.charAt(i) != pattern.charAt(j)){
                j = pi[j-1];
            }
            if(pattern.charAt(i) == pattern.charAt(j)){
                pi[i] = ++j;
            }
        }
    }
    
    public static void kmp(){
        int j = 0;
		for (int i = 0; i < all.length(); i++) {
			// 맞는 위치가 나올 때까지 j - 1칸의 공통 부분 위치로 이동
			while(j > 0 && all.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			// 맞는 경우
			if(all.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length() - 1) {
					answer = 1;
					break;
				}else{
                    j++;
                }
			}
		}
    }
}
