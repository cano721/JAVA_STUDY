import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D20211102_2_BJ_2606_바이러스 {
	private static boolean[] visited;
	private static boolean[][] map;
	private static int cnt = -1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int x, y, n = Integer.parseInt(br.readLine());
		int no = Integer.parseInt(br.readLine());
		
		visited = new boolean[n+1];
		map = new boolean[n+1][n+1];
		
		for(int i = 0; i < no; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			map[x][y] = map[y][x] = true;
		}
		dfs(1, n);
		System.out.println(cnt);
	}
	static void dfs(int v, int n) {
		if(visited[v]) return;
		visited[v] = true;
		cnt++;
		for(int i = 0; i <= n; i++) if(map[v][i]) dfs(i, n);
	}
}
