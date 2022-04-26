package Programmers;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	Set<Integer>[] map;
	int[] info;
	int answer = 0;

	/**
	 * 각 노드마다 양와 늑대가 있다.
	 * 노드를 방문할때마다 노드에 있는 동물이 따라온다.
	 * 만약 양의 마릿수보다 늑대의 마릿수가 같거나 크면 양이 잡아먹힌다.
	 * 양을 데리고 루트 노드로 돌아오려고 할 때, 데려올 수 있는 최대 양의 마릿수를 구하는 문제
	 * 
	 * 다음 노드를 파라미터로 만들어준다는 건 좀 신선했음.
	 * 보통 이런식으로 구현하면 무조건 시간 초과 메모리 초과 100퍼라 생각조차 안하고 있었는데
	 * 다음 노드 구성도 addAll로 다 때려박아도 대부분 1ms, 최대 16ms안으로 끝남
	 * 약간 허무하네
	 * @param info 각 노드에 있는 동물의 정보
	 * @param edges 트리의 정보
	 * @return 양의 마릿수
	 */
	public int solution(int[] info, int[][] edges) {
		init(info, edges);
		bruteforce(0, 0, 0, map[0]);
		return answer;
	}

	public void init(int[] info, int[][] edges) {
		this.info = info;
		map = new Set[info.length];
		for (int i = 0; i < map.length; i++) {
			map[i] = new HashSet<>();
		}
		for (int[] edge : edges) {
			int first = edge[0];
			int second = edge[1];
			map[first].add(second);
		}
	}

	public void bruteforce(int cur, int sheep, int wolf, Set<Integer> next) {
		sheep += info[cur] ^ 1;
		wolf += info[cur];
		if (wolf >= sheep) {
			return;
		}
		answer = Math.max(answer, sheep);
		for (int nextNode : next) {
			Set<Integer> newNext = new HashSet<>();
			newNext.addAll(next);
			newNext.remove(nextNode);
			newNext.addAll(map[nextNode]);
			bruteforce(nextNode, sheep, wolf, newNext);
		}
	}
}