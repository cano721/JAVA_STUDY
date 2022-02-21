package programmers;

import java.util.Arrays;

public class d220221_HIndex {

	public static void main(String[] args) {
		int[] citations = {3, 0, 6, 1, 5};
		int ans = solution(citations);
		System.out.println(ans);

	}

	private static int solution(int[] citations) {
		Arrays.sort(citations);
		int answer = 0;
		for (int i = 0; i < citations.length; i++) {
			if(citations[i] >= citations.length - i) {
				answer = citations.length - i;
                break;
			}
		}
		return answer;
	}

}
