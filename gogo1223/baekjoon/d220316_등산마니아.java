package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
3 
1 2
2 3
8
6 2
7 5
3 4
5 6
1 5
4 1
8 6
*/
public class d220316_등산마니아 {
	static int N;
	static ArrayList<Integer>[] list;
	static int answer;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
        //연결된 오솔길 작성
        for (int i = 0; i < N-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	list[a].add(b);
        	list[b].add(a);
        }
        createDP(1);
        System.out.println(answer);
        
	}
	//https://blog.naver.com/PostView.nhn?blogId=kerochuu&logNo=222179356853
	//문제가 이해가 안되는데,,
	private static int createDP(int now) {
		dp[now] = 1;
		for (int next : list[now]) {
			if (dp[next] == 0) {
				dp[now] += createDP(next);
			}
		}
		if (now != 1) {
			answer += comb(N) - comb(N - dp[now]);
		}
		return dp[now];
	}

	private static long comb(int n) {
		return (long) n * (long) (n - 1) / 2;
	}
}
