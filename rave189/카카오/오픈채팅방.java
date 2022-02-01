package Programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		String[] result = solution.solution(record);
	}
}

class Solution {
	HashMap<String, String> hash = new HashMap<>();

	/**
	 * 채팅방에는 다음과 같이 로그를 기록한다.
	 * 유저가 들어오면 "Enter 유저아이디 닉네임"의 형식으로 기록이 된다.
	 * 유저가 나가면 "Leave 유저아이디"의 형식으로 기록된다.
	 * 유저가 닉네임을 바꾸면 "Change 유저아이디 닉네임"으로 기록된다.
	 * 이 채팅방에서는 A유저가 나갔다가 다른 닉네임으로 들어오면 이전 채팅 기록도 모두 새로 들어온 닉네임으로 변경된다.
	 * 기록이 주어질 때, 모든 기록이 처리된 후의 로그를 출력하는 문제
	 * 유저가 들어오면 "user님이 들어왔습니다.", 유저가 나가면 "user님이 나갔습니다."와 같이 출력한다.
	 * 닉네임을 변경하는 것은 출력하지 않는다.
	 * 
	 * 유저가 나가면 hash에서 삭제하도록 했다.
	 * 그러면 나중에 기록으로 옮길 때, 유저의 정보가 없어서 null로 나오게 되는데
	 * null님이들어왔습니다. 처럼 출력이 되버린다.
	 * 
	 * @param record 채팅방의 기록
	 * @return 가공한 채팅방의 기록
	 */
	public String[] solution(String[] record) {
		ArrayList<User> chatRoom = new ArrayList<>();
		for (String message : record) {
			String[] split = message.split(" ");
			if (split[0].equals("Enter")) {
				hash.put(split[1], split[2]);
				chatRoom.add(new User(split[1], 1));
			} else if (split[0].equals("Leave"))
				chatRoom.add(new User(split[1], 2));
			else
				hash.replace(split[1], split[2]);
		}
		String[] answer = new String[chatRoom.size()];
		for (int i = 0; i < chatRoom.size(); i++) {
			User user = chatRoom.get(i);
			answer[i] = hash.get(user.id) + (user.type == 1 ? "님이 들어왔습니다." : "님이 나갔습니다.");
		}
		return answer;
	}
}

class User {
	String id;
	int type;

	public User(String id, int type) {
		this.id = id;
		this.type = type;
	}
}