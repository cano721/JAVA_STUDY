package 전체유형문제풀이.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 
 * 1. 방문하지 않는 곳 방문하기
 * 2. 이전과 다음이동할 노드의 값과 차이 구하기
 * 3. 방문한 노드 list에 담기, 합 구하기
 * 4. 모든 방문이 끝나면 list에 담긴 값끼리 인구이동(* list.size() > 1) --> 인구조건에 맞는 값으로 변경
 * 5. 1~4번 과정을 반복하여 더이상 이동할 인구가 없을 때 순회횟수를 계산하고 이를 종료
 * 
 */

public class BJ16234_인구이동 {

	static int n, l, r, a[][];
	static boolean[][] visited;
	static ArrayList<countryNode> countryList;
	static int cntDay = 0;

	static int[] dx = { -1, 0, 1, 0 }; // 북,동,남,서
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		a = new int[n][n];

		//visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		System.out.println(getMoveBFS());

	}

	public static int getMoveBFS() {
		while (true) {
			boolean isMove = false;
			visited = new boolean[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					//방문하지 않는 나라이면
					if (!visited[i][j]) {
						//인구이동을 할 수 있는 나라 수 구하기
						int peopleSum = getCountryCntBFS(i, j);

						//인구를 이동할 나라가 존재한다면
						if (countryList.size() > 1) {
							//인구이동시키기
							getChangePepole(peopleSum);
							isMove = true;
						}

					}

				}
			}

			if (!isMove)
				return cntDay;

			cntDay++;
		}
	}

	//4. 모든 방문이 끝나면 list에 담긴 값끼리 인구이동(* list.size() > 1) --> 인구조건에 맞는 값으로 변경
	public static void getChangePepole(int peopleSum) {
		int changeCnt = peopleSum / countryList.size();

		for (countryNode temp : countryList) {
			a[temp.x][temp.y] = changeCnt;
		}

	}

	public static int getCountryCntBFS(int x, int y) {
		Queue<countryNode> q = new LinkedList<>();
		countryList = new ArrayList<>();

		q.offer(new countryNode(x, y));
		countryList.add(new countryNode(x, y));
		visited[x][y] = true;

		int sum = a[x][y];

		while (!q.isEmpty()) {

			countryNode loc = q.poll();

			//상하좌우방향 이동
			for (int i = 0; i < 4; i++) {
				int nx = loc.x + dx[i];
				int ny = loc.y + dy[i];

				//범위 내일 경우와 방문하지 않은 곳이라면!!!
				//2.dl전과 다음이동할 노드의 값과 차이 구하기
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
					int diff = Math.abs(a[loc.x][loc.y] - a[nx][ny]);

					//L~R범위 일 경우
					//3. 방문한 노드 list에 담기, 합 구하기
					if (l <= diff && r >= diff) {
						countryList.add(new countryNode(nx, ny));
						q.offer(new countryNode(nx, ny));

						sum += a[nx][ny];
						visited[nx][ny] = true;
					}
				}
			}

		}
		return sum;
	}

	public static class countryNode {
		int x;
		int y;

		countryNode(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
