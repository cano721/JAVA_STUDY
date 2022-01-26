package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 우주전쟁 게임은 1:1 RTS 게임으로, 건물을 건설하고 유닛을 생성하여 싸우는 게임이다.
 * 건물에는 짓는 순서가 있기 때문에 선행되어야 하는 건물을 모두 건설하여야 건설할 수 있는 건물이 있다.
 * 이 게임에는 치트키가 있어, 건물을 마음대로 설치할 수 있다.
 * 건물의 순서와 건물의 건설 및 파괴 정보가 주어질 때, 치트키를 썻는지 안썻는지 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] relation;
	static int[] builtCnt, edge, builtEdge;

	/**
	 * 위상 정렬을 응용하여 푸는 문제
	 * 위상 정렬의 차수를 모든 node에 저장한다.
	 * 건물의 건설과 파괴가 주어질 때마다 건물 건설 개수를 증가시키거나 감소시킨다.
	 * 만약 건물이 0개에서 생성되는 경우 이후의 영향을 받는 건물의 차수를 모두 1 증가시킨다.
	 * 건물이 1개에서 파괴되는 경우 이후의 영향을 받는 건물의 차수를 모두 1 감소시킨다.
	 * 만약 edge에 저장된 필요 차수보다 작은 차수로 건물을 건설하였다면 치트키를 쓴 것이다.
	 * 또한, 건물의 개수가 0개 인데 파괴가 된 경우도 치트키를 쓴 경우이다.
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