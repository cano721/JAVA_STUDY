package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Point> houses = new ArrayList<>();
	static ArrayList<Point> chickens = new ArrayList<>();
	static Point[] selected;
	static int answer = Integer.MAX_VALUE;
	static int m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int v = Integer.parseInt(st.nextToken());
				if (v == 1)
					houses.add(new Point(i, j));
				else if (v == 2)
					chickens.add(new Point(i, j));
			}
		}
		selected = new Point[m];
		bruteforce(0, 0);
		System.out.println(answer);
	}

	public static void bruteforce(int depth, int cur) {
		if (depth == m) {
			answer = Math.min(answer, calc());
			return;
		}

		for (int i = cur; i < chickens.size(); i++) {
			selected[depth] = chickens.get(i);
			bruteforce(depth + 1, i + 1);
		}
	}

	public static int calc() {
		int sum = 0;
		for (Point house : houses) {
			int distance = Integer.MAX_VALUE;
			for (Point chicken : selected)
				distance = Math.min(distance, house.getDistance(chicken));
			sum += distance;
		}
		return sum;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getDistance(Point o) {
		return Math.abs(x - o.x) + Math.abs(y - o.y);
	}
}