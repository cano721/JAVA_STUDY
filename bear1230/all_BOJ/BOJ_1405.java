import java.util.*;
import java.io.*;

public class Main {
	static int[] nx = { 0, 0, 1, -1 };
	static int[] ny = { 1, -1, 0, 0 };
	static boolean[][] visited;
	static int N;
	static int cnt;
	static double ans = 0;
	static double[] per = new double[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		visited = new boolean[31][31];
		for (int i = 0; i < 4; i++) {
			per[i] = Double.parseDouble(st.nextToken()) / 100;
		}
		visited[14][14] = true;
		dfs(14, 14, 0, 1.00);
		System.out.println(ans);
	}

	public static void dfs(int x, int y, int cnt, double res) {
		if (cnt == N) {
			ans += res;
			return;
		}
		for (int i = 0; i < nx.length; i++) {
			if (per[i] == 0)
				continue;
			int mx = x + nx[i];
			int my = y + ny[i];
			if (!visited[mx][my]) {
				visited[mx][my] = true;
				dfs(mx, my, cnt + 1, per[i] * res);
				visited[mx][my] = false;
			}
		}

	}
}
