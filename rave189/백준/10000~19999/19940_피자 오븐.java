package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 피자를 오븐에 구우려고 한다.
 * 오븐에 넣은 피자를 정확히 N분 동안 동작시키려고 한다.
 * 오븐은 -1, 1, -10, 10, 60의 버튼이 있다.
 * N이 주어질 때, N을 만들기 위한 버튼의 최소 횟수와 방법을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[] buttons = { -1, 1, -10, 10, 60 };

	/**
	 * 시간이 0.25초로 매우 짧았음
	 * 근데 너비우선 + 그리디여서 while문안에서 돌리는 방식으로 계속 구현했었음. (시간초과, 메모리 초과)
	 * 그러다 어차피 60분 넘어가면 60분을 누르는게 무조건 빠른 방법이기 때문에
	 * 60으로 나눈 나머지 부분만 채워주면 맞출수 있을 것 같았음.
	 * min배열을 60까지만 했었다가 틀림.
	 * 60을 누르고 가는 방법이 짧을 수가 있어서 61로 바꿈
	 * 한 2~3시간정도 본듯....
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		int[][] min = new int[61][buttons.length];
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		boolean[] visited = new boolean[min.length];
		visited[0] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < buttons.length; i++) {
				int next = cur + buttons[i];
				try {
					if (visited[next])
						continue;
					visited[next] = true;
					q.add(next);
					System.arraycopy(min[cur], 0, min[next], 0, buttons.length);
					min[next][buttons.length - 1 - i]++;
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		while (testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int pass = n / 60;
			int remain = n % 60;
			answer.append(String.format("%d %d %d %d %d\n", min[remain][0] + pass, min[remain][1], min[remain][2],
					min[remain][3], min[remain][4]));
		}
		System.out.println(answer);
	}
}