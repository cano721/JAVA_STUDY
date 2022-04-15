import java.io.*;
import java.util.*;

public class Main {
	private static int r, c, k;
	private static int ans;
	private static int[][] map;
	private static int newR = 3, newC = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[101][101];
		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			if (map[r][c] == k)
				break;
			if (newR >= newC) {
				sortR();
			} else {
				sortC();
			}

			ans++;
			if (ans > 100) {
				System.out.println("-1");
				return;
			}
		}
		System.out.println(ans);
	}

	private static void sortR() {
		for (int k = 1; k <= newR; k++) {
			ArrayList<Integer> list = new ArrayList<>();
			int count[] = new int[101];

			for (int j = 1; j <= newC; j++) {
				count[map[k][j]]++;
			}

			boolean visited[] = new boolean[101];
			while (true) {
				int mincnt = 101;
				int minidx = 101;
				for (int i = 1; i <= 100; i++) {
					if (count[i] != 0 && count[i] < mincnt && !visited[i]) {
						mincnt = count[i];
						minidx = i;
					}
					if (count[i] != 0 && count[i] == mincnt && !visited[i]) {
						if (minidx > i) {
							mincnt = count[i];
							minidx = i;
						}
					}
				}
				if (mincnt == 101 || minidx == 101) {
					break;
				}

				list.add(minidx);
				list.add(count[minidx]);
				visited[minidx] = true;
			}
			for (int i = 0; i < list.size(); i++) {
				map[k][i + 1] = list.get(i);
			}
			newC = newC > list.size() ? newC : list.size();
			for (int i = list.size(); i < 100; i++) {
				map[k][i + 1] = 0;
			}
		}
	}

	private static void sortC() {
		for (int k = 1; k <= newC; k++) {
			ArrayList<Integer> list = new ArrayList<>();
			int count[] = new int[101];

			for (int j = 1; j <= newR; j++) {
				count[map[j][k]]++;
			}

			boolean visited[] = new boolean[101];
			while (true) {
				int mincnt = 101;
				int minidx = 101;
				for (int i = 1; i <= 100; i++) {
					if (count[i] != 0 && count[i] < mincnt && !visited[i]) {
						mincnt = count[i];
						minidx = i;
					}
					if (count[i] != 0 && count[i] == mincnt && !visited[i]) {
						if (minidx > i) {
							mincnt = count[i];
							minidx = i;
						}
					}
				}
				if (mincnt == 101 || minidx == 101) {
					break;
				}

				list.add(minidx);
				list.add(count[minidx]); 
				visited[minidx] = true;
			}
			for (int i = 0; i < list.size(); i++) {
				map[i + 1][k] = list.get(i);
			}
			newR = newR < list.size() ? list.size() : newR;
			for (int i = list.size(); i < 100; i++) {
				map[i + 1][k] = 0;
			}
		}
	}

}
