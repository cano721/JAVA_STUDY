package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d220418_퇴사 {
	static int n;
	static int[] day;
	static int[] pay;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		n = Integer.parseInt(st.nextToken());
		day = new int[n];
		pay = new int[n];
		
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			day[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		System.out.println(answer);

	}

	private static void dfs(int depth, int val) {
		if(depth >= n) {
			answer = Math.max(answer, val);
			return;
		}
		if(depth+day[depth] > n) dfs(depth+day[depth], val);
		else dfs(depth+day[depth], val+pay[depth]);		//상담 선택
		
		dfs(depth+1, val);									//상담 미선택
	}

}
