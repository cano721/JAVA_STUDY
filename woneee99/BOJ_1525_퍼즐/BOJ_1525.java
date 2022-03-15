import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1525 {
	static Queue<String> q;
	static HashMap<String, Integer> map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		q = new LinkedList<>(); // bfs 돌리기
		map = new HashMap<>(); // 중복 체크

		int k = 0;
		String str = "";
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			str += Integer.parseInt(st.nextToken());
			str += Integer.parseInt(st.nextToken());
			str += Integer.parseInt(st.nextToken());
		}

		if (str.equals("123456780"))
			System.out.println("0");
		else {
			map.put(str, 0);
			q.offer(str);
			System.out.println(bfs());
		}

	}

	static int bfs() {
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		while (!q.isEmpty()) {
			String str = q.poll();
			int zero = str.indexOf("0");
			int row = zero / 3;
			int col = zero % 3;

			for (int i = 0; i < 4; i++) {
				int x = dx[i] + row;
				int y = dy[i] + col;

				if (x < 0 || y < 0 || x >= 3 || y >= 3)
					continue;

				int idx = 3 * x + y;

				StringBuilder sb = new StringBuilder(str);
				char ch = sb.charAt(idx);
				sb.setCharAt(idx, '0');
				sb.setCharAt(zero, ch);

				if (sb.toString().equals("123456780"))
					return map.get(str) + 1;
				if (!map.containsKey(sb.toString())) {
					q.offer(sb.toString());
					map.put(sb.toString(), map.get(str) + 1);
				}

			}
		}
		return -1;

	}
}
