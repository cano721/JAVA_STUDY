package elwlahd555.baekjoon;

import java.util.Scanner;

public class baekjoon17144_미세먼지_안녕 {
	static int R, C, T;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int arr[][];

	static int air[];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		int result = 0;
		arr = new int[R][C];
		air = new int[2];
		int aircount = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {

				arr[i][j] = sc.nextInt();
				if (arr[i][j] == -1) {
					air[aircount++] = i;
				}
			}
		}

		for (int t = 0; t < T; t++) {
			int temp[][] = new int[R][C];

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j] >= 5) {
						int cnt = 0;
						for (int k = 0; k < 4; k++) {
							int x = i + dx[k];
							int y = j + dy[k];
							if (x >= 0 && x < R && y >= 0 && y < C && arr[x][y] != -1) {
								temp[x][y] += arr[i][j] / 5;
								cnt++;
							}
						}
						temp[i][j] += arr[i][j] - arr[i][j] / 5 * cnt;
					} else {
						temp[i][j] += arr[i][j];
					}
				}
			}
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					arr[i][j] = temp[i][j];
				}
			}
			rotation();

		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] > 0) {
					result += arr[i][j];
				}
			}
		}
		System.out.println(result);
	}

	private static void rotation() {
		// TODO Auto-generated method stub
		int first = 0;
		int temp = 0;
		for (int i = 1; i < C; i++) {
			temp = arr[air[0]][i];
			arr[air[0]][i] = first;
			first = temp;
		}
		for (int i = air[0] - 1; i >= 0; i--) {
			temp = arr[i][C - 1];
			arr[i][C - 1] = first;
			first = temp;

		}
		for (int i = C - 2; i >= 0; i--) {
			temp = arr[0][i];
			arr[0][i] = first;
			first = temp;
		}
		for (int i = 1; i < air[0]; i++) {
			temp = arr[i][0];
			arr[i][0] = first;
			first = temp;

		}
		first = 0;
		for (int i = 1; i < C; i++) {
			temp = arr[air[1]][i];
			arr[air[1]][i] = first;
			first = temp;
		}
		for (int i = air[1] + 1; i < R; i++) {
			temp = arr[i][C - 1];
			arr[i][C - 1] = first;
			first = temp;

		}
		for (int i = C - 2; i >= 0; i--) {
			temp = arr[R - 1][i];
			arr[R - 1][i] = first;
			first = temp;
		}
		for (int i = R - 2; i > air[1]; i--) {
			temp = arr[i][0];
			arr[i][0] = first;
			first = temp;

		}

	}

}