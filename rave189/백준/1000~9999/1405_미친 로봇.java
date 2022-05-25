package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * �κ��� �ִ�.
 * �κ��� �����¿츦 ���Ƿ� �����Ͽ� ������ �� �ִ�.
 * �κ��� ���� ���� �� ���� �湮�ϴ� ��θ� �ܼ��ϴٰ� �Ѵ�.
 * �κ��� �� �������� ������ Ȯ���� �־��� ��, �ܼ��ϰ� ������ Ȯ���� ���ϴ� ����
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
	 * ó���� ������ ���� �������� �� ���� ���ߵǴ� �� �˰� bfs�� ��������.
	 * �з����� �������� ����üũ�� �ƴ϶� �湮üũ�� �ʿ��ϴٴ� �� �˰Ե�
	 * �з����� dfs�� �ٲ㼭 ����
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