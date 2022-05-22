package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static List<Computer>[] map;
	static int[] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()) - 1;
			map = new List[n];
			distance = new int[n];
			Arrays.fill(distance, Integer.MAX_VALUE);
			for (int i = 0; i < n; i++)
				map[i] = new ArrayList<>();
			while (d-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int time = Integer.parseInt(st.nextToken());
				map[b].add(new Computer(a, time));
			}
			dijkstra(c);
			int infectionComputerCnt = 0, timeToInfectionLastComputer = 0;
			for (int i = 0; i < distance.length; i++) {
				if (distance[i] == Integer.MAX_VALUE)
					continue;
				infectionComputerCnt++;
				timeToInfectionLastComputer = Math.max(timeToInfectionLastComputer, distance[i]);
			}
			answer.append(infectionComputerCnt).append(' ').append(timeToInfectionLastComputer).append('\n');
		}
		System.out.print(answer);
	}

	public static void dijkstra(int start) {
		PriorityQueue<Computer> pq = new PriorityQueue<>((v1, v2) -> v1.time - v2.time);
		distance[start] = 0;
		pq.add(new Computer(start, 0));
		while (!pq.isEmpty()) {
			Computer cur = pq.poll();
			for (Computer next : map[cur.no]) {
				if (cur.time + next.time < distance[next.no]) {
					distance[next.no] = cur.time + next.time;
					pq.add(new Computer(next.no, distance[next.no]));
				}
			}
		}
	}
}

class Computer {
	int no, time;

	public Computer(int no, int time) {
		this.no = no;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Computer [no=" + no + ", time=" + time + "]";
	}
}