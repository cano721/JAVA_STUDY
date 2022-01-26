package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 수직선위에 얼음이 담긴 양동이가 있다.
 * 너무 더운 백곰은 양 손을 뻗어 K만큼 떨어진 곳의 양동이를 가져오려고 한다.
 * 이 때, 가져올 수 있는 최대한의 얼음이 얼마인지 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 이분탐색을 통해 양손 + 백곰의 자리인 2K+1  범위안에 양동이가 있다면 그 안의 얼음의 합이 최대인지 구한다.
	 * 이 때, x의 위치로 정렬을 한 후 구해야 한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Pail[] pails = new Pail[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pails[i] = new Pail(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(pails);
		int answer = 0, left = 0, right = 0, sum = 0;
		while(left <= right && right < n) {
			if(pails[right].x - pails[left].x <= 2*k + 1) {
				sum += pails[right++].amount;
				answer = Math.max(answer, sum);
			} else
				sum -= pails[left++].amount;
		}
		System.out.println(answer);
	}
}

class Pail implements Comparable<Pail> {
	int amount, x;

	public Pail(int amount, int x) {
		this.amount = amount;
		this.x = x;
	}

	@Override
	public int compareTo(Pail o) {
		return x - o.x;
	}

}