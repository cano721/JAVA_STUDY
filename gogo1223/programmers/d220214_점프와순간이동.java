package programmers;

public class d220214_점프와순간이동 {

	public static void main(String[] args) {
		int n = 6;
		int answer = solution(n);
		System.out.println(answer);
	}

	private static int solution(int n) {
		int answer = 0;
		while(n != 0) {
			if(n % 2 == 0) {
				n /= 2;
			}else {
				n -= 1;
				answer++;
			}
		}
		return answer;
	}

}
