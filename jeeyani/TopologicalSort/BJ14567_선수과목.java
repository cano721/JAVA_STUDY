package TopologicalSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 * 
 * dp[i] = Math.max(dp[i], dp[j]+1)
 * 
 * i = 선수과목 이후 듣는 과목 , j = 선수과목
 * 
 @author Jeeyani
 */

public class BJ14567_선수과목 {

	static int n, m;
	static List<Integer>[] list; //선행관계 
	static int[] ClassList;
	static int[] dp; //해당 건물을 짓기 전의 선행건물의 짓는데 걸리는 시간의 최댓값

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];
		ClassList = new int[n + 1];
		dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
			dp[i] = 1;
		}

		for (int i = 1; i <= m; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			ClassList[b]++;

		}

		getTerm();

		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= n; i++) {
			sb.append(dp[i] + " ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void getTerm() {
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= n; i++) {
			if (ClassList[i] == 0) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()) {
			int v = q.remove();
			
			for (int j = 0; j < list[v].size(); j++) {
				int next = list[v].get(j);
				//단순 값을 증가시키는 것이 아닌 최대값을 찾아야함
				//dp[next]++;
				dp[next] = Math.max(dp[next], dp[v]+1);
				ClassList[next]--;
				
				if (ClassList[next] == 0) {
					q.add(next);
				}
			}
			
		
		}
		
	}

}
