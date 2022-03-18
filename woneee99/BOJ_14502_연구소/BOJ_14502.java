import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {
	static int n, m, result = 0;
	static int[][] arr, brr;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로

		arr = new int[n][m];
		brr = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(result);
	}

	static void dfs(int depth) {
		if(depth == 3) {
			bfs();
			return;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					dfs(depth+1);
					arr[i][j] = 0;
				}
			}
		}
	}
	static void bfs() {
		brr = new int[n][m];
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		Queue<int[] > q = new LinkedList<>();
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				brr[i][j] = arr[i][j];
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(brr[i][j] == 2) q.add(new int[]{i, j});
			}
		}
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			
			for(int i=0; i<4; i++) {
				int x = dx[i] + tmp[0];
				int y = dy[i] + tmp[1];
				
				if(x<0 || y<0 || x>=n || y>=m) continue;
				
				if(brr[x][y] == 0) {
					brr[x][y] = 2;
					q.add(new int[] {x, y});
				}
				
			}
		}
		int cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(brr[i][j] == 0) cnt++;
			}
		}
		result = Math.max(result, cnt);
	}
}
