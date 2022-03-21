import java.io.*;
import java.util.*;

class Tree implements Comparable<Tree> {
	int x, y, z;

	public Tree(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int compareTo(Tree o) {
		return this.z - o.z;
	}
}

public class BOJ_16235 {
	static PriorityQueue<Tree> q;
	static Queue<Tree> alive, dead;
	static int[][] arr, map;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // n * n 크기 땅
		int m = Integer.parseInt(st.nextToken()); // m개 나무 구입
		int k = Integer.parseInt(st.nextToken()); // k년 지난 후

		arr = new int[n + 1][n + 1];
		map = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) { // A배열의 값
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5; // 첫 양분 상태
			}
		}
		q = new PriorityQueue<Tree>();

		for (int i = 0; i < m; i++) { // 나무의 정보를 나타내는 세 정수 x, y, z(나무 나이)
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			q.add(new Tree(x, y, z));
		}

		alive = new LinkedList<Tree>(); // 살아 있는 나무
		dead = new LinkedList<Tree>(); // 죽은 나무

		while (k-- > 0) {
			cal();
		}

		System.out.println(q.size());
	}

	static void cal() {
		// 봄
		while (!q.isEmpty()) {
			Tree tree = q.poll();
			int x = tree.x;
			int y = tree.y;
			int z = tree.z;

			if (z <= map[x][y]) {
				map[x][y] -= z;
				z += 1;
				alive.add(new Tree(x, y, z));
			} else {
				dead.add(new Tree(x, y, z));
			}
		}

		// 여름
		while (!dead.isEmpty()) {
			Tree tree = dead.poll();
			int x = tree.x;
			int y = tree.y;
			int z = tree.z;

			map[x][y] += z / 2;
		}

		// 가을
		while (!alive.isEmpty()) {
			Tree tree = alive.poll();
			int x = tree.x;
			int y = tree.y;
			int z = tree.z;

			if (z % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (0 < nx && nx <= n && 0 < ny && ny <= n) {
						q.add(new Tree(nx, ny, 1));
					}
				}
			}
			q.add(new Tree(x, y, z)); // 뭐징
		}

		// 겨울
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] += arr[i][j];
			}
		}
	}

}
