import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class D20211024_3_BJ_14889_스타트와링크 {
	static int N;
	static int[][] Map;
	static boolean[] Visited;
	
	static int Answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Map = new int[N][N];
		Visited = new boolean[N];
 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) Map[i][j] = Integer.parseInt(st.nextToken());
		}
 
		dfs(0, 0);
		System.out.println(Answer);
 
	}
 
	static void dfs(int idx, int count) {
//		절반이 방문했으면 점수차 계산
		if(count == N / 2) {
			calculation();
			return;
		}
		for(int i = idx; i < N; i++) {
			if(Visited[i]) continue;
			
			Visited[i] = true;
			dfs(i + 1, count + 1);
			Visited[i] = false;
		}
	}
 
	static void calculation() {
		int startTeamPoint = 0;
		int linkTeamPoint = 0;
 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
//				i j와 j i를 따로 더하니 중복이라 두번 값이 더해짐 so. 한번만 더함 
				if(Visited[i] && Visited[j]) startTeamPoint += Map[i][j];
				else if(!Visited[i] && !Visited[j])	linkTeamPoint += Map[i][j];
			}
		}
		int temp = Math.abs(startTeamPoint - linkTeamPoint);
		Answer = Math.min(temp, Answer);
	}
 
}