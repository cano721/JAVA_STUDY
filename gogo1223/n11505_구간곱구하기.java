import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n11505_구간곱구하기 {
	static int N, M, K;
	static long[] arr, tree;
	static int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new long[N];
		tree = new long[N*4];
		Arrays.fill(tree, 1);
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		init(0, N-1, 1);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K+M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if(a == 1) {
				arr[b-1] = c;
				update(0, N-1, 1, b-1, c);
			}else if(a == 2) {
				sb.append(product(0, N-1, 1, b-1, (int)(c-1))+"\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static long init(int start, int end, int node) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return tree[node] = (init(start, mid, node*2) * init(mid+1, end, node*2+1)) % MOD;
	}
	
	private static long update(int start, int end, int node, int index, long dif) {
		if(index < start || index > end) return tree[node];
		if(start == end) return tree[node] = dif;
		
		int mid = (start + end) / 2;
		return tree[node] = (update(start, mid, node*2, index, dif) * update(mid+1, end, node*2+1, index, dif)) % MOD;
	}
	
	private static long product(int start, int end, int node, int left, int right) {
		if(right < start || left > end) return 1;
		else if(left <= start && end <= right) return tree[node];
		else {
			int mid = (start + end) / 2;
			return (product(start, mid, node*2, left, right) * product(mid+1, end, node*2+1, left, right)) % MOD;
		}
	}
}
