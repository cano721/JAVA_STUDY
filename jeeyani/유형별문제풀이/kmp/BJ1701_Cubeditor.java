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
 * 2번 이상 존재한다 == 부분 문자열 중에 접두사와 접미사가 같은 경우가 있다는 것
 * 
 * 기존 kmp코드를 이용하여 전체 문자열을 돌 수 있도록 하기
 * 
 @author Jeeyani
 */

public class BJ1701_Cubeditor {

	static String input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		input = br.readLine();

		int result = kmp(input);

		StringBuffer sb = new StringBuffer();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static int kmp(String input) {
		int sLen = input.length();

		int max = 0;

		for (int i = 0; i < sLen; i++) {

			String p = input.substring(i, sLen);
			int[] table = new int[p.length()];

			int j = 0;
			for (int k = 1; k < table.length; k++) {

				while (j > 0 && p.charAt(k) != p.charAt(j)) {
					// 일치하는 접두사 크기에 한해서만 부분 문자열의 인덱스를 이동
					j = table[j - 1];
				}

				if (p.charAt(k) == p.charAt(j)) {
					table[k] = ++j;
					
					//접두사와 접미사가 같으면, 가장 긴 길이
					max = Math.max(max, j);
				}

			}

		}

		return max;
	}

}
