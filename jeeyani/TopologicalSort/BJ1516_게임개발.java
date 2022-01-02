package TopologicalSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 * 
 * dp : 선행되는 조상노드의 실행 시간의 최댓값
 * 
 * a = 5, b = 10, c =7 일때
 * b,c 건물이 다 지어진 이후에 a건물을 지울 수 있다고 하면
 * 
 * a 건물을 짓는데 걸리는 최단 시간은 10+5 = 15이다!!
 * 
 * dp[i] = max(dp[i], buildingTime[j]+dp[j])
 * 
 * 
 * 
 * 참고 : https://hqjang.tistory.com/109
 @author Jeeyani
 */

public class BJ1516_게임개발 {

	static int n;
	static List<Integer>[] list; //선행관계 
	static int[] buildingTime; //건물의 짓는데 걸리는 시간
	static int[] buildingList;
	static int[] dp; //해당 건물을 짓기 전의 선행건물의 짓는데 걸리는 시간의 최댓값

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		list = new ArrayList[n + 1];
		buildingTime = new int[n + 1];
		buildingList = new int[n + 1];
		dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i <= n; i++) {

			st = new StringTokenizer(br.readLine());
			buildingTime[i] = Integer.parseInt(st.nextToken());
			dp[i] = buildingTime[i];
			
			while (st.hasMoreTokens()) {
				int temp = Integer.parseInt(st.nextToken());

				if (temp == -1) break;

				list[temp].add(i);
				buildingList[i]++;
			}

		}

		getBulitMinTime();
		
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <=n; i++) {
			sb.append(dp[i]+"\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void getBulitMinTime() {
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= n; i++) {
			if (buildingList[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int v = q.remove();

			for (int j = 0; j < list[v].size(); j++) {
				int next = list[v].get(j);
				dp[next] = Math.max(dp[next], buildingTime[v]+dp[v]);
				buildingList[next]--;

				if (buildingList[next] == 0) {
					q.add(next);
				}

			}
		}

	}
}
