import java.util.*;
public class Main {
	static String res = "123456780";
	static Map<String, Integer> map = new HashMap<>();
	static Queue<String> q = new LinkedList<>();
	static int resCnt = -1;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int n = sc.nextInt();
				str += n;
			}
		}

		map.put(str, 0);
		q.offer(str);

		while (!q.isEmpty()) {
			str = q.poll();
			int move = map.get(str);

			if (str.equals(res)) {
				resCnt = move;
				break;
			}

			int idx = str.indexOf('0');
			int x = idx / 3;
			int y = idx % 3;

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx >= 0 && ny >= 0 && nx < 3 && ny < 3) {
					int cnt = nx * 3 + ny;
					char ch = str.charAt(cnt);
					String next = str;
					next = next.replace('0', '9');
					next = next.replace(ch, '0');
					next = next.replace('9', ch);

					if (!map.containsKey(next)) {
						q.offer(next);
						map.put(next, move + 1);
					}
				}
			}
		}

		System.out.println(resCnt);
	}
}