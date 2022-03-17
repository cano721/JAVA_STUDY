import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2613 {

	/*
	 * 8 3
	 * 5 4 2 6 9 3 8 7
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 구슬 개수
		int m = Integer.parseInt(st.nextToken()); // 그룹 수

		int upperBound = 0, lowerBound = 0;
		arr = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			upperBound += arr[i];
			lowerBound = Math.max(lowerBound, arr[i]);
		}

		// 이분탐색
		int mid = 0;
		while (lowerBound <= upperBound) {
			mid = (lowerBound + upperBound) / 2;
			int cnt = isPossible(mid);
			if (cnt > m) {
				lowerBound = mid + 1;
			} else {
				upperBound = mid - 1;
			}
		}

		bw.write(lowerBound + "\n");
		int cnt = 0, sum = 0, i;
		for (i = 1; i <= n; i++) {
			sum += arr[i];
			if (sum > lowerBound) {
				m--;
				cnt = (cnt == 0 ? 1 : cnt);
				bw.write(cnt + " ");
				sum = arr[i];
				cnt = 0;
			}
			cnt++;

			// 1개씩은 무조건
			if (m == n - i + 1) {
				break;
			}
		}
		for (; i <= n; i++) {
			bw.write(cnt + " ");
			cnt = 1;
		}
		bw.flush();
	}

	static int n;
	static int[] arr;

	static int isPossible(int mid) {
		int start = 0, cnt = 1;
		for (int i = 1; i <= n; i++) {
			start += arr[i];
			if (start > mid) {
				start = arr[i];
				cnt++;
			}
		}
		return cnt;
	}
}
