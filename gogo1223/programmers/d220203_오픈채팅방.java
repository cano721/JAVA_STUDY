package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Nicname{
	private String id;
	private String name;
	
	public Nicname(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
public class d220203_오픈채팅방 {

	static final String ENTER = "님이 들어왔습니다.";
	static final String LEAVE = "님이 나갔습니다.";
	
	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] answer = solution(record);
		
		for(String ans : answer) {
			System.out.println(ans);
		}

	}
	
	private static String[] solution(String[] record) {
		//ArrayList<Nicname> list = new ArrayList<Nicname>();
		HashMap<String, String> map = new HashMap<String, String>();
		
		int num = record.length;
		for (int i = 0; i < record.length; i++) {
			String[] info = record[i].split(" ");
			switch(info[0]) {
				case "Enter": 
					map.put(info[1], info[2]);
					break;
				case "Change": 
					num--;
					map.put(info[1], info[2]);
					break;
			}
		}
		String[] answer = new String[num];
		int idx = 0;
		
		for (int i = 0; i < record.length; i++) {
			String[] info = record[i].split(" ");
			switch(info[0]) {
				case "Enter": 
					answer[idx++] = map.get(info[1]) + ENTER;
					break;
				case "Leave":
					answer[idx++] = map.get(info[1]) + LEAVE;
					break;
			}
		}
		return answer;
	}

}
