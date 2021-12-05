package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n1495_기타리스트 {
	static int n, s, m;
	static int[] arr, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken()); 	//the number of songs
        s = Integer.parseInt(st.nextToken()); 	//start volume
        m = Integer.parseInt(st.nextToken()); 	//max volume
        
        arr = new int[n];						//song array
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        dp = new int[m+1];
        dp[s] = 1;	//dp[volume] = i번째 연주곡
        
        for (int i = 0; i < n; i++) {
        	ArrayList<Integer> list = new ArrayList<Integer>();
        	for (int j = 0; j <= m; j++) {
				if(dp[j] == i+1) {
					int plus = j + arr[i];
					int minus = j - arr[i];
					if(plus <= m && plus >= 0)
						list.add(plus);
					if(minus <= m && minus >= 0)
						list.add(minus);
					
				}
			}
        	for(int volume : list) {
        		dp[volume] = i+2;
        	}
        	
		}
        
        int answer = -1;
        for (int i = 0; i <= m; i++) {
			if(dp[i] == n+1) {
				answer = Math.max(answer, i);
			}
		}
        System.out.println(answer);

	}

}
