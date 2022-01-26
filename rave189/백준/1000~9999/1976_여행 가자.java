package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] unionFind;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		unionFind = new int[n];
		for (int i = 1; i < n; i++)
			unionFind[i] = i;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				if (st.nextToken().equals("1")) {
					int firstParent = find(i);
					int secondParent = find(j);
					unionFind[secondParent] = firstParent;
				}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int valid = find(unionFind[Integer.parseInt(st.nextToken()) - 1]);
		while (--m > 0) {
			if (valid != find(unionFind[Integer.parseInt(st.nextToken()) - 1])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	public static int find(int x) {
		if (x == unionFind[x])
			return x;
		return unionFind[x] = find(unionFind[x]);
	}
}