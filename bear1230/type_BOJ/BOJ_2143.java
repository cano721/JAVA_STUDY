import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int[] B = new int[m];
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		List<Long> sumA = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			long tmp = 0;
			for (int j = i; j < n; j++) {
				tmp += A[j];
				sumA.add(tmp);
			}
		}
		Collections.sort(sumA);

		List<Long> sumB = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			long tmp = 0;
			for (int j = i; j < m; j++) {
				tmp += B[j];
				sumB.add(tmp);
			}
		}
		Collections.sort(sumB);

		long answer = 0;
		for (int i = 0; i < sumA.size(); i++) {
			long target = T - sumA.get(i);
			answer += upperBound(sumB, target) - lowerBound(sumB, target);
		}

		System.out.println(answer);
	}

	private static int upperBound(List<Long> list, long target) {
		int start = 0;
		int end = list.size();

		while (start < end) {
			int mid = (start + end) / 2;
			if (list.get(mid) <= target)
				start = mid + 1;
			else
				end = mid;
		}

		return end;
	}

	private static int lowerBound(List<Long> list, long target) {
		int start = 0;
		int end = list.size();

		while (start < end) {
			int mid = (start + end) / 2;
			if (list.get(mid) >= target)
				end = mid;
			else
				start = mid + 1;
		}

		return end;
	}
}
