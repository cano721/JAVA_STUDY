package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int E, S, M;
	static int limitE = 15, limitS = 28, limitM = 19;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int e = 1, s = 1, m = 1, answer = 1;
		while (!isValid(e, s, m)) {
			e = e + 1 > limitE ? 1 : e + 1;
			s = s + 1 > limitS ? 1 : s + 1;
			m = m + 1 > limitM ? 1 : m + 1;
			answer++;
		}
		System.out.println(answer);
	}

	public static boolean isValid(int e, int s, int m) {
		return E == e && S == s && M == m;
	}
}