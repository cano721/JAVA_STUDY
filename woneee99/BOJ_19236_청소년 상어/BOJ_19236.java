import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// fish 돌 때 x, y 처리 
class Teen {
	int num; // 물고기 번호
	int loc; // 방향
	int alive;

	public Teen(int num, int loc, int alive) {
		this.num = num;
		this.loc = loc;
		this.alive = alive;
	}

}

public class BOJ_19236 {
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static Teen[] teen;
	static int[][] arr;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		teen = new Teen[17];
		arr = new int[4][4];

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int n = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken()) - 1;
				teen[n] = new Teen(n, l, 1);
				arr[i][j] = n;
			}
		}

		int die = arr[0][0];
		int l = teen[die].loc;
		teen[die].alive = 0;
		arr[0][0] = -1;

		shark(0, 0, l, 0);

		System.out.println(answer);
	}

	static void shark(int x, int y, int d, int now) {
		answer = Math.max(answer, now);

		int[][] brr = new int[4][4];
		Teen[] newteen = new Teen[17];

		copy(arr, brr, newteen, teen);

		fish();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[d] * i;
			int ny = y + dy[d] * i;

			if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
				continue;

			if (arr[nx][ny] != 0) { // 물고기 있는 경우
				int renew = arr[nx][ny];
				int nd = teen[renew].loc;
				arr[x][y] = 0;
				arr[nx][ny] = -1;
				teen[renew].alive = 0;

				shark(nx, ny, nd, renew + now);

				teen[renew].alive = 1;
				arr[nx][ny] = renew;
				arr[x][y] = -1;
			}
		}
		copy(brr, arr, teen, newteen);
	}

	static void fish() {
		for (int i = 1; i <= 16; i++) {
			if (teen[i].alive == -1)
				continue;

			int cnt = 0;
			int l = teen[i].loc;

			while (true) {
				if (cnt < 8)
					break;

				cnt++;
			}

		}
	}

	static void copy(int[][] arr, int[][] brr, Teen[] newteen, Teen[] teen) {
		for (int i = 0; i < brr.length; i++) {
			System.arraycopy(arr[i], 0, brr[i], 0, brr.length);
		}
		for (int i = 1; i <= 16; i++) {
			newteen[i] = new Teen(teen[i].num, teen[i].loc, teen[i].alive);
		}
	}
}
