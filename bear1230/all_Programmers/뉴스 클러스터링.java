import java.util.*;

class Solution {

	public int solution(String str1, String str2) {
		int answer = 0;
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		List<String> list1 = split(str1);
		List<String> list2 = split(str2);
		int size2 = list2.size();
		List<String> inter = new ArrayList<String>();

		for (String string : list1) {
			for (int i = 0; i < list2.size(); i++) {
				if (string.equals(list2.get(i))) {
					inter.add(string);
					list2.remove(i);
					break;
				}
			}
		}
		if (list1.size() + size2 - inter.size() == 0) {
			return 65536;
		}
		answer = 65536 * inter.size() / (list1.size() + size2 - inter.size());
		return answer;
	}

	public List<String> split(String str) {
		List<String> arr = new ArrayList<String>();
		for (int i = 0; i < str.length() - 1; i++) {
			char temp1 = str.charAt(i);
			char temp2 = str.charAt(i + 1);
			if (('A' <= temp1 && 'Z' >= temp1) && ('A' <= temp2 && 'Z' >= temp2)) {
				arr.add("" + temp1 + temp2);
			}
		}
		return arr;

	}
}
