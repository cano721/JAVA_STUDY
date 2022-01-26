package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 유토피아의 왕이 죽어 다음 왕을 선출해야 한다.
 * 유토피아는 나라를 세운 사람과 가장 가까운 혈통의 사람이 왕이된다.
 * 나라를 세운 사람과 가족 관계가 주어지고, 왕이 되고 싶은 사람이 주어졌을 때
 * 다음 왕이 될 사람을 구하는 문제
 * 답은 항상 유일한 답이 나온다.
 * @author Rave
 *
 */
public class Main {

	static Map<String, Integer> hash = new HashMap<>();
	static HashSet<Integer>[] map;

	/**
	 * 가족 관계가 순서대로 나온다는 보장이 없으므로 관계를 설정하고 계산은 나중에 해야 한다.
	 * 계산은 위상 정렬을 사용하여 계산 한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String create = br.readLine();
		double[] blood = new double[n * 3];
		int[] degree = new int[blood.length];
		map = new HashSet[blood.length];
		for (int i = 0; i < map.length; i++)
			map[i] = new HashSet<>();
		hash.put(create, 0);
		blood[0] = 1;
		int idx = 1;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String[] family = new String[3];
			for (int j = 0; j < family.length; j++) {
				family[j] = st.nextToken();
				if (!hash.containsKey(family[j]))
					hash.put(family[j], idx++);
			}
			map[hash.get(family[1])].add(hash.get(family[0]));
			map[hash.get(family[2])].add(hash.get(family[0]));
			degree[hash.get(family[0])] += 2;
		}
		Queue<Integer> q = new LinkedList<>();
		for (String name : hash.keySet()) {
			if (degree[hash.get(name)] == 0)
				q.add(hash.get(name));
		}
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : map[cur]) {
				blood[next] += blood[cur] / 2;
				degree[next]--;
				if (degree[next] == 0)
					q.add(next);
			}
		}
		double max = 0;
		String answer = "";
		while (m-- > 0) {
			String candidate = br.readLine();
			double result = hash.containsKey(candidate) ? blood[hash.get(candidate)] : 0;
			if (result > max) {
				max = blood[hash.get(candidate)];
				answer = candidate;
			}
		}
		System.out.println(answer);
	}
}