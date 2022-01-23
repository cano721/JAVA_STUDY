package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N���� ������ �ְ� K���� ����� �־�����.
 * ����� N���� ���� �� i��° ���� �ٲٴ� ����� �ְ�
 * i��° ������ j��° �������� ���� ������� �������� 0���� ����ϴ� ����� �ִ�.
 * ������ ����� ����ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int[] tree;
	static int s;

	/**
	 * tree�� ������ 1, ����� 0, 0�� -1�� �����Ѵ�. (����� ����� ���� ������ ��ġ�� �����Ƿ� 0���� ����)
	 * ���� -1�� ������ Ʈ���� �θ�鵵 -1�� �ʱ�ȭ, �ƴ϶�� ������ ������ ������ �ʱ�ȭ �ϵ��� �Ѵ�.
	 * �� ������ ���� �� �θ� ��带 ������ �����µ� ���浵 0�� ������ -1 ������ ������ ������ ������ �������ش�.
	 * ����� 0�� �ϳ��� ������ 0��, ������ ������ Ȧ�� ���̸� -, �ƴ� ��� ���� +�� ����Ѵ�.
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