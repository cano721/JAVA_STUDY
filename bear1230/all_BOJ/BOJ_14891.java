import java.util.*;
import java.io.*;

public class Main {
	static int[] turn;
	static int[][] arr;
	static int R, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		arr = new int[5][9];

		for (int i = 1; i <= 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		R = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken()); 
			int dir = Integer.parseInt(st.nextToken()); 

			turn = new int[5];
			turn[num] = dir;
			right(num + 1, dir * -1);
			left(num - 1, dir * -1);
			rotate();
		}

		int cnt = 1;
		for (int i = 1; i <= 4; i++) {
			if (arr[i][0] == 1) {
				ans += cnt;
			}
			cnt *= 2;
		}

		System.out.println(ans);
	}

	private static void rotate() {
		for (int i = 1; i <= 4; i++) {

			if (turn[i] == -1) { // 반시계
				int fir = arr[i][0];
				for (int j = 1; j < 8; j++) {
					arr[i][j - 1] = arr[i][j];
				}
				arr[i][7] = fir;
			}

			else if (turn[i] == 1) { // 시계
				int last = arr[i][7];
				for (int j = 6; j >= 0; j--) {
					arr[i][j + 1] = arr[i][j];
				}
				arr[i][0] = last;
			}

		}

	}

	private static void right(int num, int dir) {
		if (num == 5) {
			return;
		}

		if (arr[num - 1][2] != arr[num][6]) {
			turn[num] = dir;
			right(num + 1, dir * -1);
		}

	}

	private static void left(int num, int dir) {
		if (num == 0) {
			return;
		}

		if (arr[num][2] != arr[num + 1][6]) {
			turn[num] = dir;
			left(num - 1, dir * -1);
		}

	}

}