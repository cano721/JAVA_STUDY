package programmers;

public class d220303_카카오프렌즈컬러링북 {

	public static void main(String[] args) {
		int m = 6;
		int n = 4;
		int[][] picture = {{1, 1, 1, 0},{1, 2, 2, 0},{1, 0, 0, 1},{0, 0, 0, 1},{0, 0, 0, 3},{0, 0, 0, 3}};
		int[] answer = solution(m, n, picture);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}

	}
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int temp_maxSizeOfOneArea = 0;
	private static int[] solution(int m, int n, int[][] picture) {
		int[] answer = new int[2]; //영역 개수, 최대 넓이
		visited = new boolean[m][n];
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(picture[i][j] != 0 && !visited[i][j]) {
					numberOfArea++;
					dfs(i, j, picture);
				}
				maxSizeOfOneArea = Math.max(maxSizeOfOneArea, temp_maxSizeOfOneArea);
				temp_maxSizeOfOneArea = 0;
			}
		}
		answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
		return answer;
	}
	private static void dfs(int i, int j, int[][] picture) {
		if(visited[i][j]) return;
		visited[i][j] = true;
		temp_maxSizeOfOneArea++;
		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			if(nx<0 || nx>=picture.length || ny<0 || ny>=picture[0].length) continue;
			if(picture[nx][ny] == picture[i][j] && !visited[nx][ny]) {
				dfs(nx, ny, picture);
			}
		}
		
	}

}
