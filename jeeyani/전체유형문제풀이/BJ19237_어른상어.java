package 전체유형문제풀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 1. sharkInfo[x][y][3]
 * 		sharkInfo[x][y][0] : 해당 위치의 상어번호
 * 		sharkInfo[x][y][1] : 냄새의 당사자
 * 		sharkInfo[x][y][3] : 해당 위치의 냄새의 강도
 * 
 * 2. shark : 상어좌표, 방향, 우선순위 저장
 * 
 * https://www.youtube.com/watch?v=6TlAeqK3zXA
 * */


public class BJ19237_어른상어 {

	static int n, m, k, res;
    static int[][][] sharkInfo = new int[20][20][3]; 
    static shark[] shark = new shark[400];
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		//상어 위치정보와 냄새정보 입력받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				sharkInfo[i][j][0] = Integer.parseInt(st.nextToken());//인덱스 맞추기
				
				//상어가 존재할 경우, 정보 갱신
				if(sharkInfo[i][j][0] != 0) {
					int sharkNum = sharkInfo[i][j][0]-1;
					
					shark[sharkNum] = new shark(i, j, 0, new int[4][4]);

					sharkInfo[i][j][1] = sharkInfo[i][j][0];
					sharkInfo[i][j][2] = k;
					
				}
			}
		}
		
		//각 상어마다의 시작 방향 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			shark[i].dir = Integer.parseInt(st.nextToken())-1;
		}
		
		//상어마다의 우선순위 방향 입력받기
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				shark[i].priority[j][0] = Integer.parseInt(st.nextToken())-1;
				shark[i].priority[j][1] = Integer.parseInt(st.nextToken())-1;
				shark[i].priority[j][2] = Integer.parseInt(st.nextToken())-1;
				shark[i].priority[j][3] = Integer.parseInt(st.nextToken())-1;
			}
		}
		
		res = -1;
		getMoveSharks();
		
		System.out.println(res);
		
	}

	public static void getMoveSharks() {
		int time = 0;
		int killedShark = 0;
		
		//최대 이동횟수 1000초
		while(time <= 1000) {
			
			time++;
			
			//이동할때마다 복사하여 상어이동 정보를 저장
			int[][][] copyShark = new int[n][n][3];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					copyShark[i][j][0] = sharkInfo[i][j][0];
					copyShark[i][j][2] = sharkInfo[i][j][2];
					
					//아직 냄새가 있는 경우
					if(copyShark[i][j][2] > 0) {
						copyShark[i][j][2]--;
					}
					//냄새가 줄어들어도 아직 냄새가 있는 경우
					if (copyShark[i][j][2] > 0){
						copyShark[i][j][1] = sharkInfo[i][j][1];
					}
				}
			}
			//1번상어 부터 이동
			for (int i = 0; i < m; i++) {
				//현재 상어 정보
				int sharkX = shark[i].x;
				int sharkY = shark[i].y;
				int sharkDir = shark[i].dir;
				
				//이미 상어가 죽어있는 경우 무시
				if(sharkX == -1) continue;
				
				//상어가 움직일 수 있는지 없는지 체크
				boolean isMove = false;
				
				for (int j = 0; j < 4; j++) {
					//이동할 좌표와 방향에 따른 우선순위 갱신
					int nd = shark[i].priority[sharkDir][j];
					int nx = sharkX + dx[nd];
					int ny = sharkY + dy[nd];
					
					
					//범위를 벗어나거나, 빈칸이 아닐 경우 제외
					if(nx < 0 || nx >= n || ny < 0 || ny>=n || sharkInfo[nx][ny][2] != 0) continue;
					
					isMove = true;
					copyShark[sharkX][sharkY][0] = 0;
						
					/*상어죽을지 안죽을지 확인하기*/
					
					//빈칸인 경우, 움직인 상어의 정보를 갱신
					if(copyShark[nx][ny][0] == 0) {
						copyShark[nx][ny][0] = i+1;
						copyShark[nx][ny][1] = i+1;
						copyShark[nx][ny][2] = k;
						
						//이동한 정보로 갱신
						shark[i].x = nx;
						shark[i].y = ny;
						shark[i].dir = nd;
					}
					
					//움직일 예정인 장소에 상어가 있는 경우
					//1번부터 상어를 움직이기 때문에 1번 이후에 오는 상어들은 죽음!
					else {
						//현재위치의 상어죽이기
						killedShark++;
						shark[i].x = -1;
					}
					break;
				}
				
				
				if(!isMove) {
					for (int j = 0; j < 4; j++) {
						//이동할 좌표와 방향에 따른 우선순위 갱신
						int nd = shark[i].priority[sharkDir][j];
						int nx = sharkX + dx[nd];
						int ny = sharkY + dy[nd];

						
						//범위를 벗어나거나, 빈칸이 아닐 경우, 냄새주인의 당사자가 아닐 경우 제외
						if(nx < 0 || nx >= n || ny < 0 || ny>=n) continue;
						if(sharkInfo[nx][ny][2] != 0 && sharkInfo[nx][ny][1] != i+1) continue;
						
						isMove = true;
						copyShark[sharkX][sharkY][0] = 0;
							
						/*상어죽을지 안죽을지 확인하기*/
						
						//빈칸인 경우, 움직인 상어의 정보를 갱신
						if(copyShark[nx][ny][0] == 0) {
							copyShark[nx][ny][0] = i+1;
							copyShark[nx][ny][1] = i+1;
							copyShark[nx][ny][2] = k;
							
							//이동한 정보로 갱신
							shark[i].x = nx;
							shark[i].y = ny;
							shark[i].dir = nd;
						}
						
						//움직일 예정인 장소에 상어가 있는 경우
						//1번부터 상어를 움직이기 때문에 1번 이후에 오는 상어들은 죽음!
						else {
							//현재위치의 상어죽이기
							killedShark++;
							shark[i].x = -1;
						}
						break;
					}
				}
				
			}
			
			//1마리만 남기고 다 죽은 경우 종료!
			if (killedShark == m-1) break;
			
			//한턴이 끝나면 재 복사
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sharkInfo[i][j][0] = copyShark[i][j][0];
					sharkInfo[i][j][1] = copyShark[i][j][1];
					sharkInfo[i][j][2] = copyShark[i][j][2];
				}
			}
			
		}
		
		if(time <= 1000) res = time;
		
	}

	public static class shark{
		int x;
		int y;
		int dir;
		int priority[][] = new int[4][4];
		
		public shark(int x, int y, int dir, int[][] priority) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.priority = priority;
		}

	}
	
}
