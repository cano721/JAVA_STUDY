package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 등굣길의 높이가 N개의 수로 주어진다.
 * 이 수들 중 가장 큰 오르막길을 찾는 문제
 * 오르막길은 높이가 높이가 증가하는 두 수로 이루어져야 한다.
 * 
 * @author Rave
 *
 */
public class Main {

	/**
	 * 처음 수를 시작으로 탐색을 하며 차이가 가장 큰 두 수를 찾는다.
	 * 만약 수가 낮거나 같아진다면 오르막길이 아니므로 prev와 max를 next로 업데이트 해준다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken());
		int max = prev;
		int answer = 0;
		while (--n > 0) {
			int next = Integer.parseInt(st.nextToken());
			if (max < next)
				answer = Math.max(answer, next - prev);
			else
				prev = next;
			max = next;
		}
		System.out.println(answer);
	}
}