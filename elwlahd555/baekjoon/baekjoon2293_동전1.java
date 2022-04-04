package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2293_동전1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coin[] = new int[n];
		int answer[] = new int[k + 1];
		answer[0] = 1;
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			for (int j = coin[i]; j <= k; j++) {
				answer[j] += answer[j - coin[i]];
			}
		}

		System.out.println(answer[k]);
	}
}
