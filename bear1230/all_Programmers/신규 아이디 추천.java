class Solution {
	public String solution(String new_id) {
		String answer = "";

		new_id = new_id.toLowerCase();

		for (int i = 0; i < new_id.length(); i++) {
			char ch = new_id.charAt(i);
			if (Character.isLetterOrDigit(ch) || ch == '-' || ch == '_') {
				answer += ch;
			} else if (ch == '.') {
				if (answer.length() > 0 && answer.charAt(answer.length() - 1) != '.')
					answer += '.';
			}

		}
		if (answer == "") {
			answer += "a";
		}

		int len = answer.length();

		if (len - 1 >= 0 && answer.charAt(len - 1) == '.') {
			answer = answer.substring(0, len - 1);
		}

		len = answer.length();

		if (len > 15) {
			answer = answer.substring(0, 15);
		} else if (len < 3) {
			char ch = answer.charAt(answer.length() - 1);
			while (answer.length() < 3) {
				answer += ch;
			}
		}

		len = answer.length();

		if (len - 1 >= 0 && answer.charAt(len - 1) == '.') {
			answer = answer.substring(0, len - 1);
		}

		return answer;
	}
}
