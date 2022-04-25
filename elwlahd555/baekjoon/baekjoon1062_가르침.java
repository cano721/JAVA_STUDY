package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon1062_가르침 {
	private static char arr[];
	private static int K, answer;
	private static char result[];
	private static String words[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) - 5;
		answer = 0;
		words = new String[N];
		LinkedList<Character> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine().replace("a", "").replace("n", "").replace("t", "").replace("i", "").replace("c",
					"");
			for (int j = 0; j < words[i].length(); j++) {
				if (!list.contains(words[i].charAt(j))) {
					list.add(words[i].charAt(j));
				}
			}
		}
		arr = new char[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		if (K == 0) {
			for (int i = 0; i < words.length; i++) {
				if (words[i].equals("")) {
					answer++;
				}
			}
		} else if (K == 21) {
			answer = N;
		} else if (K > 0) {
			result = new char[K];
			dfs(0, 0);
		}
		System.out.println(answer);
	}

	private static void dfs(int cnt, int start) {
		if (cnt == K) {
			int temp = 0;
			for (int i = 0; i < words.length; i++) {
				if (check(words[i])) {
					temp++;
				}
			}
			answer = Math.max(temp, answer);
			return;
		}
		for (int j = start; j < arr.length; j++) {
			result[cnt] = arr[j];
			dfs(cnt + 1, j + 1);
		}
	}

	private static boolean check(String word) {
		for (int i = 0; i < word.length(); i++) {
			boolean visited = false;
			for (int j = 0; j < result.length; j++) {
				if (word.charAt(i) == result[j]) {
					visited = true;
					break;
				}
			}
			if (!visited) {
				return false;
			}
		}
		return true;
	}
}
