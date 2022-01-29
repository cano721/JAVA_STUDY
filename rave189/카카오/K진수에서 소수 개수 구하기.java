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
	 * ���� ���� N�� �־�����.
	 * �� ������ K������ �ٲ��� �� ���ǿ� �´� �Ҽ��� �� �� ���� ���ϴ� ����
	 * ������ ������ ����.
	 * 0P0ó�� �Ҽ� ���ʿ� 0�� �ִ� ���
	 * P0ó�� �Ҽ� �����ʿ��� 0�� �ְ� ���ʿ��� �ƹ��͵� ���� ���
	 * 0Pó�� �Ҽ� ���ʿ��� 0�� �ְ� �����ʿ��� �ƹ��͵� ���� ���
	 * Pó�� �Ҽ� ���ʿ� �ƹ��͵� ���� ���
	 * ��, P�� �� �ڸ����� 0�� �������� �ʴ� �Ҽ��Դϴ�.
	 * - ���� ���, 101�� P�� �� �� �����ϴ�.
	 * 
	 * K������ �ٲ� �� 0�� " "�� �ٲٰ� StringTokenizer�� �ϳ��� �޴´�.
	 * ���� ���� �Ҽ����� Ȯ���Ͽ� ������ ������ ������Ų��.
	 * @param n ���� ����
	 * @param k �ٲ� ����
	 * @return ���ǿ� �´� �Ҽ��� ����
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