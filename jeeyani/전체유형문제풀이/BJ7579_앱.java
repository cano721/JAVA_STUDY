package 전체유형문제풀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ7579_앱 {

	static int n,m;
	static int cost[], memory[];
	static int[] dp = new int[10001];
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		memory = new int[n];
		cost = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i <n; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
			
		}
		
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i <n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			sum += cost[i];
		}
		
		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			for (int j = sum; j >= cost[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - cost[i]]+memory[i]);
				
				//최소 M 메모리를 확보할 수 있는 비용 구하기
				if(dp[j] >= m) ans = Math.min(ans, j);
			}
			
			
		}
		
		System.out.println(ans);
		
	}
}
