package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Shark {
	int r; // 행 위치
	int c; // 열 위치
	int s; // 속력
	int d; // 방향
	int z; // 크기

	Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}
public class d220409_낚시터 {
	static int R, C, M;
	static Shark[][] map;
	static int answer = 0;
	public static int dx[] = {-1, 0, 1, 0}; //상 좌 하 우 순 
	public static int dy[] = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//상어 낚시 격자판 만들고, 각 위치에 상어 클래스로 만든 인스턴스 저장 
		map = new Shark[R][C];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()); // 행 위치 
			int c = Integer.parseInt(st.nextToken()); // 열 위치 
			int s = Integer.parseInt(st.nextToken()); // 속력 
			int d = Integer.parseInt(st.nextToken()); // 이동 방향 
			int z = Integer.parseInt(st.nextToken()); // 크기 

			//방향 쉽게 바꾸기위해 입력받은 상하좌우(1 2 3 4) -> 상좌하우(0 1 2 3)로 변경 
			if(d == 1)
				d = 0;
			else if(d == 4)
				d = 1;

			map[r-1][c-1] = new Shark(r-1, c-1, s, d, z); //격자판에 상어 저장 
		}
	    
		for(int col = 0; col < C; col++) { // 열의 끝까지 반복 
			// 1. 낚시왕 이동 
			for(int row = 0; row < R; row++) {
				if(map[row][col] != null) { 
					answer += map[row][col].z; // 2. 가장 가까운 상어 크기 정답 변수에 저장 
					map[row][col] = null; // map에서 상어 없애기 
					break;
				}
			}
	
			// 3. 상어 이동 
			Queue<Shark> queue = new LinkedList<>(); 
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] != null) { // 현재 map에 있는 상어들 큐에 추가 
						queue.add(new Shark(i, j, map[i][j].s, map[i][j].d, map[i][j].z));
					}
				}
			}
	
			map = new Shark[R][C]; // 새로운 낚시판 만들기위해 배열 초기화 
	
			// 모든 상어 한마리씩 꺼내서 이동 
			while(!queue.isEmpty()) {
				Shark sm = queue.poll();
	            
				// 속력만큼 상어 이동 시키기 
				int speed = sm.s; // 시간초과로 최소한의 이동을 위해 나머지 연산
				if(sm.d == 0 || sm.d == 2) //상 하
					speed %= (R -1) * 2; 
				else if(sm.d == 1 || sm.d == 3) //좌 우
					speed %= (C -1) * 2;
				
				for(int s = 0; s < speed; s++) {
					// 현재 r, c에 방향에 맞게 1칸씩 추가하며 위치 이동 
					int newR = sm.r + dx[sm.d]; 
					int newC = sm.c + dy[sm.d];
	
					// 이동할 새로운 위치가 범위를 벗어나 벽에 부딪히면 
					if(newR < 0 || newR >= R || newC < 0 || newC >= C) { 
						sm.r -= dx[sm.d]; // 다시 값 돌려주고 
						sm.c -= dy[sm.d];
						sm.d = (sm.d + 2) % 4; // 방향 반대로 
						continue;
					}
	
					// 위치 벗어나지 않을때는 새로운 위치로 이동 
					sm.r = newR; 
					sm.c = newC;
				}
	
				// 4. 새로운 위치가 빈 공간인지 이미 상어가 있는지 확인
				if(map[sm.r][sm.c] != null) { // 이미 상어가 있다면 두 상어 크기 비교 
					if(map[sm.r][sm.c].z < sm.z) { // 기존 상어보다 현재 상어가 크다면 
						map[sm.r][sm.c] = new Shark(sm.r, sm.c, sm.s, sm.d, sm.z); // 현재 상어 넣어줌 
					} 
				} else { // 없다면 현재 상어 바로 넣어줌 
					map[sm.r][sm.c] = new Shark(sm.r, sm.c, sm.s, sm.d, sm.z);
				}
			}
		} // 이동 for문 끝 
	
		System.out.println(answer);
	}

}
