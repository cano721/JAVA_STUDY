package 전체유형문제풀이.프로그래머스;

import java.util.ArrayList;
import java.util.Collections;

public class PG92335_k진수에서소수개수구하기 {

	public static void main(String[] args) {
		int n = 110011;
		int k = 10;

		int result = solution(n, k);
		System.out.println(result);

	}

	private static int solution(int n, int k) {
		int answer = 0;

		ArrayList<Integer> changeList = new ArrayList<Integer>();
		ArrayList<Integer> chkNumList = new ArrayList<Integer>();

		//k 진수 만들기
		while (n > 0) {
			changeList.add(n % k);
			n /= k;
		}

		Collections.reverse(changeList);

		boolean chkZero = true;
		String temp = "";
		for (int i = 0; i < changeList.size(); i++) {
			chkZero = (changeList.get(i) != 0) ? true : false;

			if (chkZero) {
				temp += String.valueOf(changeList.get(i));
			}

			if (chkZero == false) {
				if("".equals(temp)) {
					continue;
				}
				int tempInt = Integer.parseInt(temp);
				answer++;
				
				boolean isPrime = false;
				if (tempInt < 2) {
					isPrime = true;
				} else {
				
					prime: for (int j = 2; j < tempInt; j++) {
				
						if (tempInt % j == 0) {
							isPrime = true;
							break prime;
						}
					}
				
				}
				if (isPrime) {
					answer--;
				}
				temp = "";
			}

		}

		for (int i = 0; i < changeList.size(); i++) {

			System.out.print(changeList.get(i));

		}
		System.out.println(" ");

		return answer+1;
	}

}
