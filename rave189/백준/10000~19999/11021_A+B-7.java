package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for(int i=1; i<=testCase; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			answer.append(String.format("Case #%d: %d", i, Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken()))).append('\n');
		}
		System.out.println(answer);
	}
}