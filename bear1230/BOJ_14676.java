import java.util.*;
import java.io.*;

public class Main {

	static List<Integer> list[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++)
			list[i] = new ArrayList<>();

		int[] indegree = new int[n + 1];
		int[] cnt = new int[n + 1];
		boolean answer = true;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			indegree[b]++;
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == 1) {
				if (indegree[b] != 0) {
					answer = false;
					continue;
				}
				cnt[b]++;

				if (cnt[b] == 1) {
					for (int next : list[b]) {
						indegree[next]--;
					}
				}
			} else {
				if (cnt[b] <= 0) {
					answer = false;
					continue;
				}
				cnt[b]--;
				if (cnt[b] == 0) {
					for (int next : list[b]) {
						indegree[next]++;
					}
				}
			}
		}
		if (answer)
			System.out.println("King-God-Emperor");
		else
			System.out.println("Lier!");
	}

}