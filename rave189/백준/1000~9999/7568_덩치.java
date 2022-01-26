package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[][] people = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			people[i][0] = Integer.parseInt(st.nextToken());
			people[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < n; i++) {
			int count = 1;
			for (int j = 0; j < n; j++)
				if (people[j][0] > people[i][0] && people[j][1] > people[i][1])
					count++;
			answer.append(count).append(' ');
		}
		System.out.println(answer);
	}
}