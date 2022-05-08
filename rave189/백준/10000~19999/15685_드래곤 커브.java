package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static final int SIZE = 100;
	static boolean[][] map = new boolean[SIZE + 1][SIZE + 1];
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			makeDragonCurve(x, y, d, g);
		}
		System.out.println(getSurroundCnt());
	}

	public static void makeDragonCurve(int x, int y, int d, int g) {
		ArrayList<DragonCurve> dragonCurves = new ArrayList<>();
		int nextX = x + dx[d];
		int nextY = y + dy[d];
		dragonCurves.add(new DragonCurve(x, y, nextX, nextY, d));
		map[x][y] = true;
		map[nextX][nextY] = true;
		for (int curGeneration = 1; curGeneration <= g; curGeneration++) {
			int idx = dragonCurves.size();
			while (--idx >= 0) {
				DragonCurve curCurve = dragonCurves.get(idx);
				DragonCurve lastCurve = dragonCurves.get(dragonCurves.size() - 1);
				int sx = lastCurve.ex, sy = lastCurve.ey;
				int nextDirection = rotateClockWise(curCurve.d);
				int ex = sx + dx[nextDirection], ey = sy + dy[nextDirection];
				dragonCurves.add(new DragonCurve(sx, sy, ex, ey, nextDirection));
				map[sx][sy] = true;
				map[ex][ey] = true;
			}
		}
	}

	public static int rotateClockWise(int d) {
		return (d + 1) % dx.length;
	}

	public static int getSurroundCnt() {
		int count = 0;
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1])
					count++;
		return count;
	}
}

class DragonCurve {
	int sx, sy, ex, ey, d;

	public DragonCurve(int sx, int sy, int ex, int ey, int d) {
		this.sx = sx;
		this.sy = sy;
		this.ex = ex;
		this.ey = ey;
		this.d = d;
	}
}