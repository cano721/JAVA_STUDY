import java.util.*;

class Solution {
	long answer = 0;
	char[] arr = { '*', '-', '+' };
	boolean[] check = new boolean[3];

	public long solution(String expression) {
		char[][] arr = { { '+', '-', '*' }, { '+', '*', '-' }, { '-', '+', '*' }, { '-', '*', '+' }, { '*', '+', '-' },
				{ '*', '-', '+' } };
		ArrayList<Long> numlist = new ArrayList<Long>();
		ArrayList<Character> oplist = new ArrayList<Character>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c == '-' || c == '+' || c == '*') {
				oplist.add(c);
				String num = sb.toString();
				sb.delete(0, num.length());
				numlist.add(Long.parseLong(num));
			} else {
				sb.append(c);
			}
		}
		numlist.add(Long.parseLong(sb.toString()));

		for (char[] list : arr) {
			ArrayList<Long> n_numlist = new ArrayList<Long>(numlist);
			ArrayList<Character> n_oplist = new ArrayList<Character>(oplist);
			for (int i = 0; i < 3; i++) {
				while (n_oplist.contains(list[i])) {
					int idx = n_oplist.indexOf(list[i]);
					if (n_oplist.get(idx) == list[i]) {
						if (list[i] == '*') {
							n_numlist.set(idx, n_numlist.get(idx) * n_numlist.get(idx + 1));
						} else if (list[i] == '-') {
							n_numlist.set(idx, n_numlist.get(idx) - n_numlist.get(idx + 1));
						} else if (list[i] == '+') {
							n_numlist.set(idx, n_numlist.get(idx) + n_numlist.get(idx + 1));
						}
						n_numlist.remove(idx + 1);
						n_oplist.remove(idx);
					}
				}
			}
			answer = Math.max(Math.abs(n_numlist.get(0)), answer);
		}
		return answer;
	}
}
