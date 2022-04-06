import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static boolean[] visited;
	static int ans = Integer.MAX_VALUE;
	static ArrayList<Node> house;
	static ArrayList<Node> chicken;

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] line = new int[n][n];
		house = new ArrayList<>();
		chicken = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				line[i][j] = Integer.parseInt(st.nextToken());
				if (line[i][j] == 1)
					house.add(new Node(i, j));
				else if (line[i][j] == 2)
					chicken.add(new Node(i, j));
			}
		}

		visited = new boolean[chicken.size()];
		comb(0, m);

		System.out.println(ans);
	}

	public static void calc() {
		int tmp = 0;
		for (int i = 0; i < house.size(); i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < chicken.size(); j++) {
				if (visited[j]) {
					int chicken_len = Math.abs(chicken.get(j).x - house.get(i).x)
							+ Math.abs(chicken.get(j).y - house.get(i).y);
					min = Math.min(min, chicken_len);
				}
			}
			tmp += min;
		}
		ans = Math.min(ans, tmp);
	}

	public static void comb(int start, int x) {
		if (x == 0) {
			calc();
			return;
		} else {
			for (int i = start; i < chicken.size(); i++) {
				visited[i] = true;
				comb(i + 1, x - 1);
				visited[i] = false;
			}
		}
	}
}
