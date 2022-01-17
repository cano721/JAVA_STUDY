import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class n2234_성곽 {
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dir = { 1, 2, 4, 8 };
	static int[][] map, wall;
	static int N, M, max = 0;
	static Deque<int[]> deque;
	static ArrayList<Integer> space =  new ArrayList(); 				// 방의 넓이 담기 
	static HashMap<Integer, Set<Integer>> side = new HashMap<>(); 		// 키: 방 넘버 , 값: 근접해 있는 방들의 넘버 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        wall = new int[M][N];
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
				wall[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        int roomNum = 1;
        int room = 0;
        deque = new ArrayDeque<int[]>();
        for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0) {
					bfs(i, j, roomNum++);
					room++;
				}
			}
		}
        System.out.println(room);	//방개수
        System.out.println(max);	//방넓이 최대값
        int sumMax = 0;
        for (int i = 0; i < room; i++) {
        	for(int j : side.get(i+1)) {
        		sumMax = Math.max(sumMax, space.get(j-1) + space.get(i)) ;
        	}
		}
        System.out.println(sumMax);	//두개인접방 넓이 최대값

	}
	private static void bfs(int x, int y, int roomNum) {
		int nx, ny, roomSize = 0;
		int[] pos = new int[2]; 						// 좌표값을 담을 배열
		deque.add(new int[] { x, y }); 					// num번 방 시작
		map[x][y] = roomNum; 							// 방번호 할당
		Set<Integer> set = new HashSet<>(); 			// 인접한 방의 번호를 담을 set(중복 방지)
		while (!deque.isEmpty()) {
			pos = deque.poll();
			x = pos[0];
			y = pos[1];
			roomSize++;
			for (int i = 0; i < 4; i++) { 				// 서, 북, 동, 남 순서
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= M || ny < 0 || ny >= N) // 범위 벗어나면
					continue;
				if (map[nx][ny] != 0 && map[nx][ny] != roomNum) { // 번호가 할당된 방이고(이미 들린 방이고), 그게 지금 방은 아닐때 
					set.add(map[nx][ny]); 				// 인접한 방 세트에 추가
					continue;
				}
				// dir = {1, 2, 4, 8}; 서, 북, 동, 남 순서 
				if ((wall[x][y] & dir[i]) == 0 			// * 비트 연산을 만족하고,(뚫려있음)
						&& map[nx][ny] == 0) { 			// 아직 방문 안했으면
					deque.add(new int[] { nx, ny }); 	// 탐색하기 위해 담고, 
					map[nx][ny] = roomNum; 				// 룸번호 할당
					continue;
				}
			}
		}
		side.put(roomNum, set); 						// 키: roomNum, 값: 인접한 방 세트
		space.add(roomSize); 							// 방 넓이 담기
		max = Math.max(max, roomSize); 					// 가장 넓은 방 넓이 갱신
	}
}
