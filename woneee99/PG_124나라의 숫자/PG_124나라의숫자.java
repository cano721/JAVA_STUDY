
class PG_124나라의숫자 {
	public String solution(int n) {
		StringBuilder sb = new StringBuilder();
		String[] tmp = { "4", "1", "2" };
		while (true) {
			if (n <= 3) {
				sb.append(tmp[n % 3]);
				break;
			}
			sb.append(tmp[n % 3]);
			if (n % 3 == 0) n -= 1;
			n = n / 3;
		}
		String answer = sb.reverse().toString();
		return answer;
	}
}
