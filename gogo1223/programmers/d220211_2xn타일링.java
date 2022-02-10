package programmers;

public class d220211_2xn타일링 {

	public static void main(String[] args) {
		int n = 4;
		int answer = solution(n);
		System.out.println(answer);

	}

	private static int solution(int n) {
		int[] answer = new int[n+1];
		answer[0] = 1;
		answer[1] = 1;
		for (int i = 2; i < answer.length; i++) {
			answer[i] = answer[i-2] + answer[i-1];
		}
		return answer[n];
	}

}
