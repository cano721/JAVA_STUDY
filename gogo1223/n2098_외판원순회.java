import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n2098_외판원순회 {
	static int[][] map;
	static int[][] dp;
	static int N;
	static final int INF = 11000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][(1 << N) - 1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], INF);
		}
		System.out.println(dfs(0, 1));
	}
	private static int dfs(int city, int visitBitmask) {
		
		if(visitBitmask == (1 << N) - 1) {	// 모든 도시를 방문했다면
			if(map[city][0] == 0) {			// 혹시 발생하는 경우 예외 처리
				return INF;
			}
			return map[city][0];	// 현재 도시 -> 0번째(시작) 도시 이동 거리
		}
		
		if(dp[city][visitBitmask] != INF) {	// dp값이 존재하는경우
			return dp[city][visitBitmask];
		}
		
		for(int i = 0; i < N; i++) {	// 현재 도시(city)에서 각 i의 도시로 이동한 경우에 대해 DFS 수행
			if((visitBitmask & (1 << i)) == 0 && map[city][i] != 0) {	// 한 번이라도 그 도시를 방문했는데 다시 그 도시를 방문하는 경우 예외처리
	//			d[i][j] = 현재 있는 도시가 i이고 이미 방문한 도시들의 집합이 j일때, 방문하지 않은 나머지 도시들을 모두 방문한 뒤 출발 도시로 돌아올 때 드는 최소 비용.
	//			즉, 방문해야하는 도시(여기에 시작지점으로 돌아오는 것 포함) 들까지 가는 최소 비용
				dp[city][visitBitmask] = Math.min(dp[city][visitBitmask], dfs(i, visitBitmask | (1 << i)) + map[city][i]);	// dfs(다음 도시, 다음도시 방문했다고 가정) + 여기서 다음 도시까지의 거리 와 최소거리 비교
			}
		}
		
		return dp[city][visitBitmask];
	}

}
