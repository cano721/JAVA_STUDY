package 전체유형문제풀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14502_연구소 {

	static int n, m, result = 0;
    static int[][] map = new int[8][8]; 
    static int[][] temp = new int[8][8];
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		 dfs(0);
	     System.out.println(result);
		
	}

	private static void dfs(int count) {
		
		//1.울타리 3개가 다 설치되었을 때
		if(count == 3) {
			
			//1-1. 임시지도에 현재 울타리 위치를 저장
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			 //1-2.각 바이러스의 위치에서 바이러스 전파
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (temp[i][j] == 2) {
                        virus(i, j);
                    }
                }
            }
            //1-3.안전 영역의 최대값 계산
            result = Math.max(result, getScore());
            return;
			
		}
				
		//2. 빈 공간에 울타리를 설치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
            	//안전영역에 울타리 치기
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    count += 1;
                    dfs(count);
                    
                    //3개 울타리를 계속치기 위해 초기화
                    map[i][j] = 0;
                    count -= 1;
                }
            }
        }
		
	}

	//2. 안정영역(0)인 수 계산
	private static int getScore() {
		int score =0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(temp[i][j]==0) {
					score +=1;
				}
			}
		}
		return score;
	}

	//3. 각 바이러스가 사방으로 퍼지도록 하기
	private static void virus(int x, int y) {
		// 상하좌우로 이동
		for (int i = 0; i < 4; i++) {
	            int nx = x + dx[i];
	            int ny = y + dy[i];
	            
	            //바이러스가 퍼질 수 있는 위치에 바이러스 퍼트리기
	            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
	                if (temp[nx][ny] == 0) {
	     	            temp[nx][ny] = 2;
	                    virus(nx, ny);
	                }
	            }
	        }
	}

}
