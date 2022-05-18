import java.io.*;
import java.util.*;

public class Main {
	static long[][] map;
	static long[][] sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new long[N + 1][M + 1];
		sum = new long[N + 1][M + 1];
		long ans = 0;
		
		for(int i=1; i<=N; i++) {
			String line = br.readLine();
			for(int j=1; j<=M; j++) {
				map[i][j] = Long.parseLong("" + line.charAt(j - 1));
				sum[i][j] = map[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
			}
		}
		
		
		for(int i=1; i<=N-2; i++) {
			for(int j=i+1; j<=N-1; j++) {
				long r1 = func(1, 1, i, M);
				long r2 = func(i+1, 1, j, M);
				long r3 = func(j+1, 1, N, M);
				
				if(ans < r1 * r2 * r3) {
					ans = r1 * r2 * r3;
				}
			}
		}
		
		for(int i=1; i<=M-2; i++) {
			for(int j=i+1; j<=M-1; j++) {
				long r1 = func(1, 1, N, i);
				long r2 = func(1, i+1, N, j);
				long r3 = func(1, j+1, N, M);
				
				if(ans < r1 * r2 * r3) {
					ans = r1 * r2 * r3;
				}
			}
		}
		
		for(int i=1; i<=N-1; i++) {
			for(int j=1; j<=M-1; j++) {
				long r1 = func(1, 1, i, M);
				long r2 = func(i+1, 1, N, j);
				long r3 = func(i+1, j+1, N, M);
				
				if(ans < r1 * r2 * r3) {
					ans = r1 * r2 * r3;
				}
			}
		}
		
		for(int i=1; i<=N-1; i++) {
			for(int j=1; j<=M-1; j++) {
				long r1 = func(1, 1, i, j);
				long r2 = func(1, j+1, i, M);
				long r3 = func(i+1, 1, N, M);
				
				if(ans < r1 * r2 * r3) {
					ans = r1 * r2 * r3;
				}
			}
		}
		
		for(int i=1; i<=M-1; i++) {
			for(int j=1; j<=N-1; j++) {
				long r1 = func(1, 1, N, i);
				long r2 = func(1, i + 1, j, M);
				long r3 = func(j+1, i+1, N, M);
				
				if(ans < r1 * r2 * r3) {
					ans = r1 * r2 * r3;
				}
			}
		}
		
		for(int i=1; i<=M-1; i++) {
			for(int j=1; j<=N-1; j++) {
				long r1 = func(1, 1, j, i);
				long r2 = func(j+1, 1, N, i);
				long r3 = func(1, i+1, N, M);
				
				if(ans < r1 * r2 * r3) {
					ans = r1 * r2 * r3;
				}
			}
		}
		
		System.out.println(ans);
	}

	private static long func(int dx, int dy, int ex, int ey) {
		return sum[ex][ey] - sum[dx-1][ey] - sum[ex][dy-1] + sum[dx - 1][dy - 1];
	}

}