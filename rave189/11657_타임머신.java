package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N���� ���ð� �ִ�.
 * �� �� �ϳ��� ���ÿ��� �ٸ� ���÷� �����ϴ� M���� ������ �ִ�.
 * M���� ������ �̵��ϴµ� �ɸ��� �ð��� ��� �ٸ��� ����(Ÿ�Ӹӽ�)�� ���� �� �ִ�.
 * 1�����ÿ��� ������ ���÷� �̵��ϴ� ���� ���� �ð��� ���ϴ� ����
 * 1�����ÿ��� �ٸ� ���÷� ���� ���� �ð��� ������ �ǵ����ٸ�(���� ��ȯ) -1���� ����Ѵ�.
 * @author Rave
 *
 */
public class Main {

	static Bus[] map;
	static long INF = Integer.MAX_VALUE;

	/**
	 * �������� �˰����� ����Ͽ� ��� ������ Ȯ���غ���.
	 * �⺻������ �湮�� ������ ���� ��� ������ �����غ���.
	 * ���� ���� ������ v-1���� �����Ѵٸ� �� �ִܰŸ��� ���ߴٰ� �� �� �ִ�.
	 * ���⼭ �� �� �� �����Ͽ� �����ϴ� ��尡 �ִٸ� �̴� ���� ��ȯ�� �߻��Ѵٴ� ���� �� �� �ִ�.
	 * �ִ� �Ÿ��� -500*6000*10000 = -300���̹Ƿ� ������ �����÷ο찡 �߻��� �� �ֱ� ������ long���� �������ش�.
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
			// ��� ������ �� �����غ���.
			for (Bus cur : map)
				if (distance[cur.start] != INF && distance[cur.start] + cur.weight < distance[cur.end]) {
					distance[cur.end] = distance[cur.start] + cur.weight;
					// ���� ��ȯ �߻�
					if (i == vertex) {
						System.out.println(-1);
						return;
					}
				}
		}
		// 1�� ��带 ������ ��� ����� �ִ� �Ÿ��� ����Ѵ�.
		// �ִ� �Ÿ��� ���ٸ� -1�� ����Ѵ�.
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