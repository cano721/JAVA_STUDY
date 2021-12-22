import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
        
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			parent = new int[N + 1];

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				parent[b] = a;

			}
            
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			int v1_depth = get_depth(v1);
			int v2_depth = get_depth(v2);

			System.out.println(LCA(v1, v1_depth, v2, v2_depth));
		}
	
	}

	public static int get_depth(int a) {
		int depth = 0;
        int cur = a;

        while (cur != 0) {
            depth++;
            cur = parent[cur];
        }

        return depth - 1;
	}

	public static int LCA(int x, int x_depth, int y, int y_depth) {
		if (x_depth > y_depth) {
			while (x_depth != y_depth) {
				x_depth--;
				x = parent[x];
			}
		} else if (x_depth < y_depth) {
			while (x_depth != y_depth) {
				y_depth--;
				y = parent[y];
			}
		}
		while (x != y) {
			x = parent[x];
			y = parent[y];
		}

		return x;
	}
}