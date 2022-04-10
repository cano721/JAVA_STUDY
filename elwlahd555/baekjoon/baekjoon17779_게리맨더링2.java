package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon17779_게리맨더링2 {
	static int N, result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				solve(i, j, map);
			}
		}
		System.out.println(result);
	}

	static void solve(int x, int y, int[][] map) {
		int d1 = 1;
		int d2 = 1;

		// d1, d2 길이 1부터시작
		// d1반복문
		while (true) {
			d2 = 1;
			// x좌표가 조건에 안맞으면 while문 break
			if (0 <= x && x < x + d1 + d2 && x + d1 + d2 <= N - 1) {
				// d2반복문
				while (true) {
					// x좌표가 조건에 안맞으면 while문 break
					if (0 <= x && x < x + d1 + d2 && x + d1 + d2 <= N - 1) {
						// y좌표가 조건에 안맞으면 while문 break
						if (0 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N - 1) {
							// 구역 나누는 메소드
							divideArea(map, x, y, d1, d2);
							d2++;
						} else {
							break;
						}
					} else {
						break;
					}
				}
				d1++;
			} else {
				break;
			}
		}
	}

	// 구역 나누는 메소드
	static void divideArea(int[][] map, int x, int y, int d1, int d2) {
		//나누느 구역을 담을 이차원 배열 copyMap;
		int[][] divideMap = new int[N][N];

		//drawBoundary 경계 나누는 메소드, fillArea 구역 색칠하는 메소드
		divideMap = fillArea(drawBoundary(x, y, d1, d2), x, y, d1, d2);
		//색칠된 구역의 인구수 카운트 메소드
		countArea(divideMap, map);

	}
	
	//경계 나누는 메소드
	static int[][] drawBoundary(int x, int y, int d1, int d2) {
		int[][] map = new int[N][N];
		for (int i = 0; i <= d1; i++) {
			map[x + i][y - i] = 5;
			map[x + d2 + i][y + d2 - i] = 5;
		}
		for (int i = 0; i <= d2; i++) {
			map[x + i][y + i] = 5;
			map[x + d1 + i][y - d1 + i] = 5;
		}
		return map;
	}

	//구역 색칠하는 메소드
	static int[][] fillArea(int[][] copyMap, int x, int y, int d1, int d2) {
		// 5구역 마름모 색칠하기
		for (int i = 0; i < N; i++) {
			boolean flag = false;
			int start = 0;
			int end = 0;
			for (int j = 0; j < N; j++) {
				if (copyMap[i][j] == 5) {
					if (!flag) {
						start = j;
						flag = true;
					} else {
						end = j;
						break;
					}
				}
			}
			if (flag) {
				for (int j = start; j <= end; j++) {
					copyMap[i][j] = 5;
				}
			}
		}
		
		
		// 1,2,3,4 구역 색칠하기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (copyMap[r][c] != 5) {
					if (0 <= r && r < x + d1 && 0 <= c && c <= y) {
						copyMap[r][c] = 1;
					} else if (0 <= r && r <= x + d2 && y < c && c <= N - 1) {
						copyMap[r][c] = 2;
					} else if (x + d1 <= r && r <= N - 1 && 0 <= c && c < y - d1 + d2) {
						copyMap[r][c] = 3;
					} else if (x + d2 < r && r <= N - 1 && y - d1 + d2 <= c && c <= N - 1) {
						copyMap[r][c] = 4;
					}
				}
			}
		}
		return copyMap;
	}
	
	
	//색칠된 구역의 인구수 카운트 메소드
	static void countArea(int[][] copyMap, int[][] map) {
		// 구역의 인구 수를 저장할 일차원 배열 countArr
		int[] countArr = new int[6];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (copyMap[r][c] == 1) {
					countArr[1] += map[r][c];
				} else if (copyMap[r][c] == 2) {
					countArr[2] += map[r][c];
				} else if (copyMap[r][c] == 3) {
					countArr[3] += map[r][c];
				} else if (copyMap[r][c] == 4) {
					countArr[4] += map[r][c];
				} else if (copyMap[r][c] == 5) {
					countArr[5] += map[r][c];
				}
			}
		}
		
		//정렬
		Arrays.sort(countArr);
		
		//인구가 가장많은 선거구, 인구가 가장적은 선거구의 차이가 가장작은 것을 result에 저장
		result = Math.min(result, countArr[5] - countArr[1]);
	}
	
	
	//2차원배열 출력메소드
	static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}