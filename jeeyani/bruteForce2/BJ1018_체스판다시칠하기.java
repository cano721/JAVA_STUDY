package bruteForce2;

import java.util.Scanner;

public class BJ1018_체스판다시칠하기 {

	public static boolean[][] arr;
	public static int min = 64;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int M = in.nextInt();

		arr = new boolean[N][M];

		in.nextLine();

		// 배열 입력 
		for (int i = 0; i < N; i++) {
			String str = in.nextLine().trim();

			for (int j = 0; j < M; j++) {
				if (str.charAt(j) == 'W') {
					arr[i][j] = true; // W일 때는 true 
				} else {
					arr[i][j] = false; // B일 때는 false
				}

			}
		}

		for (int i = 0; i < N-7; i++) {
			for (int j = 0; j < M-7; j++) {
				chess(i, j);
			}
		}
		System.out.println(min);
	}

	public static void chess(int x, int y) {

		int count = 0;

		boolean boardNew = arr[x][y]; // 첫 번째 칸의 색 

		for (int i = x; i < x + 8; i++) {
			for (int j = y; j < y + 8; j++) {

				// 올바른 색이 아닐경우 count 1 증가 
				if (arr[i][j] != boardNew) {
					count++;
				}
				/*
				 * 색을 계속 바꿔서 arr[i][j]와 비교하기
				 */
				boardNew = (!boardNew);
			}

			boardNew = !boardNew;
		}

	
		//첫번째 시작 색 기준으로의 결과와 첫번째 색 반대색일때의 기준값 비교
		count = Math.min(count, 64 - count);
		min = Math.min(min, count);

	}

}
