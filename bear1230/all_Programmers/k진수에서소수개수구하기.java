import java.util.*;

class Solution {
	public int solution(int n, int k) {
		int answer = 0;

		StringBuilder sb = new StringBuilder();
		while (n >= k) {
			sb.append(n % k);
			n = n / k;
		}
		sb.append(n);
		String res = sb.reverse().toString();

		String[] arr = res.split("0");
		for (String s : arr) {
			if (!s.isEmpty() && checkNum(Long.valueOf(s))) {
				answer += 1;
			}
		}
		return answer;
	}

	static public boolean checkNum(long n) {
		if (n <= 1)
			return false;
		else if (n == 2)
			return true;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}

		return true;
	}
	
}
