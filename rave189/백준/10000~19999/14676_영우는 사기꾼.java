package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * �������� ������ 1:1 RTS ��������, �ǹ��� �Ǽ��ϰ� ������ �����Ͽ� �ο�� �����̴�.
 * �ǹ����� ���� ������ �ֱ� ������ ����Ǿ�� �ϴ� �ǹ��� ��� �Ǽ��Ͽ��� �Ǽ��� �� �ִ� �ǹ��� �ִ�.
 * �� ���ӿ��� ġƮŰ�� �־�, �ǹ��� ������� ��ġ�� �� �ִ�.
 * �ǹ��� ������ �ǹ��� �Ǽ� �� �ı� ������ �־��� ��, ġƮŰ�� ������ �ț����� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] relation;
	static int[] builtCnt, edge, builtEdge;

	/**
	 * ���� ������ �����Ͽ� Ǫ�� ����
	 * ���� ������ ������ ��� node�� �����Ѵ�.
	 * �ǹ��� �Ǽ��� �ı��� �־��� ������ �ǹ� �Ǽ� ������ ������Ű�ų� ���ҽ�Ų��.
	 * ���� �ǹ��� 0������ �����Ǵ� ��� ������ ������ �޴� �ǹ��� ������ ��� 1 ������Ų��.
	 * �ǹ��� 1������ �ı��Ǵ� ��� ������ ������ �޴� �ǹ��� ������ ��� 1 ���ҽ�Ų��.
	 * ���� edge�� ����� �ʿ� �������� ���� ������ �ǹ��� �Ǽ��Ͽ��ٸ� ġƮŰ�� �� ���̴�.
	 * ����, �ǹ��� ������ 0�� �ε� �ı��� �� ��쵵 ġƮŰ�� �� ����̴�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		builtCnt = new int[n + 1];
		edge = new int[n + 1];
		builtEdge = new int[n + 1];
		relation = new HashSet[n + 1];
		for (int i = 1; i <= n; i++)
			relation[i] = new HashSet<>();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			if (!relation[first].contains(second)) {
				relation[first].add(second);
				edge[second]++;
			}
		}
		while (k-- > 0) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (!exec(command, num)) {
				System.out.println("Lier!");
				return;
			}
		}
		System.out.println("King-God-Emperor");
	}

	public static boolean exec(int command, int n) {
		if (command == 1) {
			if (builtEdge[n] == edge[n]) {
				builtCnt[n]++;
				if (builtCnt[n] == 1)
					for (int next : relation[n])
						builtEdge[next]++;
				return true;
			}
		} else if (builtCnt[n] > 0) {
			builtCnt[n]--;
			if (builtCnt[n] == 0)
				for (int next : relation[n])
					builtEdge[next]--;
			return true;
		}
		return false;
	}
}