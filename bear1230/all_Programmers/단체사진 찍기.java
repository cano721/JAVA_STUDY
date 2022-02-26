class Solution {
	int temp;

	public int solution(int n, String[] data) {
		int answer = 0;

		temp = 0;
		char[] friend = { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
		boolean[] visit = new boolean[8];

		permutation(data, friend, visit, 0, new char[8]);

		return answer = temp;
	}

	public void permutation(String[] data, char[] friend, boolean[] visit, int cnt, char[] str) {

		if (cnt == 8) {
			if (check(data, str))
				temp++;
			return;
		}

		for (int i = 0; i < 8; i++) {
			if (visit[i])
				continue;

			visit[i] = true;
			str[cnt] = friend[i];
			permutation(data, friend, visit, cnt + 1, str);
			visit[i] = false;
		}
	}

	public boolean check(final String[] data, final char[] str) {

		for (String d : data) {
			char f1 = d.charAt(0);
			char f2 = d.charAt(2);
			char op = d.charAt(3);
			int diff = d.charAt(4) - '0';

			int fIdx1 = 0;
			int fIdx2 = 0;

			for (int i = 0; i < str.length; i++) {
				if (str[i] == f1) {
					fIdx1 = i;
				}

				if (str[i] == f2) {
					fIdx2 = i;
				}
			}

			boolean res = false;
			int realDiff = Math.abs(fIdx1 - fIdx2) - 1;
			switch (op) {
			case '=':
				if (realDiff == diff)
					res = true;
				break;
			case '<':
				if (realDiff < diff)
					res = true;
				break;
			case '>':
				if (realDiff > diff)
					res = true;
				break;
			}

			if (!res)
				return false;
		}

		return true;
	}
}
