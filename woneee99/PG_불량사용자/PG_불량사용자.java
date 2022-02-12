import java.util.HashSet;
/*
 * 참고 : https://so-cute-danu-dev.tistory.com/34
 * 다시 풀어보기 !!
 */
public class PG_불량사용자 {
	private static HashSet<String> ban_user_idx;

	public int solution(String[] user_id, String[] banned_id) {

		boolean visited[] = new boolean[user_id.length]; // 방문체크
		ban_user_idx = new HashSet<String>(); // 중복제거
		cal(user_id, banned_id, 0, visited); // 경우의 수 찾기
		int answer = ban_user_idx.size();
		return answer;
	}

	static void cal(String[] user_id, String[] banned_id, int ban_idx, boolean[] visited) {
		if (ban_idx == banned_id.length) {
			StringBuilder user_idxs = new StringBuilder();
			for (int i = 0; i < user_id.length; i++) {
				if (visited[i]) {
					user_idxs.append(i);
				}
			}
			ban_user_idx.add(user_idxs.toString());
			return;
		}
		for (int i = 0; i < user_id.length; i++) {
			if (visited[i]) continue;
			boolean flag = false;
			if (user_id[i].length() == banned_id[ban_idx].length()) {
				for (int s = 0; s < user_id[i].length(); s++) {
					if (banned_id[ban_idx].charAt(s) == '*')
						continue;
					if (user_id[i].charAt(s) != banned_id[ban_idx].charAt(s)) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					visited[i] = true;
					cal(user_id, banned_id, ban_idx + 1, visited);
					visited[i] = false;
				}
			}
		}
	}
}
