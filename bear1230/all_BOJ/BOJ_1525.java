import java.io.*;
import java.util.*;

/*
백준 퍼즐 - 1525
 * 블로그 참조 https://loosie.tistory.com/253
 * */
public class Main {
	static Map<String, Integer> map = new HashMap<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		String init = "";
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				int num = Integer.parseInt(st.nextToken());
				init += num;
			}
		}

		map.put(init, 0);
		System.out.println(bfs(init));
	}

	static int bfs(String init) {
		Queue<String> que = new LinkedList<>();
		que.add(init);
		
		while (!que.isEmpty()) {
			String pos = que.poll();
			int move = map.get(pos);
			int empty = pos.indexOf('0');
			int px = empty % 3;
			int py = empty / 3;

			if (pos.equals("123456780")) {
				return move;
			}

			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];

				if (nx < 0 || ny < 0 || nx > 2 || ny > 2)
					continue;
		
				char ch = pos.charAt(ny * 3 + nx);
				String next = pos.replace(ch, 'c');
				next = next.replace('0', ch);
				next = next.replace('c', '0');

				if (!map.containsKey(next)) {
					que.add(next);
					map.put(next, move + 1);
				}
			}
		}
		return -1;

	}

}