package elwlahd555.programmers;

public class 프로그래머스87946_피로도 {
	public static void main(String[] args) {
		int k = 80;
		int dungeons[][] = { { 80, 20 }, { 50, 40 }, { 30, 10 } };
		System.out.println(solution(k, dungeons));
	}

	private static boolean visited[];
	private static int answer;

	public static int solution(int k, int[][] dungeons) {
		answer = -1;
		visited = new boolean[dungeons.length];
		dfs(0, k, dungeons, 0);
		return answer;
	}

	private static void dfs(int cnt, int k, int[][] dungeons, int ans) {
		if (cnt >= dungeons.length) {
			if (answer < ans) {
				answer = ans;
			}
		}
		for (int i = 0; i < dungeons.length; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			if (k >= dungeons[i][0]) {
				dfs(cnt + 1, k - dungeons[i][1], dungeons, ans + 1);
			} else {
				dfs(cnt + 1, k, dungeons, ans);
			}
			visited[i] = false;
		}
	}
}
