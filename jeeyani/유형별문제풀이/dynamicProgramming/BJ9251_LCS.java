package 유형별문제풀이.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * LCS 최장 공통 부분 수열
 * 
 * 1.LCS[0][0] = 0 : 첫 초기값 0설정
 * 2. 만약 문자열A와 문자열B가 같다면 LCS[i][j] = LCS[i - 1][j - 1] + 1
 * 3. 두 문자가 다르다면 LCS[i][j] = max(LCS[i - 1][j], LCS[i][j - 1])
 * 
 * 
 * 참고)https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence
*/

public class BJ9251_LCS {

	static String A,B;
	static int LCS[][]= new int[1001][1001];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//charAt(0)는 0으로 비교제외하기 위해
		A = '0'+br.readLine();
		B = '0'+br.readLine();
		
		LCS[0][0] = 0;
		
		int n = A.length();
		int m = B.length();
		
		for (int i = 1; i < A.length(); i++) {
			for (int j = 1; j < B.length(); j++) {
				
				if(A.charAt(i) == B.charAt(j)) {
					LCS[i][j] = LCS[i - 1][j - 1] + 1;
				}else {
					LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
				}
				
			}
		}
		System.out.println(LCS[n-1][m-1]);
	}

}
