package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 선생님이 학생들에게 단어를 가르치려고 한다.
 * 시간이 없는 선생님은 K개의 단어만 가르치려고 한다.
 * 이러면 학생들은 K개의 단어 이하로 구성된 단어만 읽을 수 있다.
 * 어떤 단어를 가르쳐야 학생들이 가장 많은 단어를 읽을 수 있는지 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int answer = 0, alphabetSize = 26;
	static int[] words;

	/**
	 * 비트마스킹 문제
	 * 분류 안봤으면 비트마스킹인지 한참이 지나서야 알았을 듯...
	 * 비트마스킹인걸 아니까 금방 풀음
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		words = new int[n];
		for (int i = 0; i < n; i++) {
			for (char ch : br.readLine().toCharArray()) {
				words[i] |= 1 << (ch - 'a');
			}
		}
		bruteforce(0, k, 0);
		System.out.println(answer);
	}

	public static void bruteforce(int cur, int depth, int selected) {
		if (depth == 0) {
			answer = Math.max(answer, calc(selected));
			return;
		}
		for (int i = cur; i < alphabetSize; i++)
			bruteforce(i + 1, depth - 1, selected | 1 << i);
	}

	public static int calc(int selected) {
		int count = 0;
		for (int word : words)
			if ((word & selected) == word)
				count++;
		return count;
	}
}
