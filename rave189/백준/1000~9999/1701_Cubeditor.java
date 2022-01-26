package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문자열 s가 주어진다.
 * 이 s에서 두 번 이상 나오는 부분 문자열 중 가장 긴 문자열의 길이를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int max = 0;

	/**
	 * kmp 알고리즘을 활용한다.
	 * kmp 알고리즘에서 접두사와 접미사가 같은 길이를 구하기 때문에
	 * 두 번 이상 나오는 부분 문자열을 구할 수 있다.
	 * 따라서 구한 table의 값 중 최대값이 곧 가장 긴 문자열의 길이가 된다.
	 * 이때, kmp에서 table을 구할 때에는 s의 접두사와 접미사가 같은지를 구하기 때문에
	 * 중간부터 시작하는 부분 문자열이 가장 긴 경우를 탐색하지 못한다.
	 * 따라서 substring을 사용하여 모든 부분 문자열에 대해 table을 구하고 이 값들 중에서 최댓값을 찾는다.
	 * 
	 * kmp아니고 문자열만으로도 풀 수 있는 것 같긴함.
	 * 생각 좀 많이 해봐야댈듯?
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++)
			findMax(s.substring(i));
		System.out.println(max);
	}

	public static void findMax(String s) {
		int[] table = new int[s.length()];
		for (int i = 1, j = 0; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != s.charAt(j))
				j = table[j - 1];
			if (s.charAt(i) == s.charAt(j)) {
				table[i] = ++j;
				max = Math.max(max, table[i]);
			}
		}
	}
}