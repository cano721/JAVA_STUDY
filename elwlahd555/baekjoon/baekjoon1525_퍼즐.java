package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon1525_퍼즐 {
	static String correct = "123456780";
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<String> que = new LinkedList<String>();
		String start = "";
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				start += st.nextToken();
			}
		}
		int answer = -1;
		map.put(start, 0);
		que.add(start);
		while (!que.isEmpty()) {
			String q = que.poll();
			int move = map.get(q);
			int empty = q.indexOf('0');

			if (q.equals(correct)) {
				answer = move;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int x = empty % 3 + dx[i];
				int y = empty / 3 + dy[i];

				if (x < 0 || x > 2 || y < 0 || y > 2) {
					continue;
				}
				int nq = y * 3 + x;
				char ch = q.charAt(nq);
				String next = q.replace(ch, 'k');
				next = next.replace('0', ch);
				next = next.replace('k', '0');
				if (!map.containsKey(next)) {
					que.add(next);
					map.put(next, move + 1);
				}
			}
		}
		System.out.println(answer);
	}
}
