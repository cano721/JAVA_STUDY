package 유형별문제풀이.kmp;

import java.io.BufferedReader;	
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 
 * KMP 알고리즘 사용
 * 
 * 1. 숫자만 삭제
 * 2. 접두사와 접미사가 일치하는 최대 길이를 저장하는 배열을 생성 table
 * 
 * 3. 구한 table을 활용하여 부분 문자열 찾기
 * 3-1. 긴글과 찾을 문자열을 하나씩 비교
 * 3-2. 서로 다른 문자가 발견되면 일치하는 접두사 크기에 한해서만 부분 문자열의 인덱스를 이동시킨다. j = table[j-1];
 * 3-3. 인덱스 이동 이후에도 계속 하나씩 비교를 하면서 진행
 * 
 @author Jeeyani
 */

public class BJ16172_나는친구가적다_Large {

	static String s,p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		s = br.readLine();
		p = br.readLine();

		//1. 숫자만 삭제하기
		s = s.replaceAll("[0-9]", "");
		
		int result = kmp();
		
		StringBuffer sb = new StringBuffer();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static int kmp() {
		
		int[] table = makeTable();
		int sLen = s.length();
		int pLen = p.length();
		
		int ans = 0;
		
		int j = 0;
		for (int i = 0; i < sLen; i++) {
			
			while(j > 0 && s.charAt(i) != p.charAt(j)) {
				// 일치하는 접두사 크기에 한해서만 부분 문자열의 인덱스를 이동
				j = table[j-1];
			}
			
			if(s.charAt(i) == p.charAt(j)) {
				//문자열의 마지막까지 찾은 경우
				if(j == pLen-1) {
					ans = 1;
					j = table[j];
				}
				else j++;
			}
			
		}

		return ans;
	}

	//접두사와 접미사가 일치하는 길이 저장하기
	private static int[] makeTable() {
		int pLen = p.length();
		
		int[] table = new int[pLen];
		
		int j = 0;
		for (int i = 1; i < pLen; i++) {
			
			while( j> 0 && p.charAt(i) != p.charAt(j)) {
				j = table[j-1];
			}
			
			if(p.charAt(i) == p.charAt(j)) {
				table[i] = ++j;
			}
			
		}
		
		return table;
	}
}
