package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int N = 
//				5;
				6;
		int[][] road =
//				{{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
		{{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
		int K = 
//				3;
				4;
		System.out.println(solution.solution(N, road, K));
	}
}

class Solution {
	LinkedList<Road>[] map;
	int[] distance;

	/**
	 * N���� ������ ���ΰ� ǥ�õ� ������ �־�����.
	 * �� ���δ� ������̸� ���� ���� ������ �̵��ϴµ� �ɸ��� �ð��� �����Ѵ�.
	 * 1�� ������ �ִ� ������������ K�ð� ���Ϸ� �ɸ��� ������ ����� �������� �Ѵ�.
	 * 1�� �������� ����� �� �ִ� ������ ���� ���ϴ� ����
	 * @param N ������ ����
	 * @param road ������ ����
	 * @param K ��� ������ �ð�
	 * @return ��� ������ ������ ��
	 */
	public int solution(int N, int[][] road, int K) {
		int answer = 0;
		init(N, road);
		dijkstra(1);
		for (int i = 1; i <= N; i++)
			if (distance[i] <= K)
				answer++;
		return answer;
	}

	public void init(int N, int[][] roads) {
		map = new LinkedList[N + 1];
		distance = new int[N + 1];
		for (int i = 1; i <= N; i++)
			map[i] = new LinkedList<>();
		for (int[] road : roads) {
			int start = road[0];
			int end = road[1];
			int weight = road[2];
			map[start].add(new Road(end, weight));
			map[end].add(new Road(start, weight));
		}
		Arrays.fill(distance, Integer.MAX_VALUE);
	}

	public void dijkstra(int start) {
		distance[start] = 0;
		PriorityQueue<Road> pq = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
		pq.add(new Road(start, 0));
		while (!pq.isEmpty()) {
			Road cur = pq.poll();
			for (Road next : map[cur.point]) {
				if (cur.weight + next.weight < distance[next.point]) {
					distance[next.point] = cur.weight + next.weight;
					pq.add(new Road(next.point, distance[next.point]));
				}
			}
		}
	}
}

class Road {
	int point, weight;

	public Road(int point, int weight) {
		this.point = point;
		this.weight = weight;
	}
}