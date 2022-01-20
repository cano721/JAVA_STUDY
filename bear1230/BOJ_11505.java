import java.io.*;
import java.util.*;

public class Main {
    static int mod = 1000000007;
	static int size;
	static long tree[], ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		size = 1;
		while (size < n) {
			size *= 2;
		}

		tree = new long[size * 2];

		for (int i = 1; i <= n; i++) {
			int idx = size + i - 1;
			tree[idx] = Long.parseLong(br.readLine());
		}

		for (int i = size - 1; i > 0; i--) {
			tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % mod;
		}

		ans = 0;
		for (int i = 1; i <= m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				init(b, c);
			} else {
				ans = sum(b, (int) c);
				System.out.println(ans);
			}
		}		


	}

	static long sum(int b, int c) {
		long ans = 1;
		int s = b + size - 1;
		int e = c + size - 1;

		while (s <= e) {
			if (s % 2 == 1) {
				ans = (ans * tree[s]) % mod;
				s++;
			}
			if (e % 2 == 0) {
				ans = (ans * tree[e]) % mod;
				e--;
			}
			s /= 2;
			e /= 2;
		}

		return ans;
	}

	static void init(int b, long c) {
		int idx = b + size - 1;
		tree[idx] = c;
		idx /= 2;

		while (idx > 0) {
			tree[idx] = (tree[idx * 2] * tree[idx * 2 + 1]) % mod;
			idx /= 2;
		}

	}

}