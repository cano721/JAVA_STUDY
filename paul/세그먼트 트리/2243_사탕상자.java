import java.io.*;
import java.util.*;

public class Main {
	static int tree[];
    static int N, MAX = 1000001;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new int[MAX*4];

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			if(a == 1) {
				int b = Integer.parseInt(st.nextToken());
				int ans = search(1, MAX, 1, b);
				sb.append(ans+"\n");
			}else if(a == 2) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
                update(1, MAX, 1, b , c);
			}
		}
		
        System.out.println(sb);

	}
	

    public static void update(int start, int end, int node, int idx, int val) {
		if (idx < start || idx > end) return;
		tree[node] += val;
		if (start == end) return;
		int mid = (start + end) / 2;
		update(start, mid, 2*node, idx, val);
        update(mid+1, end, 2*node + 1, idx, val);
	}

    private static int search(int start, int end, int node, int target) {
        if (start == end) {
            update(1, 1, MAX, start, -1);
            return start;
        }

        int mid = (start + end) / 2;
        if (tree[node * 2] >= target)
            return search(node * 2, start, mid, target);
        else
            return search(node * 2 + 1, mid + 1, end, target - tree[node * 2]);
    }
}