import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D20211024_4_BJ_6603_로또 {

	static int N;
	static int[] arr;
	static boolean[] Visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			String arrStr = br.readLine();
			if(arrStr.equals("0")) break;
			
			st = new StringTokenizer(arrStr, " ");
			
			N = Integer.parseInt(st.nextToken());
			arr = new int[N];
			Visited = new boolean[N];
			
			for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			dfs(0, 0);
			sb.append("\n");

		}
		System.out.println(sb);
	}

	private static void dfs(int point, int depth) {
		if (depth == 6) {
			for (int i = 0; i < N; i++) if (Visited[i] == true) sb.append(arr[i]).append(" ");
			sb.append("\n");
		}

		for (int i = point; i < N; i++) {
			Visited[i] = true;
			dfs(i + 1, depth + 1);
			Visited[i] = false;
		}

	}

}