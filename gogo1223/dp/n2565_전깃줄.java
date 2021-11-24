package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class n2565_전깃줄 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][2];
		int[] dp = new int[n];
		int answer = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, new Comparator<int[]>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {
        		return o1[0] - o2[0];
        	}
		});
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if(arr[i][1] > arr[j][1]){
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		for (int j = 0; j < dp.length; j++) {
			answer = Math.max(answer, dp[j]);
		}
		System.out.println(n - answer);

	}

}
