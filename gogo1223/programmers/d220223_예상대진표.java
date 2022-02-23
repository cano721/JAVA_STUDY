package programmers;

public class d220223_예상대진표 {

	public static void main(String[] args) {
		int n = 8; 
		int a = 4;
		int b = 7;
		int ans = solution(n, a, b);
		System.out.println(ans);
	}

	private static int solution(int n, int a, int b) {
		int answer = 1;
		int temp = 0;
		if(a > b) {
			temp = a;
			a = b;
			b = temp;
		}
		while(true) {
            if(a % 2 == 1 && b - a == 1) break;
			if(a % 2 == 1) a++;
			if(b % 2 == 1) b++;
			a /= 2;
			b /= 2;
			
			answer++;
		}
		
		return answer;
    }

}
