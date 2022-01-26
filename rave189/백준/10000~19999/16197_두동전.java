package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static char[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Queue<Point> coin = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'o')
					coin.add(new Point(i, j));
			}
		}
		System.out.println(bfs());
	}

	public static int bfs() {
		int count = 0;
		while (!coin.isEmpty()) {
			count++;
			int size = coin.size();
			while ((size -= 2) >= 0) {
				Point coin1 = coin.poll();
				Point coin2 = coin.poll();
				for (int i = 0; i < dx.length; i++) {
					int dropCoinCnt = 0;
					int firstCoinX = coin1.x + dx[i];
					int firstCoinY = coin1.y + dy[i];
					int secondCoinX = coin2.x + dx[i];
					int secondCoinY = coin2.y + dy[i];
					try {
						if (map[firstCoinX][firstCoinY] == '#')
							coin.add(coin1);
						else
							coin.add(new Point(firstCoinX, firstCoinY));
					} catch (ArrayIndexOutOfBoundsException e) {
						dropCoinCnt++;
					}
					try {
						if (map[secondCoinX][secondCoinY] == '#')
							coin.add(coin2);
						else
							coin.add(new Point(secondCoinX, secondCoinY));
					} catch (ArrayIndexOutOfBoundsException e) {
						dropCoinCnt++;
					}
					if (dropCoinCnt == 1)
						return count;
				}
			}
			if (count >= 10)
				return -1;
		}
		return -1;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}