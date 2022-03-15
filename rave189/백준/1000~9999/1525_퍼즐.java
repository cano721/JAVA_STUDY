package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int SIZE = 3;
	static String lastLocation = "123456780";
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start = "";
		for (int i = 0; i < SIZE; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < SIZE; j++)
				start += st.nextToken();
		}
		System.out.println(bfs(start));
	}

	public static int bfs(String start) {
		HashSet<String> hash = new HashSet<>();
		Queue<char[]> q = new LinkedList<>();
		q.add(start.toCharArray());
		hash.add(start);
		int move = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				char[] cur = q.poll();
				if (isEquals(cur))
					return move;
				int zeroIdx = getZeroIndex(cur);
				int x = zeroIdx / SIZE;
				int y = zeroIdx % SIZE;
				for (int i = 0; i < dx.length; i++) {
					int nextX = x + dx[i];
					int nextY = y + dy[i];
					int nextPosition = nextX * SIZE + nextY;
					if (!((0 <= nextX && nextX < SIZE) && (0 <= nextY && nextY < SIZE)))
						continue;
					swap(cur, zeroIdx, nextPosition);
					String next = new String(cur);
					if (!hash.contains(next)) {
						q.add(next.toCharArray());
						hash.add(next);
					}
					swap(cur, nextPosition, zeroIdx);
				}
			}
			move++;
		}
		return -1;
	}

	public static int getZeroIndex(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '0')
				return i;
		}
		return -1;
	}

	public static void swap(char[] arr, int first, int second) {
		char tmp = arr[first];
		arr[first] = arr[second];
		arr[second] = tmp;
	}

	public static boolean isEquals(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != lastLocation.charAt(i))
				return false;
		}
		return true;
	}
}