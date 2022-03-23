package programmers;

public class d220324_등굣길 {

	public static void main(String[] args) {
		int m = 4;
		int n = 3;
		int[][] puddles = {{2, 2}};
		int answer = solution(m, n, puddles);	//4	3	[[2, 2]]
		System.out.println(answer);	//4
	}
	static final int NUMBER = 1000000007;
	static int[] dx = {1, 0}, dy = {0, 1};	//아래, 오른쪽
	static int answer = 0;
	
	private static int solution(int m, int n, int[][] puddles) {
		int[][] map = new int[m+1][n+1];
		//물웅덩이 map에 표시
		for(int[] puddle : puddles) {
			map[puddle[0]][puddle[1]] = -1;	
		}
		//효율성 0점
		//bfs(1, 1, m, n, map);
        //return answer;
		//dynamic programming dp로 풀기
		map[1][1] = 1;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if(map[i][j] == -1) continue;
				if(map[i-1][j] > 0) map[i][j] += map[i-1][j] % NUMBER;
				if(map[i][j-1] > 0) map[i][j] += map[i][j-1] % NUMBER;
			}
		}
		
		return map[m][n] % NUMBER;
	}

	private static void bfs(int x, int y, int m, int n, int[][] map) {
		if(x == m && y == n) {
			answer++;
			return;
		}
		for (int i = 0; i < 2; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			//map을 벗어나지 못하게 한다.
			if(nx > m || nx < 1 || ny > n || ny < 1) continue;
			if(map[nx][ny] == -1) continue;
			bfs(nx, ny, m, n, map);
		}
	}

}
