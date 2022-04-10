package elwlahd555.programmers;

public class 프로그래머스42895_N으로_표현 {
	public static void main(String[] args) {
		int N=5;
		int number=12;
		System.out.println(solution(N, number));
	}
    private static int answer;
    public static int solution(int N, int number) {
		answer = Integer.MAX_VALUE;
		dfs(N, number, 0, 0);
		return answer == Integer.MAX_VALUE ? -1 : answer;
    }
	private static void dfs(int n, int number, int cnt, int prev) {
		if (cnt > 8) {
			answer = -1;
			return;
		}
		if (prev == number) {
			answer = Math.min(answer, cnt);
			return;
		}
		int tempN = n;
		for (int i = 0; i < 8 - cnt; i++) {
			int newCount = cnt + i + 1;
			dfs(n, number, newCount, prev + tempN);
			dfs(n, number, newCount, prev - tempN);
			dfs(n, number, newCount, prev / tempN);
			dfs(n, number, newCount, prev * tempN);

			tempN = tempN * 10 + n;
		}
	}
}
