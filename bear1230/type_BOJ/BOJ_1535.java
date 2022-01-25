import java.util.*;
import java.io.*;

public class Main {
    static int happy[];
	static int sad[];	
	static int n;
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       
		n = Integer.parseInt(br.readLine());        
		sad = new int[n+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++){
            sad[i]=Integer.parseInt(st.nextToken());
        }
			
		
		happy = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++)
			happy[i]=Integer.parseInt(st.nextToken());
		
		int dp[][] = new int[n+1][101];
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<100; j++) {
				dp[i][j] = dp[i-1][j];
				if(j>=sad[i] && dp[i][j]<dp[i-1][j-sad[i]]+happy[i]) {
					dp[i][j] = dp[i-1][j-sad[i]]+happy[i];
				}
			}
		}
		System.out.println(dp[n][99]);
		
	}
}

