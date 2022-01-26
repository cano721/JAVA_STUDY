package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int s = 1048576;
	static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		tree = new int[s * 2];
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			if (command == 1) {
				int index = query(1, s, 1, a);
				update(index, -1);
				answer.append(index).append('\n');
			} else
				update(a, Integer.parseInt(st.nextToken()));
		}
		System.out.print(answer);
	}

	public static void update(int a, int b) {
		int index = s + a - 1;
		tree[index] += b;
		while ((index /= 2) > 0) {
			tree[index] = tree[index * 2] + tree[index * 2 + 1];
		}
	}

	public static int query(int left, int right, int node, int target) {
		if (target > tree[node])
			return 0;
		if (left == right)
			return left;
		int mid = (left + right) / 2;
		int leftResult = query(left, mid, node * 2, target);
		if (leftResult != 0)
			return leftResult;
		return query(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
	}
}