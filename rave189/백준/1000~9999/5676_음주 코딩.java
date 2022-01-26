package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 수열이 있고 K개의 명령이 주어진다.
 * 명령은 N개의 수열 중 i번째 수를 바꾸는 명령이 있고
 * i번째 수부터 j번째 수까지의 곱이 양수인지 음수인지 0인지 대답하는 명령이 있다.
 * 곱셈의 결과를 출력하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[] tree;
	static int s;

	/**
	 * tree를 음수는 1, 양수는 0, 0은 -1로 지정한다. (양수는 결과에 전혀 영향을 미치지 않으므로 0으로 설정)
	 * 이후 -1이 있으면 트리의 부모들도 -1로 초기화, 아니라면 음수의 개수의 합으로 초기화 하도록 한다.
	 * 값 변경은 변경 후 부모 노드를 변경해 나가는데 변경도 0이 있으면 -1 없으면 음수의 개수의 합으로 변경해준다.
	 * 출력은 0이 하나라도 있으면 0을, 음수의 개수가 홀수 개이면 -, 아닌 모든 경우는 +를 출력한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			s = 1;
			while (s < n) {
				s *= 2;
			}
			tree = new int[2 * s];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 0)
					tree[s + i] = -1;
				else if (value < 0)
					tree[s + i] = 1;
			}
			for (int i = s - 1; i > 0; i--) {
				if (tree[i * 2] == -1 || tree[i * 2 + 1] == -1)
					tree[i] = -1;
				else
					tree[i] = tree[i * 2] + tree[i * 2 + 1];
			}
			while (k-- > 0) {
				st = new StringTokenizer(br.readLine());
				char command = st.nextToken().charAt(0);
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				if (command == 'C')
					swap(i, j);
				else {
					int result = query(1, s, 1, i, j);
					answer.append(result > 0 && result % 2 == 1 ? '-' : result < 0 ? '0' : '+');
				}
			}
			answer.append('\n');
		}
		System.out.println(answer);
	}

	public static void swap(int n, int target) {
		int index = s + n - 1;
		tree[index] = target < 0 ? 1 : target == 0 ? -1 : 0;
		while ((index /= 2) > 0) {
			if (tree[index * 2] == -1 || tree[index * 2 + 1] == -1)
				tree[index] = -1;
			else
				tree[index] = tree[index * 2] + tree[index * 2 + 1];
		}
	}

	public static int query(int left, int right, int node, int queryLeft, int queryRight) {
		if (queryRight < left || right < queryLeft)
			return 0;
		else if (queryLeft <= left && right <= queryRight)
			return tree[node];
		else if (left == right)
			return tree[left];
		else {
			int mid = (left + right) / 2;
			int leftResult = query(left, mid, node * 2, queryLeft, queryRight);
			if (leftResult == -1)
				return leftResult;
			int rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
			if (rightResult == -1)
				return rightResult;
			return leftResult + rightResult;
		}
	}
}