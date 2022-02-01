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
	 * N개의 마을과 도로가 표시된 지도가 주어진다.
	 * 각 도로는 양방향이며 각각 다음 마을로 이동하는데 걸리는 시간이 존재한다.
	 * 1번 마을에 있는 음식점에서는 K시간 이하로 걸리는 마을만 배달을 받으려고 한다.
	 * 1번 마을에서 배달할 수 있는 마을의 수를 구하는 문제
	 * @param N 마을의 개수
	 * @param road 도로의 정보
	 * @param K 배달 가능한 시간
	 * @return 배달 가능한 마을의 수
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