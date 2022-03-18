package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Virus{
	int x;
	int y;
	
	public Virus(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class d220318_연구소 {
	static int N, M;
	static int[][] arr;
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					DFS(1);
					arr[i][j] = 0;
				}
				
			}
		}
        System.out.println(answer);

	}
	private static void DFS(int depth) {
		if(depth == 3) {
			BFS();
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					DFS(depth + 1);
					arr[i][j] = 0;
				}
				
			}
		}
		
	}
	//안전영역
	private static void BFS() {
		int[][] virusArr = new int[N][M];
		Queue<Virus> queue = new LinkedList<Virus>();
		//배열 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				virusArr[i][j] = arr[i][j];
			}
		}
		//virus 있는 좌표 queue에 넣기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(virusArr[i][j] == 2) {
					queue.add(new Virus(i, j));
				}
			}
		}
		//virus 퍼뜨리기
		while(!queue.isEmpty()) {
			Virus virus = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = virus.x + dx[i];
				int ny = virus.y + dy[i];
				if(nx >= N || nx < 0 || ny >= M || ny < 0) continue;
				if(virusArr[nx][ny] == 0) {
					virusArr[nx][ny] = 2;
					queue.add(new Virus(nx, ny));
				}
				
			}			
		}
		//safe count
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(virusArr[i][j] == 0) {
					cnt++;
				}
			}
		}
		answer = Math.max(answer, cnt);
	}

}
