package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 로봇이 있다.
 * 로봇은 상하좌우를 임의로 선택하여 움직일 수 있다.
 * 로봇이 같은 곳을 한 번만 방문하는 경로를 단순하다고 한다.
 * 로봇이 각 방향으로 움직일 확률이 주어질 때, 단순하게 움직일 확률을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static double[] percentages = new double[dx.length];
	static final int SIZE = 30;
	static double[][] map = new double[SIZE][SIZE];
	static boolean[][] visited = new boolean[SIZE][SIZE];
	static double answer = 0.0;

	/**
	 * 처음에 문제를 같은 방향으로 한 번만 가야되는 줄 알고 bfs로 구현했음.
	 * 분류보고 질문보니 방향체크가 아니라 방문체크가 필요하다는 걸 알게됨
	 * 분류보고 dfs로 바꿔서 맞춤
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < dx.length; i++)
			percentages[i] = Integer.parseInt(st.nextToken()) / 100.0;
		dfs(n, SIZE / 2, SIZE / 2, 1.0);
		System.out.println(answer);
	}

	public static void dfs(int depth, int x, int y, double result) {
		if (visited[x][y])
			return;
		if (depth == 0) {
			answer += result;
			return;
		}
		visited[x][y] = true;
		for (int i = 0; i < dx.length; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			dfs(depth - 1, nextX, nextY, result * percentages[i]);
		}
		visited[x][y] = false;
	}
}