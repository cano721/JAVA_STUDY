import java.util.*;
import java.io.*;
/*
LCA(최소 공통 조상) - LCA 2 
*/

public class Main {
	static ArrayList[] list;
	static int[] depth, parent;
	static int[][] map;
	static int m = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];
        
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}
        
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
        
		depth = new int[n + 1];
		parent = new int[n + 1];
		int tmp = 1;
        
        //트리의 깊이 계산
		while (tmp <= n) { 
			tmp <<= 1;
			m++;
		}
        
		map = new int[n + 1][m];
		dfs(1, 1);
		for (int i = 1; i < m; i++) { // 각 노드별 2^m 번 째 조상 노드를 저장
			for (int j = 1; j <= n; j++) {
				map[j][i] = map[map[j][i - 1]][i - 1];
			}
		}


        
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int ans = LCA(a,b);
            System.out.println(ans);
		}
		

	}

	public static void dfs(int node, int cur) {// node: 방문 노드, cnt: 현재 깊이
		depth[node] = cur;

		for (int i = 0; i < list[node].size(); i++) {
			int child = (int) list[node].get(i);
			if (depth[child] == 0) { // 방문한 곳은 패스
				dfs(child, cur + 1);
				map[child][0] = node;
			}
		}

	}

	private static int LCA(int a, int b) {
        // a의 깊이가 b의 깊이보다 작을경우 a와 b 스왑
		if (depth[a] < depth[b]) { 
			int temp = a;
			a = b;
			b = temp;
		}

		// 더 깊은 a를 2승씩 점프하며 두 노드의 depth를 맞춘다
		for (int i = m - 1; i >= 0; i--) {
			if (Math.pow(2, i) <= depth[a] - depth[b]) {
				a = map[a][i]; // a를 2^i 번 째 조상 노드로 대체한다.
			}
		}

		// depth 맞춘 후 a의 조상노드가 b라면 종료
		if (a == b)
			return a;

		// a,b는 같은 depth이므로 2승씩 점프시키며 공통부모 바로 아래까지 올린다
		for (int i = m - 1; i >= 0; i--) {
			if (map[a][i] != map[b][i]) {
				a = map[a][i];
				b = map[b][i];
			}
		}
		return map[a][0];
        
	}
}