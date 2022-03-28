import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21608 {
	static int[] dx = {0, -1, 1, 0};
	static int[] dy = {-1, 0, 0, 1};
	static int[][] arr, check;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		check = new int[N*N+1][4];
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<4; j++) {
				check[num][j] = Integer.parseInt(st.nextToken());
			}
			cal(num, check[num]);
		}
		
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int cnt = 0;
				for(int k=0; k<4; k++) {
					int x = dx[k] + i;
					int y = dy[k] + j;
					
					if(x<0 || y<0 || x>=N || y>=N) continue;
					
					for(int d=0; d<4; d++) {
						if(arr[x][y] == check[arr[i][j]][d]) {
							cnt++;
						}
					}
				}
				if(cnt != 0) answer += (int)Math.pow(10, cnt-1);
			}
		}
		System.out.println(answer);
	}
	static void cal(int num, int[] chk) {
		int[][] cnt = new int[N+1][N+1];
		int max = 0;
		//조건 1번
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<4; k++) {
					if(chk[k] == arr[i][j]) {
						for(int d=0; d<4; d++) {
							int x = i + dx[d];
							int y = j + dy[d];
							if(x<0 || y<0 || x>=N || y>=N || arr[x][y] != 0) continue;
							
							cnt[x][y]++;
							max = Math.max(max, cnt[x][y]);
						}
					}
				}
			}
		}
		
		//조건 2, 3번
		int empty = -1;
		int x1 = 0, y1 = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(cnt[i][j] == max && arr[i][j] == 0) {
					int check = 0;
					for(int k=0; k<4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						
						if(x<0 || y<0 || x>=N || y>=N) continue;
						
						if(arr[x][y] == 0) check++;
					}
					if(empty < check) {
						empty = check;
						x1 = i;
						y1 = j;
					}
				}
			}
		}
		arr[x1][y1] = num;
	}
}
