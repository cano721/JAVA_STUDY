package 전체유형문제풀이;

import java.util.*;



/*
 * 1. 배열의 길이가 최대 8임으로, DFS를 이용한 완전탐색
 * 2. visited으로 방문처리하면서 확인하기
 * 3. 중복방지를 위해 set사용
 * 
 * 
 * 참고) https://so-cute-danu-dev.tistory.com/34
 * */

public class PG64064_불량사용자 {

	public static void main(String[] args) {

		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};

		int result = solution(user_id,banned_id);

		System.out.println(result);

	}

	static HashSet<String> banUser = new HashSet<String>();
	static boolean[] visited;
	
	private static int solution(String[] user_id, String[] banned_id) {
		
		visited = new boolean[user_id.length];
		getDFS(user_id, banned_id, 0);
		
		Iterator<String> it = banUser.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		return banUser.size();
	}

	public static void getDFS(String[] user_id, String[] banned_id, int idx) {

		//ban된 아이디 모두 찾기 완료
		if(idx == banned_id.length) {
			
			StringBuilder userIdx = new StringBuilder();
			for (int i = 0; i < user_id.length; i++) {
				if(visited[i]) {
					userIdx.append(i);
				}
			}
			banUser.add(userIdx.toString());
			/*for (int i = 0; i < user_id.length; i++) {
				if(visited[i]) {
					banUser.add(String.valueOf(i));
				}
			}*/
			
			return;
		}
		
		for (int i = 0; i < user_id.length; i++) {
			if(visited[i]) continue;
			boolean chk = false;
			
			//유저와 부량 사용자의 길이가 같은 경우만 확인
			if(user_id[i].length() == banned_id[idx].length()) {
				
				//불량사용자 일것 같은 유저 찾기
				for (int j = 0; j < user_id[i].length(); j++) {
					if(banned_id[idx].charAt(j) == '*') continue;
					
					if(user_id[i].charAt(j) != banned_id[idx].charAt(j)) {
						chk = true;
						break;
					}
				}
				
				//모든 글자가 일치한 경우, 다음을 탐색하며 진행
				if(!chk) {
					visited[i] = true;
					getDFS(user_id, banned_id, idx+1);
					visited[i] = false;
				}
				
			}
		}
		
	}

	

}
