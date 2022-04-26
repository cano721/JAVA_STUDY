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
	 * �� ��帶�� ��� ���밡 �ִ�.
	 * ��带 �湮�Ҷ����� ��忡 �ִ� ������ ����´�.
	 * ���� ���� ���������� ������ �������� ���ų� ũ�� ���� ��Ƹ�����.
	 * ���� ������ ��Ʈ ���� ���ƿ����� �� ��, ������ �� �ִ� �ִ� ���� �������� ���ϴ� ����
	 * 
	 * ���� ��带 �Ķ���ͷ� ������شٴ� �� �� �ż�����.
	 * ���� �̷������� �����ϸ� ������ �ð� �ʰ� �޸� �ʰ� 100�۶� �������� ���ϰ� �־��µ�
	 * ���� ��� ������ addAll�� �� �����ھƵ� ��κ� 1ms, �ִ� 16ms������ ����
	 * �ణ �㹫�ϳ�
	 * @param info �� ��忡 �ִ� ������ ����
	 * @param edges Ʈ���� ����
	 * @return ���� ������
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