import java.util.*;

class Solution {
	static int answer = 0;
	static boolean visit[];

	public int solution(int k, int[][] dungeons) {
		visit = new boolean[dungeons.length];
		dfs(k, dungeons, 0);
		return answer;
	}

	public void dfs(int k, int[][] dungeons, int cnt) {
		for (int i = 0; i < dungeons.length; i++) {
			if (!visit[i] && dungeons[i][0] <= k) {
				visit[i] = true;
				dfs(k - dungeons[i][1], dungeons, cnt + 1);
				visit[i] = false;
			}
		}
		answer = Math.max(answer, cnt);
	}
}
