package elwlahd555.ohou;

import java.util.HashMap;
import java.util.LinkedList;

public class test03 {
	public static void main(String[] args) {
		String tstring = "this is {template} {template} is {state}";
		String variables[][] = { { "template", "{state}" }, { "state", "{template}" } };
		System.out.println(solution(tstring, variables));
	}

	private static String solution(String tstring, String[][] variables) {
		LinkedList<String> que = new LinkedList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> checkMap = new HashMap<String, String>();
		for (int i = 0; i < variables.length; i++) {
			map.put(variables[i][0], variables[i][1]);
			if (variables[i][1].contains("{")) {
				checkMap.put(variables[i][0], variables[i][1]);
			}
		}
		String tempCheck = "";
		for (String s : checkMap.keySet()) {
			tempCheck = checkMap.get(s).replace("{", "").replace("}", "");
			if (!checkMap.containsKey(tempCheck)) {
				checkMap.remove(tempCheck);
			}
		}
		if (checkMap.size() >= 2) {
			String next = "";
			for (String s : checkMap.keySet()) {
				que.add(s);
				next = checkMap.get(s).replace("{", "").replace("}", "");
				que.add(next);
				checkMap.remove(s);
				break;
			}
			while (!checkMap.isEmpty()) {
				if (checkMap.containsKey(next)) {
					String tempNext = checkMap.get(next).replace("{", "").replace("}", "");
					if (que.contains(tempNext)) {
						int size = que.size();
						boolean check = false;
						for (int i = 0; i < size; i++) {
							if (!check) {
								String delString = que.poll();
								if (delString.equals(tempNext)) {
									map.remove(delString);
									checkMap.remove(delString);
									check = true;
								} else {
									que.add(delString);
								}
							} else {
								map.remove(que.poll());
							}
						}
					} else {
						que.add(tempNext);
						next = tempNext;
					}
				} else {
					for (String s : checkMap.keySet()) {
						que.add(s);
						next = checkMap.get(s).replace("{", "").replace("}", "");
						que.add(next);
						checkMap.remove(s);
						break;
					}
				}
			}

		}
		for (String s : map.keySet()) {
			tstring = tstring.replace("{" + s + "}", map.get(s));
		}
		return tstring;
	}
}
