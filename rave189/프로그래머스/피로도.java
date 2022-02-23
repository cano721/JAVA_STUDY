package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	int answer = 0;
	boolean[] visited;
	int[][] dungeons;

	/**
	 * 남은 피로도가 주어졌을 때, 던전을 돌 수 있는 최대 개수를 구하는 문제
	 * 던전은 한 번만 들어갈 수 있다.
	 * 던전의 정보는 [최소 필요 피로도, 소모 피로도]로 주어진다.
	 * @param k 남은 피로도
	 * @param dungeons 던전들의 정보
	 * @return 돌 수 있는 최대 던전 개수
	 */
	public int solution(int k, int[][] dungeons) {
		init(dungeons);
		bruteforce(k, 0);
		return answer;
	}

	public void init(int[][] dungeons) {
		visited = new boolean[dungeons.length];
		this.dungeons = dungeons;
	}

	public void bruteforce(int k, int count) {
		for (int i = 0; i < dungeons.length; i++) {
			if (visited[i] || dungeons[i][0] > k)
				continue;
			visited[i] = true;
			bruteforce(k - dungeons[i][1], count + 1);
			visited[i] = false;
		}
		answer = Math.max(answer, count);
	}
}