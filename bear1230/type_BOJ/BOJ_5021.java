import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static String king;
	static HashMap<String, String[]> map = new HashMap<>();
	static HashMap<String, Double> blood = new HashMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		king = br.readLine();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent1 = st.nextToken();
			String parent2 = st.nextToken();

			map.put(child, new String[] { parent1, parent2 });
			blood.put(parent1, 0.0);
			blood.put(parent2, 0.0);
		}
		blood.put(king, 1.0);
		String ans = "";
		double max = -1;
		for (int i = 0; i < m; i++) {
			String name = br.readLine();

			double blood = dfs(name);
			if (max < blood) {
				max = blood;
				ans = name;
			}
		}
		System.out.println(ans);
	}

	private static double dfs(String name) {
		if (!map.containsKey(name)) {
			if (blood.containsKey(name)) {
				return blood.get(name);
			} else
				return 0.0;

		}
		double blood = 0;

		for (String parent : map.get(name)) {

			blood += (dfs(parent) / 2.0);
		}

		return blood;

	}

}
