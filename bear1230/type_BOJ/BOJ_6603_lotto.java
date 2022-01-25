import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int n;
	static int num[];
	static int temp[];
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
        
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			num = new int[n];
			temp = new int[n];
			if (n == 0)
				break;

			for (int i = 0; i < n; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			solve(0, 0);
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void solve(int start, int idx) {
		if (idx == 6) {
			for (int i = 0; i < idx; i++) {
				sb.append(temp[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i < n; i++) {
			temp[idx] = num[i];
			solve(i + 1, idx + 1);
		}

	}
}
