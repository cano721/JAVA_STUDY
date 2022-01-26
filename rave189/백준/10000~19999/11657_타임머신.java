package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N개의 도시가 있다.
 * 이 중 하나의 도시에서 다른 도시로 도착하는 M대의 버스가 있다.
 * M개의 버스가 이동하는데 걸리는 시간은 모두 다르며 음수(타임머신)가 나올 수 있다.
 * 1번도시에서 나머지 도시로 이동하는 가장 빠른 시간을 구하는 문제
 * 1번도시에서 다른 도시로 가는 도중 시간을 무한정 되돌린다면(음수 순환) -1만을 출력한다.
 * @author Rave
 *
 */
public class Main {

	static Bus[] map;
	static long INF = Integer.MAX_VALUE;

	/**
	 * 벨만포드 알고리즘을 사용하여 모든 간선을 확인해본다.
	 * 기본적으로 방문한 정점에 대해 모든 간선을 대입해본다.
	 * 위와 같은 동작을 v-1번을 수행한다면 곧 최단거리를 구했다고 할 수 있다.
	 * 여기서 한 번 더 수행하여 갱신하는 노드가 있다면 이는 음수 순환이 발생한다는 것을 알 수 있다.
	 * 최단 거리는 -500*6000*10000 = -300억이므로 음수의 오버플로우가 발생할 수 있기 때문에 long으로 선언해준다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int vertex = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		map = new Bus[edge];
		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			map[i] = new Bus(start, end, weight);
		}
		long[] distance = new long[vertex];
		Arrays.fill(distance, INF);
		distance[0] = 0;
		for (int i = 1; i <= vertex; i++) {
			// 모든 간선을 다 대입해본다.
			for (Bus cur : map)
				if (distance[cur.start] != INF && distance[cur.start] + cur.weight < distance[cur.end]) {
					distance[cur.end] = distance[cur.start] + cur.weight;
					// 음수 순환 발생
					if (i == vertex) {
						System.out.println(-1);
						return;
					}
				}
		}
		// 1번 노드를 제외한 모든 노드의 최단 거리를 출력한다.
		// 최단 거리가 없다면 -1을 출력한다.
		for (int i = 1; i < vertex; i++)
			answer.append(distance[i] == INF ? -1 : distance[i]).append('\n');
		System.out.println(answer);
	}
}

class Bus {
	int start, end, weight;

	public Bus(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
}