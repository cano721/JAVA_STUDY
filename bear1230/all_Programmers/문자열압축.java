class Solution {
	public static int solution(String s) {
		int answer = s.length();

		for (int i = 1; i <= s.length() / 2; i++) {
			String result = "";
			String str = s.substring(0, i);
			int count = 1;

			for (int j = i; j <= s.length() - i; j += i) {
				String com = s.substring(j, j + i);
				if (str.equals(com)) {
					count++;
				} else {
					if (count != 1)
						result += count;
					result += str;

					str = com;
					count = 1;
				}
			}
			if (count != 1)
				result += count;
			result += str;

			if (s.length() / i != 0) {
				result += s.substring((s.length() / i) * i);
			}
			answer = Math.min(answer, result.length());
		}
		return answer;
	}
}
