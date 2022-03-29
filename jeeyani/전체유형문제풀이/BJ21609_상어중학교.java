package 전체유형문제풀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * 구현 bfs
 * 
 * 1. 가장 그룹수가 큰 그룹찾기
 * 	1-2. 그룹수가 같으면 0갯수가 많은 것 찾기
 * 	1-3. 1-2번도 동일하면 행이 큰 것 찾기  => 행도 동일하다면 열이 큰거 찾기
 * 
 * 2. 찾은 그룹은 삭제하기 => 빈칸: -2
 * 
 * 3. 중력작용 : 
 * 		3-2. 세로만 움직이고 제일 밑부터 빈칸수를 카운팅하기
 * 		3-3. -1일때 카운팅 초기화
 * 		3-4. 숫자를 만나면 카운팅 한 만큼 x좌표 변경
 * 
 * 4. 반시계 방향 회전
 * 			x' = n-1-y
 * 			y' = x
 * 
 * */

public class BJ21609_상어중학교 {

	static int n, m, result = 0;
	static int[][] map;
	static boolean[][] visited;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {

			int score = getBlockGroup();

			if (score == 0) break; //더 이상 그룹이 없어 점수계산을 못하면 종료
			result += score;

			getGravity();

			getRotation();

			getGravity();

		}

		System.out.println(result);

	}

	//반시계방향 회전 규칙 찾기
	/*
	 * x' = n-1-y
	 * y' = x
	 * 
	 * */
	private static void getRotation() {
		int[][] mapCopy = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mapCopy[n - 1 - j][i] = map[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = mapCopy[i][j];
			}
		}

	}

	//중력작용
	/*
	 * 세로만 움직임
	 * 제일 밑부터 빈칸수를 카운팅하기
	 * -1일때 카운팅 초기화
	 * 숫자를 만나면 카운팅 한 만큼 x좌표 변경
	 * 
	 */
	private static void getGravity() {
		for (int i = 0; i < n; i++) {
			int blank = 0;
			for (int j = n - 1; j >= 0; j--) {
				//빈칸일 경우
				if (map[j][i] == -2) {
					blank++;
				} else if (map[j][i] == -1) {
					blank = 0;
				} else {
					if (blank != 0) {
						map[j + blank][i] = map[j][i];
						map[j][i] = -2;
					}
				}

			}
		}

	}

	//블럭그룹찾기
	private static int getBlockGroup() {
		int score = 0;
		int maxRainbowCnt = 0;
		List<Block> maxGroup = new ArrayList<>();

		//컬러갯수만큼 탐색하기
		for (int k = 1; k <= m; k++) {
			visited = new boolean[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					if (!visited[i][j] && map[i][j] == k) {
						Queue<Block> q = new LinkedList<>();
						List<Block> groupList = new ArrayList<>();
						int rainbow = 0; //무지개블록갯수 체크

						q.offer(new Block(i, j));
						groupList.add(new Block(i, j)); //기준점의 좌표
						visited[i][j] = true;

						while (!q.isEmpty()) {
							Block block = q.poll();
							int x = block.x;
							int y = block.y;

							for (int d = 0; d < 4; d++) {
								int nx = x + dx[d];
								int ny = y + dy[d];

								//공간을 벗어난 경우 무시
								if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
								
								if (!visited[nx][ny] && (map[nx][ny] == k || map[nx][ny] == 0)){
									q.offer(new Block(nx, ny));
									groupList.add(new Block(nx, ny));
									visited[nx][ny] = true;
									
									if(map[nx][ny] == 0) {
										rainbow++;
									}
								}

							}

						}
						int maxCnt = maxGroup.size();
						int groupCnt = groupList.size();
						//더 큰 그룹사이즈로 갱신
						/*
						 * 1. max값 보다 현재 구한 그룹의 수가 더 큰 경우
						 * 2. max값이랑 구한 그룹의 수가 같을때, 0(레인보우블럭)이 더 많은 경우
						 * 3. 1번,  2번 의 경우모두 동일한 경우, 행/열의 크기가 큰 경우
						 * */
						if(maxCnt < groupCnt || (maxCnt == groupCnt && maxRainbowCnt < rainbow)
						|| (maxCnt == groupCnt && maxRainbowCnt == rainbow && maxGroup.get(0).x*100 + maxGroup.get(0).y < groupList.get(0).x*100 + groupList.get(0).y)) {
							
							//maxGroup.addAll(groupList);
							maxGroup = groupList.stream().collect(Collectors.toList());
							maxRainbowCnt = rainbow;
						}
						
					}
				}
			}

		}
		
		if(maxGroup.size() >=2) {
			score = maxGroup.size()*maxGroup.size();
			
			for (int i = 0; i < maxGroup.size(); i++) {
				int x = maxGroup.get(i).x;
				int y = maxGroup.get(i).y;
				
				//빈칸은 -2로 처리
				map[x][y] = -2;
			}
		}

		return score;
	}

	//블럭위치
	public static class Block {
		int x;
		int y;

		public Block(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
