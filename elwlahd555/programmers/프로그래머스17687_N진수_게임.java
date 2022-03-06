package elwlahd555.programmers;

import java.util.LinkedList;

public class 프로그래머스17687_N진수_게임 {
	public static void main(String[] args) {
		int n=2;
		int t=4;
		int m=2;
		int p=1;
		System.out.println(solution(n, t, m, p));
	}
    public static String solution(int n, int t, int m, int p) {
		StringBuffer answer = new StringBuffer();
		LinkedList<Character> que = new LinkedList<>();
		que.add('0');
		outer: for (int i = 0; i <= 1000000; i++) {
			StringBuffer sb = new StringBuffer();
			int temp = i;
			while (temp != 0) {
				if (temp % n >= 10) {
					sb.append((char) ('A' + temp % n - 10));
				} else {
					sb.append(temp % n);

				}
				temp /= n;
			}
			sb = sb.reverse();
			for (int j = 0; j < sb.length(); j++) {

				que.add(sb.charAt(j));
				if (que.size() == t * m) {
					break outer;
				}
			}
		}
		int size = que.size();
		for (int i = 0; i < size; i++) {
			if (i % m + 1 == p) {
				answer.append(que.poll());
			} else {
				que.poll();
			}
		}
		return answer.toString();
    }
}
