package programmers;

public class d220304_방문길이 {

	public static void main(String[] args) {
		String dirs = "LULLLLLLU";//"ULURRDLLU";
		int answer = solution(dirs);
		System.out.println(answer);	//7
	}
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][][][] visited = new boolean[11][11][11][11];
	private static int solution(String dirs) {
		int answer = 0;
		int afX = 5;
		int afY = 5;
		int x = 0;
		int y = 0;
		int idx = 0;
		for (int i = 0; i < dirs.length(); i++) {
			x = afX;
			y = afY;
			switch(dirs.charAt(i)) {
			case 'U': idx = 3; break;
			case 'D': idx = 2; break;
			case 'L': idx = 0; break;
			case 'R': idx = 1; break;
			}
			afX = x + dx[idx];
			afY = y + dy[idx];
			if(afX > 10 || afX < 0 || afY > 10 || afY < 0) {
				afX = x; 
				afY = y;
				continue;
			}
			if(!visited[x][y][afX][afY] && !visited[afX][afY][x][y]) {
				visited[x][y][afX][afY] = true;
				visited[afX][afY][x][y] = true;
				answer++;
			}
		}
		return answer;
	}

}
