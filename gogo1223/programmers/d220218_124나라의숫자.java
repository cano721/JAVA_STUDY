package programmers;

public class d220218_124나라의숫자 {

	public static void main(String[] args) {
		int n = 9;
		System.out.println(solution(n));	//44

	}

	private static String solution(int n) {
		String answer = "";
		while(n > 0){
            int k = n % 3;
            n = k == 0? n-1 : n;
            n /= 3;
            switch(k){
                case 0: answer = "4" + answer; break;
                case 1: answer = "1" + answer; break;
                case 2: answer = "2" + answer; break;
            }
        }
		return answer;
	}

}
