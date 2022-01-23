import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
	static int[] tree, arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String input = "";
		while ((input = br.readLine()) != null) { 
			st = new StringTokenizer(input);

		    N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				arr[i] = (temp == 0) ? 0 : (temp > 0) ? 1 : -1;
			}

			tree = new int[N * 4];
			init(1, N, 1);

			while (K-- > 0) {
				st = new StringTokenizer(br.readLine());

				String command = st.nextToken();
				int i = Integer.parseInt(st.nextToken());

				if (command.equals("C")) {
					int V = Integer.parseInt(st.nextToken());
					V = (V == 0) ? 0 : (V > 0) ? 1 : -1;

					update(1, N, 1, i, V);
				} else if (command.equals("P")) {
					int j = Integer.parseInt(st.nextToken());
					long res = mul(1, N, 1, i, j);

					sb.append((res == 0) ? 0 : (res > 0) ? "+" : "-");
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static int init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1);
	}

	public static int mul(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 1;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return mul(start, mid, node * 2, left, right) * mul(mid + 1, end, node * 2 + 1, left, right);
	}

	public static int update(int start, int end, int node, int idx, int val) {
		if (idx < start || idx > end) {
			return tree[node];
		}

		if (start == end) {
			return tree[node] = val;
		}

		int mid = (start + end) / 2;
		return tree[node] = update(start, mid, node * 2, idx, val) * update(mid + 1, end, node * 2 + 1, idx, val);
	}
}

