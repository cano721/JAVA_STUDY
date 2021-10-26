import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D20211025_2_BJ_11729_하노이탑_이동_순서 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		sb.append((int) (Math.pow(2, n) - 1)).append("\n");
 
		dfs(n, 1, 2, 3, sb);
		System.out.println(sb);
	}

	public static void dfs(int N, int one, int two, int three, StringBuilder sb) throws IOException {
		if (N == 1) {
			sb.append(one).append(" ").append(three).append("\n");
			return;
		}
		dfs(N - 1, one, three, two, sb);
		sb.append(one).append(" ").append(three).append("\n");
		dfs(N - 1, two, one, three, sb);
 
	}
}