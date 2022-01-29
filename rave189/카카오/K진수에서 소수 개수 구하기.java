package Programmers;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		int n = 110011;
		int k = 10;
		System.out.println(s.solution(n, k));
	}
}

class Solution {
	ArrayList<Long> arr = new ArrayList<>();

	/**
	 * 양의 정수 N이 주어진다.
	 * 이 정수를 K진수로 바꿨을 때 조건에 맞는 소수가 몇 개 인지 구하는 문제
	 * 조건은 다음과 같다.
	 * 0P0처럼 소수 양쪽에 0이 있는 경우
	 * P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
	 * 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
	 * P처럼 소수 양쪽에 아무것도 없는 경우
	 * 단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
	 * - 예를 들어, 101은 P가 될 수 없습니다.
	 * 
	 * K진수로 바꾼 뒤 0을 " "로 바꾸고 StringTokenizer로 하나씩 받는다.
	 * 받은 값이 소수인지 확인하여 맞으면 개수를 증가시킨다.
	 * @param n 양의 정수
	 * @param k 바꿀 진수
	 * @return 조건에 맞는 소수의 개수
	 */
	public int solution(int n, int k) {
		int answer = 0;
		String kString = getKString(n, k);
		splitZero(kString);
		for (long num : arr)
			if (isPrime(num))
				answer++;
		return answer;
	}

	public String getKString(int n, int k) {
		StringBuilder result = new StringBuilder();
		while (n > k) {
			result.append(n % k);
			n /= k;
		}
		result.append(n % k);
		return result.reverse().toString();
	}

	public void splitZero(String str) {
		StringTokenizer st = new StringTokenizer(str.replaceAll("0", " "));
		while (st.hasMoreTokens())
			arr.add(Long.parseLong(st.nextToken()));
	}

	public boolean isPrime(long n) {
		if (n < 2)
			return false;
		for (long i = 2; i * i <= n; i++)
			if (n % i == 0)
				return false;
		return true;
	}
}