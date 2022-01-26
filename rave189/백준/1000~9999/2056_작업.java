package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N개의 작업이 있다.
 * 작업들 사이에는 선행 관계가 있어서, 미리 모든 선행 작업들을 수행해야 한다.
 * 또한, K번 작업에 대해 선행 관계가 있는 작업은 1이상 K-1이하이다.
 * 모든 작업을 완료하기 위한 최소 시간을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * 위상 정렬을 이용한 문제.
	 * DP가 가장 빠르다.
	 * 작업에 걸리는 시간은 time에 차수는 edges에 저장하며 모든 작업들의 연관 관계를 설정한다.
	 * 이후 시작시간 + 소요시간이 가장 짧은 순서대로 우선순위큐에서 빼내 최소 시간을 구한다.
	 * 작업이 완료되는 시간이 가장 적은 작업이 앞에 오므로 항상 answer = 작업 완료 시간이다.
	 * 작업이 완료되었으면 그 작업과 연관된 차수가 0인 작업을 우선순위 큐에 넣는다.
	 * 모든 작업을 완료했을 때의 마지막 작업의 작업 완료시간을 출력한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] edges = new int[n + 1];
		int[] time = new int[n + 1];
		map = new HashSet[n + 1];
		for (int i = 1; i <= n; i++)
			map[i] = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			while (count-- > 0) {
				int prev = Integer.parseInt(st.nextToken());
				if (!map[prev].contains(i)) {
					map[prev].add(i);
					edges[i]++;
				}
			}
		}
		PriorityQueue<Task> pq = new PriorityQueue<>();
		int answer = 0;
		for (int i = 1; i <= n; i++)
			if (edges[i] == 0)
				pq.add(new Task(i, time[i], 0));
		while (!pq.isEmpty()) {
			Task cur = pq.poll();
			answer = cur.start + cur.time;
			for (int next : map[cur.num]) {
				edges[next]--;
				if (edges[next] == 0)
					pq.add(new Task(next, time[next], answer));
			}
		}
		System.out.println(answer);
	}
}

class Task implements Comparable<Task> {
	int num, time, start;

	public Task(int num, int time, int start) {
		this.num = num;
		this.time = time;
		this.start = start;
	}

	@Override
	public int compareTo(Task o) {
		return time + start - (o.time + o.start);
	}
}