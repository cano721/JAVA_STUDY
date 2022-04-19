package Programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = // 3;
				4;
		int start = 1;
		int end = // 3;
				4;
		int[][] roads = // { { 1, 2, 2 }, { 3, 2, 3 } };
				{ { 1, 2, 1 }, { 3, 2, 1 }, { 2, 4, 1 } };
		int[] traps = // { 2 };
				{ 2, 3 };
		System.out.println(solution.solution(n, start, end, roads, traps));
	}
}

class Solution {
	List<Node>[] map;
	List<Node>[] reverseMap;
	HashSet<Integer> trapList = new HashSet<>();

	public int solution(int n, int start, int end, int[][] roads, int[] traps) {
		init(n, roads, traps);
		return bfs(start - 1, end - 1);
	}

	public void init(int n, int[][] roads, int[] traps) {
		map = new List[n];
		reverseMap = new List[n];
		for (int i = 0; i < n; i++) {
			map[i] = new ArrayList<>();
			reverseMap[i] = new ArrayList<>();
		}
		for (int[] road : roads) {
			int p = road[0] - 1, q = road[1] - 1, s = road[2];
			map[p].add(new Node(q, s, 0));
			reverseMap[q].add(new Node(p, s, 0));
		}
		for (int trap : traps) {
			trapList.add(trap - 1);
		}
	}

	public int bfs(int start, int end) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start, 0, 0));
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.p == end) {
				return cur.time;
			}
			for (Node next : cur.trapCnt % 2 == 0 ? map[cur.p] : reverseMap[cur.p]) {
				int nextTrapCnt = trapList.contains(next.p) ? cur.trapCnt + 1 : cur.trapCnt;
				q.add(new Node(next.p, cur.time + next.time, nextTrapCnt));
			}
		}
		return -1;
	}
}

class Node {
	int p, time, trapCnt;

	public Node(int p, int time, int trapCnt) {
		this.p = p;
		this.time = time;
		this.trapCnt = trapCnt;
	}
}