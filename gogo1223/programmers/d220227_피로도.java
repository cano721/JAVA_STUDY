package programmers;

public class d220227_피로도 {

	public static void main(String[] args) {
		int k = 80;
		int[][] dungeons = {{80,20},{50,40},{30,10}};
		int ans = solution(k, dungeons);
		System.out.println(ans);
	}
	static int answer = 0;
	private static int solution(int k, int[][] dungeons) {
		boolean[] visited = new boolean[dungeons.length];
		
		dfs(visited, 0, k, dungeons);
		
		return answer;
	}

	private static void dfs(boolean[] visited, int depth, int k, int[][] dungeons) {
		for (int i = 0; i < dungeons.length; i++) {
			if(!visited[i] && dungeons[i][0] <= k) {
				visited[i] = true;
				dfs(visited, depth++, k - dungeons[i][1], dungeons);
				visited[i] = false;
			}
		}
		answer = Math.max(answer, depth);
	}

}
