import java.util.*;
class Solution {
    public int[] solution(String msg) {
        StringBuilder sb = new StringBuilder();

		ArrayList<Integer> arr = new ArrayList<Integer>();

		HashMap<String, Integer> map = new HashMap<>(); // A 65 ~ +26
		for (int i = 1; i < 27; i++) {
			String val = String.valueOf((char) (i + 64));
			map.put(val, i);
		}

		int ch = 27;
        for(int i = 0; i < msg.length(); ){
			String str = "";
			boolean check = false;
			
			String s = str + String.valueOf(msg.charAt(i));

			while (map.containsKey(s)) {
				str += msg.charAt(i);
				i++;
				if(i < msg.length()) {
					s += msg.charAt(i);
				}
				else {
					check = true;
					break;
				}
			}
			if(!check) map.put(s, ch++);
			arr.add(map.get(str));
		}
		
		int []answer = new int[arr.size()];
		for(int k=0; k<arr.size(); k++) {
			answer[k] = arr.get(k);
		}
        return answer;
    }
}