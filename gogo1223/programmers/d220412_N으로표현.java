package programmers;

public class d220412_N으로표현 {

	public static void main(String[] args) {
		int N = 5;
		int number = 12;
		int answer = solution(N, number);
		System.out.println(answer);
/* 5	12	4
 * 2	11	3
 */
	}
	static int answer = -1;
	private static int solution(int n, int number) {

		dfs(n, number, 0, 0);
		
		return answer;
	}

	private static void dfs(int n, int number, int depth, int result) {
		if(depth > 8) return;
		else if(result == number) {
			if(answer == -1) answer = depth;
			else answer = Math.min(answer, depth);
			return;
		}
		int init = n;
		for(int i=1;i<=8-depth;i++) {
			dfs(n, number, i+depth, result+init);
			dfs(n, number, i+depth, result-init);
			dfs(n, number, i+depth, result*init);
			dfs(n, number, i+depth, result/init);
			init = (10*init) + n;
		}
		
		
	}

}
