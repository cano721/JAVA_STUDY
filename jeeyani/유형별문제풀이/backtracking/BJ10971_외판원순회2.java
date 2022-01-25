package 유형별문제풀이.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10971_외판원순회2 {

	static int n;
	static int[][] W;
	static boolean[] visit;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		W = new int[n][n];
		visit = new boolean[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < n; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 시작점이 어디든 최소값은 모두 동일함!
		getCourses(1,1,0, 0);
		System.out.println(min);
	}

	private static void getCourses(int start, int now, int cnt, int pay) {

		//모든 경로를 다 돌았다면 종료 ==> 시작점으로 다시 돌아오는 경우도 확인해줘야함
		//if (cnt == n) {
		
		//다시 시작점으로 돌아온 경우와, 모든 경로를 다 돌았을 경우
		if(start == now && cnt == n ) {
			min = Math.min(min, pay);
			return;
		}

		for (int i = 0; i < n; i++) {
			
			if(W[now][i] == 0) continue;
			
			if(!visit[i] && W[now][i] > 0) {
				
				visit[i] = true;
				pay += W[now][i];
				getCourses(start, i, cnt+1, pay);
				
				pay -=W[now][i];
				visit[i] = false;
				
			}
	

		}

	}

}
