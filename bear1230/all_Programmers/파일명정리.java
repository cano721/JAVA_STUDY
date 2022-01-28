import java.util.*;

class Solution {
	public String[] solution(String[] files) {
		Arrays.sort(files, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String[] file1 = detach(s1);
				String[] file2 = detach(s2);

				int head = file1[0].compareTo(file2[0]);
				if (head == 0) {
					int num1 = Integer.parseInt(file1[1]);
					int num2 = Integer.parseInt(file2[1]);

					return num1 - num2;
				}
				return head;
			}
		});

		return files;
	}

	private String[] detach(String str) {
		String head = "";
		String number = "";

		int idx = 0;
		for (; idx < str.length(); idx++) {
			if (str.charAt(idx) >= '0' && str.charAt(idx) <= '9')
				break;
			head += str.charAt(idx);
		}

		for (; idx < str.length(); idx++) {
			if (str.charAt(idx) < '0' || str.charAt(idx) > '9')
				break;
			number += str.charAt(idx);
		}

		String[] files = { head.toUpperCase(), number };
		return files;
	}
}
