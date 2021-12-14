package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * N개의 도시가 있다.
 * 이 도시에는 M개의 도로와 W개의 웜홀이 있다.
 * 한 도시에서 다른 도시로 도로를 이용하면 거리만큼 시간이 증가한다.
 * 하지만 웜홀을 이용하면 시간이 줄어든다.
 * 이 때, 어느 한 지점에서 출발하여 다시 시작 지점으로 돌아왔을 때,
 * 시간이 되돌아간 경우가 있는지 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 벨만 포드 알고리즘을 이용해서 푸는 문제
	 * distance의 값이 무엇인지는 하나도 중요하지 않다.
	 * 그저 모든 간선을 n번만큼 탐색하여 음수 사이클이 존재하는지만 확인하면 된다.
	 * 음수가 작은 수여서 사이클이 생기지 않는 경우 도착지점쪽이 갱신되지 않고, 도착지점이 갱신되지 않으므로 시작 지점도 갱신되지 않는다.
	 * 음수 사이클인 경우 도착 지점도 작아질 것이고, 시작 지점도 작아질 것이기에 갱신이 되므로 판별이 가능하다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int[] distance = new int[n];
			LinkedList<Point> roads = new LinkedList<>();
			while (m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				roads.add(new Point(start, end, weight));
				roads.add(new Point(end, start, weight));
			}
			while (w-- > 0) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				roads.add(new Point(start, end, -weight));
			}
			boolean isCycle = false;
			loop: for (int i = 1; i <= n; i++) {
				for (Point cur : roads) {
					if (distance[cur.x] + cur.weight < distance[cur.y]) {
						distance[cur.y] = distance[cur.x] + cur.weight;
						if (i == n) {
							isCycle = true;
							break loop;
						}
					}
				}
			}
			answer.append(isCycle ? "YES" : "NO").append('\n');
		}
		System.out.print(answer);
	}
}

class Point {
	int x, y, weight;

	public Point(int x, int y, int weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}
}