import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2616_소형기관차 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1];
		int[] sum = new int[N+1];
		int[][] dp = new int[4][N+1];
        for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + arr[i];
		}
        int max = Integer.parseInt(br.readLine());
        for (int i = 1; i < 4; i++) {
			for (int j = i*max; j <= N; j++) {
				dp[i][j] = Math.max(
                        dp[i][j - 1],
                        dp[i - 1][j - max] + (sum[j] - sum[j - max])
                );
			}
		}
        
        System.out.println(dp[3][N]);
	}

}
