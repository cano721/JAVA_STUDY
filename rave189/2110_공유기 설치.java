package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] nats = new int[n];
		for (int i = 0; i < n; i++)
			nats[i] = Integer.parseInt(br.readLine());
		Arrays.sort(nats);
		int left = 0, right = nats[n - 1];
		while (left <= right) {
			int mid = (left + right) / 2;
			int natCnt = 0;
			int cur = nats[0];
			for (int i = 0; i < n; i++) {
				if (cur <= nats[i]) {
					natCnt++;
					cur = nats[i] + mid;
				}
			}
			if (natCnt >= c)
				left = mid + 1;
			else
				right = mid - 1;
		}
		System.out.println(left - 1);
	}
}