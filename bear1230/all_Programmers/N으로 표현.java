class Solution {
	int answer;

	public int solution(int N, int number) {
		answer = -1;
		DFS(N, number, 0, 0);
		return answer;
	}

	public void DFS(int N, int number, int sum, int count) {
		if (count > 8)
			return;
		if (sum == number) {
			if (count < answer || answer == -1)
				answer = count;
			return;
		}
		int tmpN = 0;
		for (int i = 1; i < 9; i++) {
			tmpN = tmpN * 10 + N;
			DFS(N, number, sum + tmpN, count + i);
			DFS(N, number, sum - tmpN, count + i);
			DFS(N, number, sum * tmpN, count + i);
			DFS(N, number, sum / tmpN, count + i);
		}

	}
}
