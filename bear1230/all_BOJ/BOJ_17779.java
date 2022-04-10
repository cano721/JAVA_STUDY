import java.io.*;
import java.util.*;

public class Main {
	static int[][] map, area;
	static int[] population;
	static int ans, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		ans = Integer.MAX_VALUE;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int L = 1; L <= N; L++) {
					for (int R = 1; R <= N; R++) {
						if (y - L <= 0 || x + L + R > N || y + R > N)
							continue;

						setArea(x, y, L, R);
						ans = Math.min(ans, getDiff());
					}
				}
			}
		}
		System.out.println(ans);
	}

	private static void setArea(int x, int y, int L, int R) {
		population = new int[6];
		area = new int[N + 1][N + 1];

		int left = 0, right = 0;
		boolean turnLeft = true, turnRight = true;
		for (int row = x; row <= x + L + R; row++) {
			for (int col = y + left; col <= y + right; col++) {
				area[row][col] = 5;
				population[5] += map[row][col];
			}

			if (left == -L)
				turnLeft = !turnLeft;
			if (right == R)
				turnRight = !turnRight;

			if (turnLeft)
				left--;
			else
				left++;

			if (turnRight)
				right++;
			else
				right--;
		}

		for (int i = 1; i < x + L; i++) {
			for (int j = 1; j <= y; j++) {
				if (area[i][j] != 5) {
					population[1] += map[i][j];
				}
			}
		}

		for (int i = 1; i <= x + R; i++) {
			for (int j = y + 1; j <= N; j++) {
				if (area[i][j] != 5) {
					population[2] += map[i][j];
				}
			}
		}
		for (int i = x + L; i <= N; i++) {
			for (int j = 1; j < y - L + R; j++) {
				if (area[i][j] != 5) {
					population[3] += map[i][j];
				}
			}
		}
		for (int i = x + R + 1; i <= N; i++) {
			for (int j = y - L + R; j <= N; j++) {
				if (area[i][j] != 5) {
					population[4] += map[i][j];
				}
			}
		}
	}

	private static int getDiff() {
		int maxVal = Integer.MIN_VALUE;
		int minVal = Integer.MAX_VALUE;

		for (int i = 1; i <= 5; i++) {
			maxVal = Math.max(maxVal, population[i]);
			minVal = Math.min(minVal, population[i]);
		}
		return maxVal - minVal;
	}
}