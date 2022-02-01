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
	 * ä�ù濡�� ������ ���� �α׸� ����Ѵ�.
	 * ������ ������ "Enter �������̵� �г���"�� �������� ����� �ȴ�.
	 * ������ ������ "Leave �������̵�"�� �������� ��ϵȴ�.
	 * ������ �г����� �ٲٸ� "Change �������̵� �г���"���� ��ϵȴ�.
	 * �� ä�ù濡���� A������ �����ٰ� �ٸ� �г������� ������ ���� ä�� ��ϵ� ��� ���� ���� �г������� ����ȴ�.
	 * ����� �־��� ��, ��� ����� ó���� ���� �α׸� ����ϴ� ����
	 * ������ ������ "user���� ���Խ��ϴ�.", ������ ������ "user���� �������ϴ�."�� ���� ����Ѵ�.
	 * �г����� �����ϴ� ���� ������� �ʴ´�.
	 * 
	 * ������ ������ hash���� �����ϵ��� �ߴ�.
	 * �׷��� ���߿� ������� �ű� ��, ������ ������ ��� null�� ������ �Ǵµ�
	 * null���̵��Խ��ϴ�. ó�� ����� �ǹ�����.
	 * 
	 * @param record ä�ù��� ���
	 * @return ������ ä�ù��� ���
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
			answer[i] = hash.get(user.id) + (user.type == 1 ? "���� ���Խ��ϴ�." : "���� �������ϴ�.");
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