/*
 * Integer.toString(int num, int N진법) 
 * 해당 진법 리턴
 */
public class PG_N진수게임 {

	public String solution(int n, int t, int m, int p) {
		
		String tmp = "";
		String result = "";
		for (int i = 0; i < m * t; i++) {
			tmp += Integer.toString(i, n);
		}

		for (int i = 0; i < t; i++) {
			result += tmp.charAt(i * m + p - 1);
		}
		
		return result.toUpperCase();
	}
}