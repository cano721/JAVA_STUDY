package programmers;

public class d220312_n진수게임 {

	public static void main(String[] args) {
		int n = 2; 
		int t = 4;
		int m = 2;
		int p = 1;
		//진법 n, 미리 구할 숫자의 갯수 t, 게임에 참가하는 인원 m, 튜브의 순서 p
		String ans = solution(n, t, m, p);
		System.out.println(ans);
	}

	private static String solution(int n, int t, int m, int p) {
		String answer = "";

        int num = 0;
        int totCnt = ((t - 1) * m) + p; // 돌아야 하는 총 순서의 수
        StringBuilder sb = new StringBuilder(" ");
        int count = 0;
        while (count < totCnt) {
            String N = getConventN(num, n); // 숫자를 N진수로 변환
            for (int i = 0; i < N.length(); i++) {
                sb.append(N.charAt(i));
                count++;
            }
            num++;
        }

        for (int i = p; i < sb.length(); i += m) {
            answer += sb.charAt(i);
        }

        return answer.substring(0, t);
	}
	// 10진수를 N진수로 변환
	private static String getConventN(int num, int n) {
        String result = "";
        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            int share = num / n;
            int remainder = num % n;
            if (remainder > 9) {
                result = (char) (remainder + 55) + result;
            } else {
                result = remainder + result;
            }
            num = share;
        }
        return result;
    }

}
