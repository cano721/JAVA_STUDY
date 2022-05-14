import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] a  = new int[N];
		int[] b  = new int[N];
		
		int[][] dp = new int [N+1][N+1];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			a[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			b[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1;j <= N; j++) {
				dp[i][j]=Math.max(dp[i - 1][j],dp[i][j - 1]);
				if(Math.abs(b[i - 1] - a[j - 1]) <= 4) {
					dp[i][j]=dp[i - 1][j - 1] + 1;
				}
			}
		}
		
		System.out.println(dp[N][N]);
	}
}
