import java.util.*;

class Solution {
	public String[] solution(String[] record) {
		Map<String, String> hash = new HashMap<>(); // 아이디-닉네임 
		int cnt = 0; 

		for (int i = 0; i < record.length; i++) {
			String[] chat = record[i].split(" ");

			if (chat[0].equals("Leave")) { 
				continue;
			} else if (chat[0].equals("Enter")) { 
				hash.put(chat[1], chat[2]);
			} else { // 닉네임 변경
				hash.put(chat[1], chat[2]);
				cnt++;
			}
		}

		String[] answer = new String[record.length - cnt];
		int idx = 0;

		for (int i = 0; i < record.length; i++) {
			String[] chat = record[i].split(" ");
			String name = hash.get(chat[1]);

			if (chat[0].equals("Enter")) { 
				answer[idx++] = name + "님이 들어왔습니다.";
			} else if (chat[0].equals("Leave")) { 
				answer[idx++] = name + "님이 나갔습니다.";
			}
		}

		return answer;
	}
}
