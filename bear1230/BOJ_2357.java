import java.util.*;
import java.io.*;

public class Main {
	static int[] min, max;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] num = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		min = new int[n * 4];
		max = new int[n * 4];
		minInit(num, 0, n, 1);
		maxInit(num, 0, n, 1);

		int[] minOut = new int[m];
		int[] maxOut = new int[m];

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(minfind(a, b, 1, 0, n) + " " + maxFind(a, b, 1, 0, n) + "\n");
		}

		System.out.println(sb);
	}

	static int maxFind(int a, int b, int node, int left, int right) {
		if (a > right || b < left)
			return 0;
		if (a <= left && b >= right)
			return max[node];
		int mid = (left + right) / 2;
		return Math.max(maxFind(a, b, node * 2, left, mid), maxFind(a, b, node * 2 + 1, mid + 1, right));
	}

	static int minfind(int a, int b, int node, int left, int right) {
		if (a > right || b < left)
			return Integer.MAX_VALUE;
		if (a <= left && b >= right)
			return min[node];
		int mid = (left + right) / 2;
		return Math.min(minfind(a, b, node * 2, left, mid), minfind(a, b, node * 2 + 1, mid + 1, right));
	}

	static int maxInit(int[] num, int left, int right, int node) {

		if (left == right)
			return max[node] = num[left];
		int mid = (left + right) / 2;
		return max[node] = Math.max(maxInit(num, left, mid, node * 2), maxInit(num, mid + 1, right, node * 2 + 1));
	}

	static int minInit(int[] num, int left, int right, int node) {
		if (left == right)
			return min[node] = num[left];
		int mid = (left + right) / 2;
		return min[node] = Math.min(minInit(num, left, mid, node * 2), minInit(num, mid + 1, right, node * 2 + 1));

	}

}